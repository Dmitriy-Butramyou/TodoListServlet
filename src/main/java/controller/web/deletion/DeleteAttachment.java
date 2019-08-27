package controller.web.deletion;

import dao.AttachmentDao;
import dao.impl.AttachmentDaoImpl;
import model.Attachment;
import util.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete_task/*")
public class DeleteAttachment extends HttpServlet {

    private AttachmentDao attachmentDao = new AttachmentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfo = req.getPathInfo().split("/");
        Long taskId = Long.valueOf(pathInfo[1]);

        Attachment attachment = attachmentDao.getOne(taskId);
        if(attachment != null) {
            FileUtils.removeFile(attachment.getGeneratedPath(), attachment.getGeneratedName());
            attachmentDao.remove(attachment.getId());
        }

        String path = req.getContextPath() + "/allTask";
        resp.sendRedirect(path);
    }
}
