package ASTC;
import javax.swing.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlaneData {

    public JFrame planeframe;
    private JTextField id;
    private int[] enteredids = new int[100];
    private int numberofid = 0;
    private String Starting_Country;
    private String Destination_Country;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PlaneData window = new PlaneData();
                    window.planeframe.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PlaneData() {
        initialize();
    }

    private void initialize() {
        planeframe = new JFrame();
        planeframe.setTitle("Airplane data");
        planeframe.getContentPane().setBackground(new Color(112, 128, 144));
        planeframe.setBounds(100, 100, 452, 403);
        planeframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        planeframe.getContentPane().setLayout(null);

        JComboBox comboBox = new JComboBox();
        comboBox.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Starting_Country = comboBox.getSelectedItem().toString();
        	}
        });
        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select country:", "Pakistan", "India", "China", "Iran", "Afghanistan", "Russia"}));
        comboBox.setBounds(199, 129, 137, 22);
        planeframe.getContentPane().add(comboBox);

        JLabel lblStart = new JLabel("Start");
        lblStart.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblStart.setBounds(44, 135, 92, 21);
        planeframe.getContentPane().add(lblStart);

        JLabel lblDestination = new JLabel("Destination");
        lblDestination.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblDestination.setBounds(44, 186, 92, 21);
        planeframe.getContentPane().add(lblDestination);

        JComboBox comboBox_1 = new JComboBox();
        comboBox_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Destination_Country = comboBox_1.getSelectedItem().toString();
        	}
        });
        comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Select country:", "Pakistan", "India", "China", "Iran", "Afghanistan", "Russia"}));
        comboBox_1.setBounds(199, 185, 137, 22);
        planeframe.getContentPane().add(comboBox_1);

        JButton btnNewButton = new JButton("Submit");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ide = id.getText();
                int idvalue = Integer.parseInt(ide);
                if (!isIDRepeated(idvalue)) {
                    System.out.println("The entered ID is:" + idvalue);
                    System.out.println("The starting country:" + Starting_Country);
                    System.out.println("The destination country:" + Destination_Country);
                    enteredids[numberofid] = idvalue;
                    numberofid++;
                    DesiredPlane obj2 = new DesiredPlane();
                    obj2.setCountries(Starting_Country,Destination_Country);
                    obj2.desired.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "ID Already exists");
                }
            }

            boolean isIDRepeated(int newID) {
                for (int i = 0; i < numberofid; i++) {
                    if (enteredids[i] == newID) {
                        return true;
                    }
                }
                return false;
            }
        });

        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnNewButton.setBounds(150, 281, 137, 33);
        planeframe.getContentPane().add(btnNewButton);

        JLabel lblAirplaneId = new JLabel("Airplane ID");
        lblAirplaneId.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblAirplaneId.setBounds(44, 82, 92, 21);
        planeframe.getContentPane().add(lblAirplaneId);

        id = new JTextField();
        id.setBounds(199, 74, 137, 30);
        planeframe.getContentPane().add(id);
        id.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(0, 0, 436, 364);
        planeframe.getContentPane().add(lblNewLabel);
        ImageIcon  icon = new ImageIcon(this.getClass().getResource(""));
		lblNewLabel.setIcon(icon);
    }
}
