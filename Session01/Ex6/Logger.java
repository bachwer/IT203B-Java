package Session01.Ex6;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public static void logError(String message, Exception e){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = LocalDateTime.now().format(formatter);

        System.out.println("[ERROR]" + time + " - " + message);
        System.out.println("Chi tiết lỗi: " + e.getMessage());
    }
}
