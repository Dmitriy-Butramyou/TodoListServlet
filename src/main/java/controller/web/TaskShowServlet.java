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
import java.util.List;

@WebServlet("/allTask")
public class TaskShowServlet extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();
    private AttachmentDao attachmentDao = new AttachmentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");


        if (userSession != null) {
            List<Attachment> attachments = attachmentDao.getAll();
            List<Task> tasks = taskDao.findAllByUser(userSession.getId());

            req.setAttribute("attachments", attachments);
            req.setAttribute("name", userSession.getName());
            req.setAttribute("tasks", tasks);
            req.getRequestDispatcher("view/showTasks.jsp")
                    .forward(req, resp);
            //защита от дурака
        } else {
            String path = req.getContextPath() + "/login";
            resp.sendRedirect(path);
        }


    }


}
