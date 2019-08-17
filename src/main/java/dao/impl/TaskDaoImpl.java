package dao.impl;

import dao.TaskDao;
import jdbc.connector.MySqlConnector;
import jdbc.query.MySqlQuery;
import model.State;
import model.Task;
import util.CloseConnection;

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
                return Task
                        .builder()
                        .id(resultSet.getLong(1))
                        .name(resultSet.getString(2))
                        .description(resultSet.getString(3))
                        .eventDate(resultSet.getLong(4))
                        .creationDateTime(resultSet.getLong(5))
                        .state(State.getState(resultSet.getInt(6)))
                        .userId(resultSet.getLong(7))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, resultSet);
        }
        return null;
    }
//
//    @Override
//    public List<Task> findAll() {
//        String query = MySqlQuery.getInstance().getQuery("taskGetAll");
//
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        List<Task> taskList = getTasks(query, preparedStatement, resultSet);
//        if (taskList != null) return taskList;
//        return new ArrayList<>(0);
//    }

    @Override
    public List<Task> findAllByUser(Long userId) {
        String query = MySqlQuery.getInstance().getQuery("taskGetAllByUser");

        List<Task> taskList = findAllBy(userId, query);
        if (taskList != null) return taskList;
        return new ArrayList<>(0);

    }

    private List<Task> findAllBy(Long userId, String query) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            List<Task> taskList = new ArrayList<>();
            while (resultSet.next()) {
                taskList.add(
                        Task
                                .builder()
                                .id(resultSet.getLong(1))
                                .name(resultSet.getString(2))
                                .description(resultSet.getString(3))
                                .eventDate(resultSet.getLong(4))
                                .creationDateTime(resultSet.getLong(5))
                                .state(State.getState(resultSet.getInt(6)))
                                .userId(resultSet.getLong(7))
                                .build()
                );
            }
            return taskList;
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, resultSet);
        }
        return null;
    }

    @Override
    public List<Task> findAll(String day) {
        String query;
        if (day.equalsIgnoreCase("today") || day.equalsIgnoreCase("tomorrow")) {
            query = MySqlQuery.getInstance().getQuery("taskFindAllByDay");
        } else if (day.equalsIgnoreCase("someday")) {
            query = MySqlQuery.getInstance().getQuery("taskFindAllSomeday");
        } else {
            return new ArrayList<>(0);
        }
        long dateLong = day.equalsIgnoreCase("today") ? getTime(0) : getTime(86400000);
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, dateLong);
            resultSet = preparedStatement.executeQuery();
            List<Task> taskList = new ArrayList<>();
            while (resultSet.next()) {
                taskList.add(
                        Task
                                .builder()
                                .id(resultSet.getLong(1))
                                .name(resultSet.getString(2))
                                .description(resultSet.getString(3))
                                .eventDate(resultSet.getLong(4))
                                .creationDateTime(resultSet.getLong(5))
                                .state(State.getState(resultSet.getInt(6)))
                                .userId(resultSet.getLong(7))
                                .build()
                );
            }
            return taskList;
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, resultSet);
        }
        return new ArrayList<>(0);
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


    private long getTime(long value) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateFormat.format(new Date())).getTime() + value;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
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
            preparedStatement.setLong(3, task.getEventDate());
            preparedStatement.setLong(4, task.getCreationDateTime());
            preparedStatement.setInt(5, State.getNumber(task.getState()));
            //немного изменил
            preparedStatement.setLong(6, task.getUserId());
            if (task.getId() != null) {
                preparedStatement.setLong(7, task.getId());
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
        Boolean isDeleted = task.getState().equals(State.DELETE);
        task.setEventDate(isDeleted ? getTime(0) : task.getEventDate());
        task.setState(State.ACTUAL);
        save(task);
    }


    private List<Task> getTasks(String query, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            List<Task> taskList = new ArrayList<>();
            while (resultSet.next()) {
                taskList.add(
                        Task
                                .builder()
                                .id(resultSet.getLong(1))
                                .name(resultSet.getString(2))
                                .description(resultSet.getString(3))
                                .eventDate(resultSet.getLong(4))
                                .creationDateTime(resultSet.getLong(5))
                                .state(State.getState(resultSet.getInt(6)))
                                .userId(resultSet.getLong(7))
                                .build()
                );
            }
            return taskList;
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, resultSet);
        }
        return null;
    }


    @Override
    public Boolean remove(Long taskId) {
        String query = MySqlQuery.getInstance().getQuery("taskDelete");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, taskId);
            preparedStatement.executeUpdate();
            return true;
//            if(preparedStatement.executeUpdate() > 0) {
//                //записать в лог
//            } else {
//                // записать в лог
//            }
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, resultSet);
        }
        return false;
    }

    @Override
    public void removeAll(Long userId) {
        String query = MySqlQuery.getInstance().getQuery("taskAllDelete");
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
//            if(preparedStatement.executeUpdate() > 0) {
//                //записать в лог
//            } else {
//                // записать в лог
//            }
        } catch (SQLException e) {
            e.printStackTrace();
            CloseConnection.close(preparedStatement, resultSet);
        }
    }
}
