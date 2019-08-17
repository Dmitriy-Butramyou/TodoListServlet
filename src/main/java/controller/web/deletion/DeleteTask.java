package controller.web.deletion;

import dao.TaskDao;
import dao.impl.TaskDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete/*")
public class DeleteTask extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfo = req.getPathInfo().split("/");
        Long taskId = Long.valueOf(pathInfo[1]);

        taskDao.remove(taskId);

        String path = req.getContextPath() + "/basket";
        resp.sendRedirect(path);
    }
}
