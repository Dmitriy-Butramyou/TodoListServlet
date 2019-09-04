package controller.web;

import dao.TaskDao;
import dao.impl.TaskDaoImpl;
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
import java.util.List;

@WebServlet("/allTask")
@MultipartConfig
public class TaskShowServlet extends HttpServlet {

    private TaskDao taskDao = new TaskDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");


        if (userSession != null) {
            String day = req.getParameter("day");
            String deadline = req.getParameter("deadline");
            List<Task> tasks = taskDao.findAllByUser(userSession.getId());

            Long nowTime = DateUtil.setTimeToMidnight(new Date()).getTime();
            String location = "All task";

            if(day != null && !day.isEmpty()) {
                switch (day) {
                    case "Today":
                        // TODO: 04.09.2019  запрос к БД
                        location = "Tasks for today";
                        break;
                    case "Tomorrow":
                        // TODO: 04.09.2019  запрос к БД
                        nowTime = nowTime + DateUtil.ONE_DAY;
                        location = "Tasks for tomorrow";
                        break;
                    case "Deadline Missing":
                        // TODO: 04.09.2019  запрос к БД
                        location = "Tasks with a missed deadline";
                        break;
                }
            }
            if(deadline != null && !deadline.isEmpty()) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date deadlineTime = dateFormat.parse(deadline);
                    // TODO: 04.09.2019  запрос к БД
                    location = "Tasks for " + deadline;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }



            req.setAttribute("location", location);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        Part attachment = req.getPart("attachment");
        Long taskId = Long.valueOf(req.getParameter("taskId"));

        resp.setCharacterEncoding("UTF-8");

        Task task = taskDao.getOne(taskId);
        taskDao.save(FileUtils.uploadAttachment(attachment, task));
        resp.sendRedirect("/allTask");

    }
}
