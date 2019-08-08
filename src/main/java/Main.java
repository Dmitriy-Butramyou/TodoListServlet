import dao.TaskDao;
import dao.impl.TaskDaoImpl;
import model.Task;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TaskDao taskDao = new TaskDaoImpl();
//
        List<Task> tasks = taskDao.findAll();

        for (Task task : tasks) {
            System.out.println(task);
        }
//
//       Boolean result = taskDao.remove(1L );
//
//        System.out.println("-------------");
//        System.out.println(result);
        System.out.println("-------------");

        taskDao.markAsComplete(2L);
        taskDao.markAsActual(5L);
//        taskDao.markAsDeleted(5L);


        tasks = taskDao.findAll();
//                Long d = new Date().getTime();
//        System.out.println(d);


        for (Task task : tasks) {
            System.out.println(task);
        }



    }
}
