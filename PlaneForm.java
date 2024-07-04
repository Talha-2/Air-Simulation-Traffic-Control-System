package ASTC;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlaneForm {
	Task_Engine obj;
	public JFrame planeobject;
	private JTextField planes;
	private JLabel lblNewLabel_1;
	private JLabel lblTaskId;
	private JTextField taskid;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaneForm window = new PlaneForm();
					window.planeobject.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PlaneForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		planeobject = new JFrame();
		planeobject.setTitle("Planes Input");
		planeobject.getContentPane().setBackground(new Color(255, 255, 255));
		planeobject.setBounds(100, 100, 337, 359);
		planeobject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		planeobject.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Planes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(28, 72, 69, 14);
		planeobject.getContentPane().add(lblNewLabel);
		
		planes = new JTextField();
		planes.setBounds(124, 71, 86, 20);
		planeobject.getContentPane().add(planes);
		planes.setColumns(10);
		
		JButton btnNewButton = new JButton("Done");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String plane = planes.getText();
				int planevalue = Integer.parseInt(plane);
			 System.out.print(planevalue);
			 PlaneData d = new PlaneData();
			 d.planeframe.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(121, 128, 89, 23);
		planeobject.getContentPane().add(btnNewButton);
		
		lblNewLabel_1 = new JLabel("TASK INFORMATION");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(74, 162, 149, 23);
		planeobject.getContentPane().add(lblNewLabel_1);
		
		lblTaskId = new JLabel("Task ID");
		lblTaskId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTaskId.setBounds(28, 222, 69, 14);
		
		
		planeobject.getContentPane().add(lblTaskId);
		
		taskid = new JTextField();
		taskid.setColumns(10);
		taskid.setBounds(124, 221, 86, 20);
		planeobject.getContentPane().add(taskid);
		String enteredTaskId = taskid.getText();
	
		

		
		lblNewLabel_2 = new JLabel("PLANE INFORMATION");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(74, 11, 158, 23);
		planeobject.getContentPane().add(lblNewLabel_2);
		
		JButton btnProceed = new JButton("Proceed");
		btnProceed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String taskide = taskid.getText();
				int taskkiid = Integer.parseInt(taskide);
				TaskDescription f = new TaskDescription();
				f.Taskobject.setVisible(true);
				System.out.println("The ID of the Task is:"+ taskkiid);
			}
		});
		btnProceed.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnProceed.setBounds(121, 275, 89, 23);
		planeobject.getContentPane().add(btnProceed);
		
		lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setBounds(321, 320, 0, 0);
		planeobject.getContentPane().add(lblNewLabel_3);
		ImageIcon  icon = new ImageIcon(this.getClass().getResource(""));
		lblNewLabel_3.setIcon(icon);
		
	}
}