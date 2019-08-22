package controller.web;

import dao.AttachmentDao;
import dao.impl.AttachmentDaoImpl;
import dao.impl.TaskDaoImpl;
import model.Attachment;
import model.State;
import model.Task;
import model.User;
import util.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@WebServlet("/addTask")
@MultipartConfig
public class AddTask extends HttpServlet {

    private TaskDaoImpl taskDao = new TaskDaoImpl();
    private AttachmentDao attachmentDao = new AttachmentDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute("user");
        req.setAttribute("name", "Please login");
        if(userSession != null) {
            req.setAttribute("name", userSession.getName());
            req.setAttribute("user", userSession.getId());
        }
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
        Part attachment = req.getPart("attachment");

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

            Task task = new Task(nameTask, description, deadlineTime.getTime(),
                    deadlineTime.getTime(), State.ACTUAL, userId);

            task = taskDao.save(task);

            if(attachment.getSize() > 0) {
                InputStream inputStream = attachment.getInputStream();

                // gets absolute path of the web application
//                String appPath = request.getServletContext().getRealPath("");
                // constructs path of the directory to save uploaded file
//                String savePath = appPath + File.separator + SAVE_DIR;


                //генерируем уникальный путь
                String generatedPath = FileUtils.UPLOAD_PATH + FileUtils.getPath();
                File uploadDir = new File(generatedPath);
                if(!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                // генерируем уникальное имя
                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + attachment.getSubmittedFileName();

                File file = new File(generatedPath, resultFileName);
                FileOutputStream outputStream = new FileOutputStream(file);

                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                outputStream.close();

                Attachment newAttachment = new Attachment(task.getId(), attachment.getSubmittedFileName(),
                        resultFileName, generatedPath);
                attachmentDao.save(newAttachment);
            }

            resp.sendRedirect("/allTask");
        }
    }
}
