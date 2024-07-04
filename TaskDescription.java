package ASTC;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.Color;

public class TaskDescription {
	Task obj;
    public JFrame Taskobject;
    public JLabel globalClockLabel;
    public JLabel timeLabel;
    public JLabel creationTimeLabel;
    public JLabel executionTimeLabel;
    public Currentclock c;
    public SystemClock clk;
    public SystemTime tim;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TaskDescription window = new TaskDescription();
                    window.Taskobject.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TaskDescription() {
    	
        initialize();
        clk = new SystemClock();
        tim = new SystemTime();
        c = new Currentclock();
        c.start(); 
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
    }

    private void initialize() {
        Taskobject = new JFrame();
        Taskobject.getContentPane().setFont(new Font("Verdana", Font.PLAIN, 11));
        Taskobject.getContentPane().setBackground(new Color(255, 255, 255));
        Taskobject.setBounds(100, 100, 520, 457);
        Taskobject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Taskobject.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Task Description");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(176, 32, 157, 27);
        Taskobject.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Global Clock");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(50, 97, 96, 20);
        Taskobject.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Creation Time");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(50, 149, 96, 20);
        Taskobject.getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Execution Time");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_2.setBounds(50, 201, 114, 20);
        Taskobject.getContentPane().add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_3 = new JLabel("Status");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_3.setBounds(50, 254, 96, 20);
        Taskobject.getContentPane().add(lblNewLabel_1_3);

        JLabel lblNewLabel_1_4 = new JLabel("Task Type");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_4.setBounds(50, 304, 96, 20);
        Taskobject.getContentPane().add(lblNewLabel_1_4);

        timeLabel = new JLabel("");
        timeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        timeLabel.setBounds(256, 97, 127, 20);
        Taskobject.getContentPane().add(timeLabel);

        creationTimeLabel = new JLabel("11:37:45");
        creationTimeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        creationTimeLabel.setBounds(256, 149, 127, 20);
        Taskobject.getContentPane().add(creationTimeLabel);

        executionTimeLabel = new JLabel("");
        executionTimeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        executionTimeLabel.setBounds(256, 201, 127, 20);
        Taskobject.getContentPane().add(executionTimeLabel);
        ImageIcon icon = new ImageIcon(this.getClass().getResource(""));
        
        JLabel taskstatus = new JLabel("Incomplete");
        taskstatus.setFont(new Font("Tahoma", Font.BOLD, 14));
        taskstatus.setBounds(256, 254, 127, 20);
        Taskobject.getContentPane().add(taskstatus);
         
       
        
        JLabel Tasktypelbl = new JLabel(obj.getSecondaryTaskLabel());
        Tasktypelbl.setFont(new Font("Tahoma", Font.BOLD, 14));
        Tasktypelbl.setBounds(256, 309, 127, 20);
        Taskobject.getContentPane().add(Tasktypelbl);
        ImageIcon icon1 = new ImageIcon(this.getClass().getResource(""));
    }

    public void updateTime() {
        timeLabel.setText(clk.getSystemTime());
        executionTimeLabel.setText(c.getCurrentTime().toString());
        creationTimeLabel.setText(tim.getCurrentTime());
    }
}