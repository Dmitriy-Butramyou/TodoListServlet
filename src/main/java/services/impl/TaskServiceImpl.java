package services.impl;

import dao.TaskDao;
import dao.impl.TaskDaoImpl;
import model.Task;
import services.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {

    private TaskDao taskDao = new TaskDaoImpl();
    @Override
    public Task getOne(Long taskId) {
        return taskDao.getOne(taskId);
    }


    @Override
    public List<Task> findAll(String day) {
        return taskDao.findAll(day);
    }

    @Override
    public Task save(Task task) {
        return taskDao.save(task);
    }

    @Override
    public Task markAsDeleted(Long taskId) {
        return taskDao.markAsDeleted(taskId);
    }

    @Override
    public List<Task> findAllFromBasket() {
        return taskDao.findAllFromBasket();
    }

    @Override
    public void remove(long taskId) {
        taskDao.remove(taskId);
    }
}
