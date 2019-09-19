package controller.web;

import dao.TaskDao;
import dao.UserDao;
import dao.impl.TaskDaoImpl;
import dao.impl.UserDaoImpl;
import model.State;
import model.Task;
import model.User;
import util.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/profile")
public class UserProfile extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");

        if (userSession != null) {
            List<Task> tasks = taskDao.findAllByUser(userSession.getId());
            Date date = DateUtils.setTimeToMidnight(new Date());
            int actual = 0;
            int deadlineMissing = 0;
            int performed = 0;
            int remote = 0;
            for (Task task : tasks) {
                if(task.getState().equals(State.COMPLETE))
                    performed++;
                if(task.getState().equals(State.DELETE))
                    remote++;
                if (date.getTime() > task.getEventDate().getTime() & task.getState().equals(State.ACTUAL)) {
                    deadlineMissing++;
                    continue;
                }
                if(task.getState().equals(State.ACTUAL))
                    actual++;

            }

            req.setAttribute("actual", actual);
            req.setAttribute("deadlineMissing", deadlineMissing);
            req.setAttribute("performed", performed);
            req.setAttribute("remote", remote);
            req.setAttribute("name", userSession.getName());
            req.getRequestDispatcher("/view/profile.jsp")
                    .forward(req, resp);
        } else {
            String path = req.getContextPath() + "/login";
            resp.sendRedirect(path);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");
        String userName = req.getParameter("username");
        String password = req.getParameter("password");


        if(userName != null && !userName.trim().isEmpty()) {
            userSession.setName(userName);
        }
        if(password != null && !password.trim().isEmpty()) {
            userSession.setPassword(password);
        }
        userDao.save(userSession);
        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
