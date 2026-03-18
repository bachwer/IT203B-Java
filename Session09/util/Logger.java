package Session09.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static String getTime(){
        return LocalTime.now().format(formatter);
    }

    public static void info(String message) {
        System.out.println("[" + getTime() + "] [INFO] " + message);
    }

    public static void warning(String message){
        System.out.println("[" + getTime() + "] [WARNING] " + message);

    }
    public static void error(String message){
        System.out.println("[" + getTime() + "] [ERROR] " + message);
    }
}
