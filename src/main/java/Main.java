import dao.TaskDao;
import dao.impl.TaskDaoImpl;

public class Main {
    public static void main(String[] args) {

        TaskDao taskDao = new TaskDaoImpl();
        taskDao.remove(15L);
    }
}
