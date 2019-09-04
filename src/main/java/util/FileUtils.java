package util;

import model.Task;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import java.util.UUID;

public class FileUtils {

    public final static String UPLOAD_PATH = "D:/Program/MyStudy/TodoListServlet/src/upload";

    private static String getLetter() {
        return String.valueOf((char) (new Random().nextInt((122 - 97) + 1) + 97));
    }

    public static String getPath() {
        StringBuilder path = new StringBuilder("/");
        for (int i = 0; i < 10; i++) {
            path.append(getLetter()).append("/");
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
}
