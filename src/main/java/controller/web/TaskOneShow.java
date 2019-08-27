package controller.web;

import dao.AttachmentDao;
import dao.TaskDao;
import dao.impl.AttachmentDaoImpl;
import dao.impl.TaskDaoImpl;
import model.Attachment;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/task/*")
public class TaskOneShow extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();
    private AttachmentDao attachmentDao = new AttachmentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");

        if (userSession != null) {
            String[] pathInfo = req.getPathInfo().split("/");
            Long taskId = Long.valueOf(pathInfo[1]);

            Task task = taskDao.getOne(taskId);
            Attachment attachment = attachmentDao.getOne(taskId);

            req.setAttribute("task", task);
            req.setAttribute("attachment", attachment);
            req.getRequestDispatcher("view/taskOne.jsp")
                    .forward(req, resp);
        } else {
            String path = req.getContextPath() + "/login";
            resp.sendRedirect(path);
        }
    }


}
