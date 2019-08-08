package controller.web;

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


@WebServlet("/mark_delete/*")
public class MarkDelete  extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfo = req.getPathInfo().split("/");
        Long id = Long.valueOf(pathInfo[1]);

        Task task = taskDao.getOne(id);

        Boolean isDeleted = task.getState().equals(State.DELETE);

        if(!isDeleted) {
            task.setState(State.DELETE);
            taskDao.save(task);
        } else if (isDeleted){
            task.setState(State.ACTUAL);
            taskDao.save(task);
        }

        String path = req.getContextPath() + "/";
        resp.sendRedirect(path);
    }

}
