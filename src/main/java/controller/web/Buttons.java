package controller.web;

import dao.TaskDao;
import dao.UserDao;
import dao.impl.TaskDaoImpl;
import dao.impl.UserDaoImpl;
import model.State;
import model.Task;
import model.User;
import util.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@WebServlet("/button")
public class Buttons extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String check = req.getParameter("command");
        Task task = new Task.Builder().build();
        if (req.getParameter("id") != null) {
            Long idTask = Long.valueOf(req.getParameter("id"));
            task = taskDao.getOne(idTask);
        }

        if(check == null) {
            check = "DEFAULT";
        }

        switch (check) {
            case "MARK_COMPLETE":
                markComplete(req, resp, task);
                break;
            case "MARK_DELETE":
                markDelete(req, resp, task);
                break;
            case "MARK_ACTUAL":
                markActual(req, resp, task);
                break;
            case "DELETE_FILE":
                deleteFile(req, resp, task);
                break;
            case "DELETE_TASK":
                deleteTask(req, resp, task);
                break;
            case "DELETE_ALL":
                deleteAllFromBasket(req, resp);
                break;
            case "DOWNLOAD_FILE":
                downloadFile(req, resp, task);
                break;
            case "SIGN_OUT":
                signOut(req, resp);
                break;
            case "USER_DELETE":
                userDelete(req, resp);
                break;
        }
    }

    private void userDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");
        List<Task> tasks = taskDao.findAllByUser(userSession.getId());
        for (Task task : tasks) {
            if (task.getOriginalFileName() != null && !task.getOriginalFileName().isEmpty()) {
                FileUtils.removeFile(task.getGeneratedFilePath(), task.getGeneratedFileName());
            }
        }
        taskDao.removeAllByUser(userSession.getId());
        userDao.remove(userSession.getId());

        String path = req.getContextPath() + "/login";
        resp.sendRedirect(path);
    }

    private void downloadFile(HttpServletRequest req, HttpServletResponse resp, Task task) throws IOException {
        FileUtils.downloadFile(task, resp, getServletContext());
    }

    private void markComplete(HttpServletRequest req, HttpServletResponse resp, Task task) throws IOException {
        boolean isComplete = task.getState().equals(State.COMPLETE);
        if(!isComplete) {
            taskDao.markAsComplete(task);
        }
        String path = req.getContextPath() + "/allTask";
        resp.sendRedirect(path);
    }

    private void markActual(HttpServletRequest req, HttpServletResponse resp, Task task) throws IOException {
        boolean notActual = task.getState().equals(State.ACTUAL);
        if(!notActual) {
            taskDao.markAsActual(task);
        }
        String path = req.getContextPath() + "/allTask";
        resp.sendRedirect(path);
    }

    private void markDelete(HttpServletRequest req, HttpServletResponse resp, Task task) throws IOException {
        boolean isDeleted = task.getState().equals(State.DELETE);
        if(!isDeleted) {
            taskDao.markAsDeleted(task);
        }
        String path = req.getContextPath() + "/allTask";
        resp.sendRedirect(path);
    }

    private void deleteTask(HttpServletRequest req, HttpServletResponse resp, Task task) throws IOException {
        FileUtils.removeFile(task.getGeneratedFilePath(), task.getGeneratedFileName());
        taskDao.remove(task.getId());

        String path = req.getContextPath() + "/basket";
        resp.sendRedirect(path);
    }

    private void deleteFile(HttpServletRequest req, HttpServletResponse resp, Task task) throws IOException {
        if (task.getOriginalFileName() != null) {
            FileUtils.removeFile(task.getGeneratedFilePath(), task.getGeneratedFileName());
            taskDao.removeAttachment(task.getId());
        }
        String path = req.getContextPath() + "/allTask";
        resp.sendRedirect(path);
    }

    private void deleteAllFromBasket(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");
        Iterable<Task> tasks = taskDao.findAllByBasket(userSession.getId());

        for (Task task : tasks) {
            if (task.getOriginalFileName() != null) {
                FileUtils.removeFile(task.getGeneratedFilePath(), task.getGeneratedFileName());
            }
        }
        taskDao.removeAllFromBasket(userSession.getId());

        String path = req.getContextPath() + "/basket";
        resp.sendRedirect(path);
    }

    private void signOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");

        String path = req.getContextPath() + "/login";
        resp.sendRedirect(path);
    }

}
