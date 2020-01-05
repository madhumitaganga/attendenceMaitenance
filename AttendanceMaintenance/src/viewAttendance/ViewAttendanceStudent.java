package viewAttendance;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import AttendanceApp.StartMenu;
import ConnectionToDB.sqliteConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ViewAttendanceStudent {

	private JFrame frame;
	private JTextField txtUSN;
	private JTextField txtSubject;
	private JTextField txtSection;
	public static String tableName1;
	public static String USN;
	public static String Sec;
	public static String sub;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAttendanceStudent window = new ViewAttendanceStudent();
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
	public ViewAttendanceStudent() {
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
		
		JLabel lblEnterStudentDetails = new JLabel("View Attendance");
		lblEnterStudentDetails.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterStudentDetails.setBounds(71, 168, 133, 33);
		panel.add(lblEnterStudentDetails);
		
		JLabel lblUsn = new JLabel("USN");
		lblUsn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsn.setBounds(326, 112, 45, 13);
		panel_1.add(lblUsn);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubject.setBounds(326, 173, 78, 13);
		panel_1.add(lblSubject);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSection.setBounds(326, 239, 78, 13);
		panel_1.add(lblSection);
		
		txtUSN = new JTextField();
		txtUSN.setBounds(440, 106, 264, 29);
		panel_1.add(txtUSN);
		txtUSN.setColumns(10);
		
		txtSubject = new JTextField();
		txtSubject.setBounds(440, 167, 264, 29);
		panel_1.add(txtSubject);
		txtSubject.setColumns(10);
		
		txtSection = new JTextField();
		txtSection.setBounds(440, 233, 264, 29);
		panel_1.add(txtSection);
		txtSection.setColumns(10);
		
		JButton btnViewAttendance = new JButton("View Attendance");
		btnViewAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				USN=txtUSN.getText();
				Sec=txtSection.getText();
				sub=txtSubject.getText();
				tableName1=txtSubject.getText()+"in"+txtSection.getText();
				String queryGetAtten="Select * from "+tableName1+" where usn="+txtUSN.getText();
				try {
					PreparedStatement pst1=connection.prepareStatement(queryGetAtten);
					//JOptionPane.showMessageDialog(null, "here1");
					ResultSet rs=pst1.executeQuery();
					try {
						connection.close();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Oops! Something went wrong");
						StartMenu.main(null);
					}
					frame.setVisible(false);
					ViewAttendanceDetailsStudent.main(null);
				}catch(Exception e1) {
					
					JOptionPane.showMessageDialog(null, "Invalid credentials, Please Check Again.");
					
				}
				
			}
		});
		btnViewAttendance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnViewAttendance.setBounds(451, 298, 232, 29);
		panel_1.add(btnViewAttendance);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					connection.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Oops, Something went wrong!");
					StartMenu.main(null);
				}
				frame.setVisible(false);
				StartMenu.main(null);
				
			}
		});
		btnBack.setBounds(754, 10, 85, 21);
		panel_1.add(btnBack);
		
		
	}
}
