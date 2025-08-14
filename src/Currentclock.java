package ASTC;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Currentclock extends Thread {
    private LocalTime currentTime;

    // Constructor
    public Currentclock() {
        this.currentTime = LocalTime.MIDNIGHT;
    }

    public static LocalTime GetGlobalTime() {
        return LocalTime.now();
    }
    

    public void advanceClock(int hours, int minutes, int seconds) {
        this.currentTime = this.currentTime.plusHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds);
    }

    public void setClock(int hours, int minutes, int seconds) {
        this.currentTime = LocalTime.of(hours, minutes, seconds);
    }

    public LocalTime getCurrentTime() {
        return this.currentTime;
    }

    @Override
    public void run() {
        for (;;) {
            advanceClock(0, 0, 1);

            LocalTime currentThreadTime = getCurrentTime();

            String formattedTime = currentThreadTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
