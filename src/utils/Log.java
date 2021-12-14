package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    public static String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(formatter);
    }

    private static void baseLog(String str, String category) {
        System.out.println("[ " + category + "\t" + getTime() + " ] " + str);
    }

    public static void info(String str) {
        baseLog(str, "Info");
    }

    public static void warn(String str) {
        baseLog(str, "Warn");
    }

    public static void error(String str) {
        baseLog(str, "ERROR");
    }
}
