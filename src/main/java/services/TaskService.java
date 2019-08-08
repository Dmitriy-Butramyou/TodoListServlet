package services;

import model.Task;

import java.util.List;

public interface TaskService {

    Task getOne(Long taskId);
    List<Task> findAll(String day);
    Task save(Task task);
    Task markAsDeleted(Long taskId);
    List<Task> findAllFromBasket();
    void remove(long taskId);
}
