package ASTC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DesiredPlane {

    public JFrame desired;
    private String startingCountry;
    private String destinationCountry;
    public Currentclock a;
    

    private JLabel pathLabel;
    private JLabel costLabel;
    private JLabel timelbl;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DesiredPlane window = new DesiredPlane();
                    window.desired.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DesiredPlane() {
        initialize();
        a = new Currentclock();
        a.start(); 
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
    }

    private void initialize() {
        desired = new JFrame();
        desired.getContentPane().setBackground(new Color(255, 255, 255));
        desired.setTitle("Info panel");
        desired.setBounds(100, 100, 530, 439);
        desired.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        desired.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("AIRPLANE INFORMATION");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNewLabel.setBounds(111, 26, 228, 39);
        desired.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Timer:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(47, 111, 78, 22);
        desired.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Path:");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_1.setBounds(47, 182, 78, 22);
        desired.getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_2 = new JLabel("Cost:");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_2.setBounds(47, 240, 78, 22);
        desired.getContentPane().add(lblNewLabel_1_2);

        JLabel lblNewLabel_1_4 = new JLabel("Current Position");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1_4.setBounds(47, 301, 125, 22);
        desired.getContentPane().add(lblNewLabel_1_4);

        timelbl = new JLabel("00:00:00");
        timelbl.setFont(new Font("Tahoma", Font.BOLD, 13));
        timelbl.setBounds(264, 109, 100, 20);
        desired.getContentPane().add(timelbl);

        pathLabel = new JLabel("");
        pathLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        pathLabel.setBounds(167, 183, 305, 20);
        desired.getContentPane().add(pathLabel);

        costLabel = new JLabel("");
        costLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        costLabel.setBounds(239, 235, 100, 20);
        desired.getContentPane().add(costLabel);
        ImageIcon  icon = new ImageIcon(this.getClass().getResource(""));
		
		JLabel Destinationlbl = new JLabel("");
		Destinationlbl.setFont(new Font("Tahoma", Font.BOLD, 15));
		Destinationlbl.setBounds(264, 266, 100, 20);
		desired.getContentPane().add(Destinationlbl);
		Destinationlbl.setText(destinationCountry);
		
		JLabel Destinationlbl_1 = new JLabel("Runway");
		Destinationlbl_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		Destinationlbl_1.setBounds(264, 302, 100, 20);
		desired.getContentPane().add(Destinationlbl_1);
		ImageIcon  icon1 = new ImageIcon(this.getClass().getResource(""));
    }

    public void setCountries(String start, String end) {
        this.startingCountry = start;
        this.destinationCountry = end;
        System.out.println("Starting Country: " + startingCountry);
        System.out.println("Destination Country: " + destinationCountry);

      
        findShortestPath(startingCountry, destinationCountry);
    }

    private void findShortestPath(String startingCountry, String destinationCountry) {
        PathFinder.Node[] nodes = PathFinder.createNodes();
        PathFinder.Node startNode = PathFinder.findNode(nodes, startingCountry);
        PathFinder.Node endNode = PathFinder.findNode(nodes, destinationCountry);

        if (startNode != null && endNode != null) {
            PathFinder.dijkstra(nodes, startNode, endNode);

       
            PathFinder.Node destinationNode = PathFinder.findNode(nodes, destinationCountry);
            if (destinationNode != null && destinationNode.distance != Integer.MAX_VALUE) {
                pathLabel.setText("Shortest path: " + buildPathText(destinationNode.shortestPath));
                costLabel.setText("Cost: " + destinationNode.distance + "$");
            } else {
          
                pathLabel.setText("Invalid destination node.");
                costLabel.setText("");
            }
        } else {
       
            pathLabel.setText("Invalid source or destination point.");
            costLabel.setText("");
        }
    }

    private String buildPathText(List<PathFinder.Node> path) {
        StringBuilder pathText = new StringBuilder();
        for (int k = 0; k < path.size(); k++) {
            pathText.append(path.get(k).name).append("- ");
        }
        return pathText.toString();
    }

    public void updateTime() {
        timelbl.setText(a.getCurrentTime().toString());
    }
}
