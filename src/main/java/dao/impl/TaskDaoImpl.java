package dao.impl;

import dao.TaskDao;
import jdbc.connector.MySqlConnector;
import jdbc.query.MySqlQuery;
import model.State;
import model.Task;
import util.CloseConnection;
import util.DateUtils;
import util.PropertyUtils;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    private Connection connection = MySqlConnector.getInstance().getConnection();

    @Override
    public Task getOne(Long taskId) {
        String query = MySqlQuery.getInstance().getQuery("taskGetOne");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, taskId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getBuildTask(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public List<Task> findActualByUser(Long userId) {
        String query = MySqlQuery.getInstance().getQuery("taskGetActualByUser");

        List<Task> taskList = findAllBy(userId, query);
        if (taskList != null) return taskList;
        return new ArrayList<>(0);
    }

    /**
     * Получение заданий по дням
     *
     * @param userId Id из сессии юзера
     * @param time   Дата запроса
     * @return
     */
    @Override
    public List<Task> findByUserAndDay(Long userId, Long time, Boolean relevance) {
        String query;
        if (relevance) {
            query = MySqlQuery.getInstance().getQuery("taskFindByUserAndDay");
        } else {
            query = MySqlQuery.getInstance().getQuery("taskFindByUserDeadlineMissing");
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, time);
            resultSet = preparedStatement.executeQuery();
            List<Task> taskList = new ArrayList<>();
            return addTasksToTheList(resultSet, taskList);
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, resultSet);
        }
        return null;
    }


    /**
     * get all where state = 3
     */
    @Override
    public List<Task> findAllByBasket(Long userId) {
        String query = MySqlQuery.getInstance().getQuery("taskFindAllByBasket");
        List<Task> taskList = findAllBy(userId, query);
        if (taskList != null) return taskList;
        return new ArrayList<>(0);
    }

    /**
     * get all where state = 2
     */
    @Override
    public List<Task> findAllByPerformed(Long userId) {
        String query = MySqlQuery.getInstance().getQuery("taskFindAllByPerformed");

        List<Task> taskList = findAllBy(userId, query);
        if (taskList != null) return taskList;
        return new ArrayList<>(0);
    }

    @Override
    public Task save(Task task) {
        PreparedStatement preparedStatement = null;
        String query = task.getId() != null ?
                MySqlQuery.getInstance().getQuery("taskUpdate") :
                MySqlQuery.getInstance().getQuery("taskInsert");
        try {
            preparedStatement = task.getId() != null ?
                    connection.prepareStatement(query) :
                    connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setLong(3, task.getEventDate().getTime());
            preparedStatement.setLong(4, task.getCreationDateTime().getTime());
            preparedStatement.setInt(5, State.getNumber(task.getState()));
            //немного изменил
            preparedStatement.setLong(6, task.getUserId());
            preparedStatement.setString(7, task.getOriginalFileName());
            preparedStatement.setString(8, task.getGeneratedFileName());
            preparedStatement.setString(9, task.getGeneratedFilePath());
            if (task.getId() != null) {
                preparedStatement.setLong(10, task.getId());
                return preparedStatement.executeUpdate() > 0 ? task : null;
            } else {
                preparedStatement.executeUpdate();
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    task.setId(resultSet.getLong(1));
                    resultSet.close();
                    return task;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, null);
        }
        return null;
    }

    @Override
    public void markAsDeleted(Task task) {
        task.setState(State.DELETE);
        save(task);
    }

    @Override
    public void markAsComplete(Task task) {
        task.setState(State.COMPLETE);
        save(task);
    }

    @Override
    public void markAsActual(Task task) {
        // Установка сегодняшней даты
        boolean isDeleted = task.getState().equals(State.DELETE);
        task.setEventDate(isDeleted ? DateUtils.setTimeToMidnight(new Date()) : task.getEventDate());
        task.setState(State.ACTUAL);
        save(task);
    }

    @Override
    public void remove(Long taskId) {
        String query = MySqlQuery.getInstance().getQuery("taskDelete");
        PropertyUtils.findOne(taskId, query, connection);
    }

    @Override
    public void removeAllByUser(Long userId) {
        String query = MySqlQuery.getInstance().getQuery("taskDeleteByUser");
        PropertyUtils.findOne(userId, query, connection);
    }

    @Override
    public void removeAllFromBasket(Long userId) {
        String query = MySqlQuery.getInstance().getQuery("taskAllDeleteFromBasket");
        PropertyUtils.findOne(userId, query, connection);
    }

    @Override
    public void removeAttachment(Long taskId) {
        String query = MySqlQuery.getInstance().getQuery("attachmentDelete");
        PropertyUtils.findOne(taskId, query, connection);
    }

    @Override
    public List<Task> findAllByUser(Long userId) {
        String query = MySqlQuery.getInstance().getQuery("taskAllByUser");
        List<Task> taskList = findAllBy(userId, query);
        if (taskList != null) return taskList;
        return new ArrayList<>(0);
    }

    private List<Task> findAllBy(Long userIdOrTime, String query) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userIdOrTime);
            resultSet = preparedStatement.executeQuery();
            List<Task> taskList = new ArrayList<>();

            return addTasksToTheList(resultSet, taskList);
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, resultSet);
        }
        return null;
    }

    private List<Task> addTasksToTheList(ResultSet resultSet, List<Task> taskList) throws SQLException {
        while (resultSet.next()) {
            taskList.add(
                    getBuildTask(resultSet)
            );
        }
        return taskList;
    }

    private Task getBuildTask(ResultSet resultSet) throws SQLException {
        return new Task
                .Builder()
                .id(resultSet.getLong(1))
                .name(resultSet.getString(2))
                .description(resultSet.getString(3))
                .eventDate(new Date(resultSet.getLong(4)))
                .creationDateTime(new Date(resultSet.getLong(5)))
                .state(State.getState(resultSet.getInt(6)))
                .userId(resultSet.getLong(7))
                .originalFileName(resultSet.getString(8))
                .generatedFileName(resultSet.getString(9))
                .generatedFilePath(resultSet.getString(10))
                .build();
    }


}
