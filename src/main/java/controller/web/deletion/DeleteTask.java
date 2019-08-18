package controller.web.deletion;

import dao.AttachmentDao;
import dao.TaskDao;
import dao.impl.AttachmentDaoImpl;
import dao.impl.TaskDaoImpl;
import model.Attachment;
import util.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete/*")
public class DeleteTask extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();
    private AttachmentDao attachmentDao = new AttachmentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfo = req.getPathInfo().split("/");
        Long taskId = Long.valueOf(pathInfo[1]);


        Attachment attachment = attachmentDao.getOne(taskId);
        if(attachment != null) {
            FileUtils.removeFile(attachment.getGeneratedPath(), attachment.getGeneratedName());
        }
        taskDao.remove(taskId);

        String path = req.getContextPath() + "/basket";
        resp.sendRedirect(path);
    }
}
