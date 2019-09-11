package controller.web;

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

@WebServlet("/download/*")
public class DownloadFile extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathInfo = req.getPathInfo().split("/");
        Long taskId = Long.valueOf(pathInfo[1]);

        Task task = taskDao.getOne(taskId);

        FileUtils.downloadFile(task, resp, getServletContext());


    }
}
