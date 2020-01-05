package enterAttendance;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import AttendanceApp.Menu;
import AttendanceApp.StartMenu;
import ConnectionToDB.sqliteConnection;

import javax.swing.JButton;

public class EnterAttendanceMenu {

	private JFrame frame;
	private JTextField txtSubject;
	private JTextField txtSection;
	public static String tableName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterAttendanceMenu window = new EnterAttendanceMenu();
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
	public EnterAttendanceMenu() {
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
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(264, 0, 722, 363);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		Panel panel = new Panel();
		panel_1.add(panel);
		panel.setBackground(new Color(0, 206, 209));
		panel.setBounds(0, 0, 264, 363);
		panel.setLayout(null);
		
		JLabel lblAttendanceMaintenanceSystem = new JLabel("Attendance Maintenance System");
		lblAttendanceMaintenanceSystem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAttendanceMaintenanceSystem.setBounds(10, 127, 254, 31);
		panel.add(lblAttendanceMaintenanceSystem);
		
		JLabel lblEnterStudentDetails = new JLabel("Choose Attendance Options");
		lblEnterStudentDetails.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterStudentDetails.setBounds(20, 168, 211, 33);
		panel.add(lblEnterStudentDetails);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubject.setBounds(349, 109, 96, 28);
		panel_1.add(lblSubject);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSection.setBounds(349, 199, 85, 28);
		panel_1.add(lblSection);
		
		txtSubject = new JTextField();
		txtSubject.setBounds(497, 112, 227, 28);
		panel_1.add(txtSubject);
		txtSubject.setColumns(10);
		
		txtSection = new JTextField();
		txtSection.setBounds(497, 202, 227, 28);
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
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tableName=txtSubject.getText()+"in"+txtSection.getText();
				String query="Select usn from "+tableName;
				try {
					PreparedStatement pst1=connection.prepareStatement(query);
					ResultSet rs=pst1.executeQuery();
					try {
						connection.close();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Oops! Something went wrong");
						StartMenu.main(null);
					}
					frame.setVisible(false);
					EnterDate.main(null);
				}catch(Exception e2) {
					
					JOptionPane.showMessageDialog(null, "Invalid Subject and Section, Check Before Entering!");
					
				}
				
			}
		});
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnContinue.setBounds(457, 277, 134, 28);
		panel_1.add(btnContinue);
		
		
	}
}
