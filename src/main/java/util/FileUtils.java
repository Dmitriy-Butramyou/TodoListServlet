package util;

import model.Task;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.Part;
import javax.xml.ws.Response;
import java.io.*;
import java.util.Random;
import java.util.UUID;

public class FileUtils {

//    private final static String UPLOAD_PATH = "D:/Program/MyStudy/TodoListServlet/src/upload";
    private final static String UPLOAD_PATH = "C:/TodoListServlet/upload";

    private static String getLetter() {
        return String.valueOf((char) (new Random().nextInt((122 - 97) + 1) + 97));
    }

    private static String getPath() {
        StringBuilder path = new StringBuilder(File.separator);
        for (int i = 0; i < 5; i++) {
            path.append(getLetter()).append(File.separator);
        }
        return path.toString();
    }

    public static boolean removeFile(String filePath, String generatedFileName) {
        File file = new File(filePath + generatedFileName);
        if(file.exists()) {
            return file.delete();
        }
        return false;
    }
    public static Task uploadAttachment(Part attachment, Task task) throws IOException {
        if (attachment.getSize() > 0) {
            InputStream inputStream = attachment.getInputStream();

            //генерируем уникальный путь
            String generatedPath = FileUtils.UPLOAD_PATH + FileUtils.getPath();
            File uploadDir = new File(generatedPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            // генерируем уникальное имя
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + attachment.getSubmittedFileName();

            File file = new File(generatedPath, resultFileName);
            FileOutputStream outputStream = new FileOutputStream(file);

            //записываем потоком байт
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            //завершаем поток
            outputStream.close();

            //записываем в БД
            task.setOriginalFileName(attachment.getSubmittedFileName());
            task.setGeneratedFileName(resultFileName);
            task.setGeneratedFilePath(generatedPath);
        }
        return task;
    }

    public static void downloadFile(Task task, HttpServletResponse resp, ServletContext servletContext) throws IOException {
        File file = new File(task.getGeneratedFilePath() + File.separator + task.getGeneratedFileName());

        FileInputStream inputStream = new FileInputStream(file);

        String mimeType = servletContext.getMimeType(task.getOriginalFileName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        resp.setContentType(mimeType);
        resp.setContentLength((int) file.length());

        // forces download
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", task.getOriginalFileName());
        resp.setHeader(headerKey, headerValue);

        // obtains response's output stream
        OutputStream outStream = resp.getOutputStream();

        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();
    }

}
