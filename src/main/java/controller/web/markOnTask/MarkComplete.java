package controller.web.markOnTask;

import dao.TaskDao;
import dao.impl.TaskDaoImpl;
import model.State;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mark_complete/*")
public class MarkComplete extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfo = req.getPathInfo().split("/");
        Long id = Long.valueOf(pathInfo[1]);

        Task task = taskDao.getOne(id);

        Boolean isComplete = task.getState().equals(State.COMPLETE);

        if(!isComplete) {
            taskDao.markAsComplete(task);
        } else {
            taskDao.markAsActual(task);
        }

        String path = req.getContextPath() + "/allTask";
        resp.sendRedirect(path);
    }

}
