package controller.web;

import dao.TaskDao;
import dao.impl.TaskDaoImpl;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/performed")
public class PerformedTask extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");
        req.setAttribute("name", "Please login");

        List<Task> tasks = new ArrayList<>();

        if(userSession != null) {
            tasks = taskDao.findAllByPerformed(userSession.getId());
            req.setAttribute("name", userSession.getName());
        }
        req.setAttribute("tasks", tasks);
        req.getRequestDispatcher("view/performed.jsp")
                .forward(req, resp);
    }
}
