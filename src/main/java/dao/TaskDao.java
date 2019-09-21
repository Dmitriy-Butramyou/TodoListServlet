package dao;

import model.Task;

import java.util.List;

public interface TaskDao {

    Task getOne(Long taskId);
    void markAsDeleted(Task task);
    void markAsComplete(Task task);
    void markAsActual(Task task);

    void remove(Long taskId);
    void removeAttachment(Long taskId);
    void removeAllFromBasket(Long userId);
    void removeAllByUser(Long userId);
    Task save(Task task);

    List<Task> findAllByUser(Long userId);
    List<Task> findActualByUser(Long userId);
    List<Task> findByUserAndDay(Long userId, Long time, Boolean relevance);
    List<Task> findAllByPerformed(Long userId);
    List<Task> findAllByBasket(Long userId);
}
