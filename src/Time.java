package ASTC;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;

public class Time extends JFrame {

    private int hours, minutes, seconds;
    private JLabel heading;
    private JLabel simulationClockLabel;
    private JLabel systemClockLabel;
    private Font font = new Font("", Font.BOLD, 35);
    private JPanel panel;

    public Time() {
        super.setTitle("Global Clock");
        simulationClockLabel = new JLabel("Simulation Clock");
        systemClockLabel = new JLabel("System Clock");
        simulationClockLabel.setFont(new Font("Verdana", Font.PLAIN, 27));
        systemClockLabel.setFont(new Font("Verdana", Font.PLAIN, 23));

        getContentPane().setLayout(new GridLayout(4, 1));
        
        panel = new JPanel();
        getContentPane().add(panel);
        heading = new JLabel("My clock");
        heading.setBackground(new Color(255, 255, 255));
        panel.add(heading);
        heading.setFont(new Font("Verdana", Font.PLAIN, 37));
        getContentPane().add(systemClockLabel);
        getContentPane().add(simulationClockLabel);

        JButton settimeButton = new JButton("Set Time");
        settimeButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JButton advancetimeButton = new JButton("Advance Time");
        advancetimeButton.setFont(new Font("Tahoma", Font.PLAIN, 14));

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
        getContentPane().add(buttonPanel);

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
        public void input() {
      int newHours,newMinutes,newSeconds;
      do{
         newHours = Integer.parseInt(JOptionPane.showInputDialog("Enter the hours:"));
      } while(newHours <0 || newHours >24);
      do{
         newMinutes = Integer.parseInt(JOptionPane.showInputDialog("Enter the Minutes:"));
      }while(newMinutes <0 || newMinutes >60);
      do{
         newSeconds = Integer.parseInt(JOptionPane.showInputDialog("Enter the Seconds:"));
      }while(newSeconds <0 || newSeconds >60);

        setTime(newHours, newMinutes, newSeconds);
        updateSimulationClock();
    }

    public void setSimulationTime() {
        input();
    }

    public void advanceSimulationTime() {
        input();
    }

    public void setTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public void advanceTime(int hours, int minutes, int seconds) {
        this.hours += hours;
        this.minutes += minutes;
        this.seconds += seconds;

        if (this.seconds >60){
            this.seconds %= 60;
            this.minutes++;
        }
        if (this.minutes>60){
            this.minutes %= 60;
            this.hours++;
        }
        if(this.hours>24){
            this.hours%=24;
        }

        updateSimulationClock();
    }

    public String getTime() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private String getSystemTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        return sdf.format(cal.getTime());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Time();
            }
        });
    }
}
