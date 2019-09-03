package controller.web.deletion;

import dao.TaskDao;
import dao.impl.TaskDaoImpl;
import model.Task;
import util.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete_task/*")
public class DeleteAttachment extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfo = req.getPathInfo().split("/");
        Long taskId = Long.valueOf(pathInfo[1]);

        Task task = taskDao.getOne(taskId);
        if (task.getOriginalFileName() != null) {
            FileUtils.removeFile(task.getGeneratedFilePath(), task.getGeneratedFileName());
            taskDao.removeAttachment(task.getId());
        }

        String path = req.getContextPath() + "/allTask";
        resp.sendRedirect(path);
    }
}
