package controller.web;

import dao.impl.TaskDaoImpl;
import model.State;
import model.Task;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/addTask")
public class AddTask extends HttpServlet {

    private TaskDaoImpl taskDao = new TaskDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");
        req.setAttribute("user", sessionUser.getId());
        req.getRequestDispatcher("view/addTask.jsp")
                .forward(req, resp);
    }

    /**
     * Добавление нового задания
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String nameTask = req.getParameter("nameTask");
        String description = req.getParameter("description");
        String eventDate = req.getParameter("eventDate");
        Long userId = Long.valueOf(req.getParameter("userId"));

        resp.setCharacterEncoding("UTF-8");
        if (nameTask != null && !nameTask.trim().isEmpty() && description != null && !description.trim().isEmpty()) {

//             TODO: 05.08.2019 сегодняшняя дата для проверки валидности деадлайна.
//            Date nowTime = DateUtil.setTimeToMidnight(new Date());

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date deadlineTime = null;
            try {
                deadlineTime = dateFormat.parse("2000-01-01");
                if (!eventDate.isEmpty()) {
                    deadlineTime = dateFormat.parse(eventDate);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

            Task task = new Task();
            task.setName(nameTask);
            task.setDescription(description);
            task.setEventDate(deadlineTime.getTime());
            task.setCreationDateTime(deadlineTime.getTime());
            task.setState(State.ACTUAL);
            task.setUserId(userId);

            taskDao.save(task);

            resp.sendRedirect("/allTask");
        }
    }
}
