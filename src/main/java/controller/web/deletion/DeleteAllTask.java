package controller.web.deletion;

import dao.TaskDao;
import dao.impl.TaskDaoImpl;
import model.Task;
import model.User;
import util.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/deleteAll")
public class DeleteAllTask extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");

        Iterable<Task> tasks = taskDao.findAllByBasket(userSession.getId());

        for (Task task : tasks) {
            if (task.getOriginalFileName() != null) {
                FileUtils.removeFile(task.getGeneratedFilePath(), task.getGeneratedFileName());
            }
        }

        taskDao.removeAll(userSession.getId());

        String path = req.getContextPath() + "/basket";
        resp.sendRedirect(path);
    }
}
