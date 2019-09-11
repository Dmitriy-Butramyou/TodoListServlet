package controller.web;

import dao.impl.TaskDaoImpl;
import model.State;
import model.Task;
import model.User;
import util.DateUtil;
import util.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/addTask")
@MultipartConfig
public class AddTask extends HttpServlet {

    private TaskDaoImpl taskDao = new TaskDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");


        if (userSession != null) {
            req.setAttribute("name", userSession.getName());
            req.setAttribute("user", userSession.getId());
            req.getRequestDispatcher("view/addTask.jsp")
                    .forward(req, resp);
        } else {
            String path = req.getContextPath() + "/login";
            resp.sendRedirect(path);
        }

    }

    /**
     * Добавление нового задания
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String nameTask = req.getParameter("nameTask");
        String description = req.getParameter("description");
        String eventDate = req.getParameter("eventDate");
        Long userId = Long.valueOf(req.getParameter("userId"));
        Part attachment = req.getPart("attachment");

        if (nameTask != null && !nameTask.trim().isEmpty() && description != null && !description.trim().isEmpty()) {

//             TODO: 05.08.2019 сегодняшняя дата для проверки валидности деадлайна.
            Date nowTime = DateUtil.setTimeToMidnight(new Date());

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
// TODO: 03.09.2019 разобраться с датами
            Task task = new Task.Builder()
                    .name(nameTask)
                    .description(description)
                    .eventDate(deadlineTime)
                    .creationDateTime(nowTime)
                    .state(State.ACTUAL)
                    .userId(userId)
                    .build();


            //записываем файл
            task = FileUtils.uploadAttachment(attachment, task);

            taskDao.save(task);
            resp.sendRedirect("/allTask");
        }
    }

}
