package ASTC;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SystemTime {
    public static void main(String[] args) {

        String formattedTime = getCurrentTime();

        System.out.println("Current Time: " + formattedTime);
    }

    public static String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return currentTime.format(formatter);
    }
}
