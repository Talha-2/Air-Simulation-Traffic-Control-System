package ASTC;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

public class SystemClock extends JFrame {

    private int hours, minutes, seconds;
    private JLabel heading;
    private JLabel simulationClockLabel;
    private JLabel systemClockLabel;
    private Font font = new Font("", Font.BOLD, 35);

    public void CurrentClock() {
        super.setTitle("Global Clock");
        heading = new JLabel("My clock");
        simulationClockLabel = new JLabel("Simulation Clock");
        systemClockLabel = new JLabel("System Clock");
        heading.setFont(font);
        simulationClockLabel.setFont(font);
        systemClockLabel.setFont(font);

        this.setLayout(new GridLayout(4, 1));
        this.add(heading);
        this.add(systemClockLabel);
        this.add(simulationClockLabel);

        JButton settimeButton = new JButton("Set Time");
        JButton advancetimeButton = new JButton("Advance Time");

        settimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setSimulationTime();
            }
        });
        advancetimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                advanceSimulationTime();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(settimeButton);
        buttonPanel.add(advancetimeButton);
        this.add(buttonPanel);

        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        updateSystemClock();

        Thread clockThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                updateSimulationClock();
                                updateSystemClock();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        clockThread.start();
    }

    private void updateSystemClock() {
        systemClockLabel.setText("System Clock: " + getSystemTime());
    }

    private void updateSimulationClock() {
        simulationClockLabel.setText("Simulation Clock: " + getTime());
    }

    private void input() {
        int newHours, newMinutes, newSeconds;
        do {
            newHours = Integer.parseInt(JOptionPane.showInputDialog("Enter the hours:"));
        } while (newHours < 0 || newHours > 24);
        do {
            newMinutes = Integer.parseInt(JOptionPane.showInputDialog("Enter the Minutes:"));
        } while (newMinutes < 0 || newMinutes > 60);
        do {
            newSeconds = Integer.parseInt(JOptionPane.showInputDialog("Enter the Seconds:"));
        } while (newSeconds < 0 || newSeconds > 60);

        setTime(newHours, newMinutes, newSeconds);
        updateSimulationClock();
    }

    public void setSimulationTime() {
        input();
    }

    public void advanceSimulationTime() {
        input();
    }

    private void setTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void advanceTime(int hours, int minutes, int seconds) {
        this.hours += hours;
        this.minutes += minutes;
        this.seconds += seconds;

        if (this.seconds > 60) {
            this.seconds %= 60;
            this.minutes++;
        }
        if (this.minutes > 60) {
            this.minutes %= 60;
            this.hours++;
        }
        if (this.hours > 24) {
            this.hours %= 24;
        }

        updateSimulationClock();
    }

    private String getTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    String getSystemTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }
     String getStaticSystemTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    private void setTimeFromSystemClock() {
        Calendar cal = Calendar.getInstance();
        setTime(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Currentclock();
            }
        });
    }
}
