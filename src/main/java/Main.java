import dao.TaskDao;
import dao.impl.TaskDaoImpl;
import model.Task;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        TaskDao taskDao = new TaskDaoImpl();
        List<Task> task = taskDao.findAllByUser(1L);
        System.out.println(task);
    }
}
