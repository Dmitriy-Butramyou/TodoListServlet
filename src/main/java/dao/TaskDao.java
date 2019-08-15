package dao;

import model.Task;

import java.util.List;

public interface TaskDao {

    Task getOne(Long taskId);
    List<Task> findAll(String day);
    List<Task> findAll();
    Task save(Task task);
    Task markAsDeleted(Long taskId);
    Task markAsComplete(Long taskId);
    Task markAsActual(Long taskId);
    List<Task> findAllFromBasket();
    Boolean remove(Long taskId);
}
