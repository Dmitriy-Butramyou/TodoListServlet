package controller.web;

import dao.TaskDao;
import dao.impl.TaskDaoImpl;
import model.Task;
import model.User;
import util.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/basket")
@MultipartConfig
public class BasketTask extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");

        if(userSession != null) {
            List<Task> tasks = taskDao.findAllByBasket(userSession.getId());
            req.setAttribute("name", userSession.getName());
            req.setAttribute("tasks", tasks);
            req.getRequestDispatcher("view/basket.jsp")
                    .forward(req, resp);
        } else {
            String path = req.getContextPath() + "/login";
            resp.sendRedirect(path);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        Part attachment = req.getPart("attachment");
        Long taskId = Long.valueOf(req.getParameter("taskId"));

        Task task = taskDao.getOne(taskId);
        taskDao.save(FileUtils.uploadAttachment(attachment, task));
        resp.sendRedirect("/allTask");

    }

}
