package controller.web;

import dao.impl.TaskDaoImpl;
import model.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/")
public class TaskShowServlet extends HttpServlet {

    private TaskDaoImpl taskDao = new TaskDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Task> tasks = taskDao.findAll();

        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("view/showTasks.jsp")
                .forward(req, resp);
    }


}
