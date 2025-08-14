package ASTC;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Login_page {

	private JFrame frmLogin;
	private JTextField usern;
	private JPasswordField passn;
	private int loginAttempts = 0;
	  static int airplane_id;

	/**
	 * Launch the application.
	 */   public static int generate_airplane_id() {
	        
				return airplane_id++;
		
	    }
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_page window = new Login_page();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	  
	        Time global_time = new Time();

	        List<Task> tasks = new ArrayList<>();
	        tasks.add(new Task("Airplane", "Landing", 1, "0", generate_airplane_id(), 0, 1));
	        tasks.add(new Task("Airplane", "parking", 2, "0", generate_airplane_id(), 0, 1));
	        tasks.add(new Task("Airplane", "Departure", 3, global_time.getTime(), generate_airplane_id(), 3, 10));
	        tasks.add(new Task("PathFinder", "Exploration", 4, "0", generate_airplane_id(), 0, 5));
	        tasks.add(new Task("PathFinder", "Exploration", 5, "0", generate_airplane_id(), 0, 5));

	        Task_Engine[] taskEngines = new Task_Engine[tasks.size()];
	        for (int i = 0; i < tasks.size(); i++) {
	            taskEngines[i] = new Task_Engine(tasks.get(i), global_time);
	         
	        }

           
        for (int i = 0; i < taskEngines.length; i++) {
        	taskEngines[i].start();
            while (taskEngines[i].isAlive()) {
                try {
                  
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Wait for all Task_Engine threads to finish
        for (Task_Engine engine : taskEngines) {
            try {
                engine.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        
        taskEngines[0].display_list(); 
    
    
	
	
	 
	}

	/**
	 * Create the application.
	 */
	public Login_page() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.getContentPane().setBackground(new Color(0, 0, 0));
		frmLogin.setBounds(100, 100, 633, 448);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 597, 387);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		usern = new JTextField();
		usern.setBounds(252, 146, 151, 26);
		panel.add(usern);
		usern.setColumns(10);
		
		passn = new JPasswordField();
		passn.setBounds(252, 214, 151, 26);
		panel.add(passn);
		
	   JLabel user = new JLabel("Username");
		user.setFont(new Font("Times New Roman", Font.BOLD, 18));
		user.setBounds(101, 152, 93, 14);
		panel.add(user);
		
		 JLabel pass = new JLabel("Password");
		pass.setFont(new Font("Times New Roman", Font.BOLD, 18));
		pass.setBounds(101, 218, 93, 14);
		panel.add(pass);
		
		JLabel lblNewLabel_2 = new JLabel("LOGIN PAGE");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(252, 71, 151, 32);
		panel.add(lblNewLabel_2);
		
		 JButton btnNewButton = new JButton("Login");
	        btnNewButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String un = usern.getText();
	                String pas = passn.getText();
	                
	                if (un.equals("wahab")||un.equals("talha") && pas.equals("123")) {
	                    JOptionPane.showMessageDialog(null, "Login Successful");
	                    PlaneForm obj1 = new PlaneForm();
	                    obj1.planeobject.setVisible(true);
	                    loginAttempts = 0;
	                } else {
	                    loginAttempts++;
	                    if (loginAttempts >= 3) {
	                        JOptionPane.showMessageDialog(null, "Too many incorrect attempts. Closing the form.");
	                        frmLogin.dispose(); 
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Incorrect Username or Password. Attempt " + loginAttempts + "/3");
	                    }
	                }
	            }
	        });
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 13));
		btnNewButton.setBounds(290, 291, 89, 23);
		panel.add(btnNewButton);
		ImageIcon  icon = new ImageIcon(this.getClass().getResource(""));
	}
}
