package util;

import java.io.File;
import java.util.Random;

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
}
