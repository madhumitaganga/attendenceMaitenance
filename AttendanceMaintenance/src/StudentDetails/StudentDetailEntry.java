package StudentDetails;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.Font;

import AttendanceApp.Menu;
import AttendanceApp.StartMenu;
import ConnectionToDB.sqliteConnection;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;
public class StudentDetailEntry {

	private JFrame frame;
	private JTextField txtUsn;
	private JTextField txtName;
	private JTextField txtSection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDetailEntry window = new StudentDetailEntry();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection=null;
	
	/**
	 * Create the application.
	 */
	public StudentDetailEntry() {
		initialize();
		connection=sqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 300, 900, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(0, 206, 209));
		panel.setBounds(0, 0, 264, 363);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAttendanceMaintenanceSystem = new JLabel("Attendance Maintenance System");
		lblAttendanceMaintenanceSystem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAttendanceMaintenanceSystem.setBounds(10, 127, 254, 31);
		panel.add(lblAttendanceMaintenanceSystem);
		
		JLabel lblEnterStudentDetails = new JLabel("Enter Student Details");
		lblEnterStudentDetails.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterStudentDetails.setBounds(51, 166, 175, 33);
		panel.add(lblEnterStudentDetails);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(264, 0, 722, 363);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblUsn = new JLabel("USN");
		lblUsn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsn.setBounds(309, 82, 74, 27);
		panel_1.add(lblUsn);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(309, 154, 74, 27);
		panel_1.add(lblName);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSection.setBounds(309, 228, 74, 27);
		panel_1.add(lblSection);
		
		txtUsn = new JTextField();
		txtUsn.setBounds(428, 82, 309, 27);
		panel_1.add(txtUsn);
		txtUsn.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(428, 154, 309, 27);
		panel_1.add(txtName);
		txtName.setColumns(10);
		
		txtSection = new JTextField();
		txtSection.setBounds(428, 228, 309, 27);
		panel_1.add(txtSection);
		txtSection.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Oops! Something went wrong");
					StartMenu.main(null);
				}
				frame.setVisible(false);
				Menu.main(null);
			}
		});
		btnBack.setBounds(791, 10, 85, 21);
		panel_1.add(btnBack);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query = "insert into StudentDetailTable (USN,Name,Section) values(?,?,?)";
				try {
					
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1,txtUsn.getText());
					pst.setString(2,txtName.getText());
					pst.setString(3,txtSection.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "Entry Successful!");
					try {
						connection.close();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Oops! Something went wrong");
						StartMenu.main(null);
					}
					frame.setVisible(false);
					Menu.main(null);
					
				} catch (Exception e1) {
					
					JOptionPane.showMessageDialog(null, e1);
					
				}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSubmit.setBounds(527, 297, 98, 27);
		panel_1.add(btnSubmit);
	}
}
