package enterAttendance;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

import AttendanceApp.StartMenu;
import ConnectionToDB.sqliteConnection;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class EnterDate {

	private JFrame frame;
	public static String date;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterDate window = new EnterDate();
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
	public EnterDate() {
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
		
		JLabel lblEnterStudentDetails = new JLabel("Enter Date");
		lblEnterStudentDetails.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterStudentDetails.setBounds(77, 168, 90, 33);
		panel.add(lblEnterStudentDetails);
		
		JLabel lblSelectDate = new JLabel("Select Date");
		lblSelectDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectDate.setBounds(498, 105, 82, 28);
		panel_1.add(lblSelectDate);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(427, 160, 237, 28);
		panel_1.add(dateChooser);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tableName=EnterAttendanceMenu.tableName;
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				date=sdf.format(dateChooser.getDate());
				String queryAlter="ALTER TABLE "+tableName+" ADD COLUMN \""+date+"\" TEXT";
				
				try {
					
					Statement stmt = connection.createStatement();
					stmt.execute(queryAlter);
					try {
						connection.close();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Oops! Something went wrong");
						StartMenu.main(null);
					}
					frame.setVisible(false);
					EnterTable.main(null);
					
				}catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, e2+date+tableName);
					
				}
				
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSubmit.setBounds(498, 221, 98, 28);
		panel_1.add(btnSubmit);
	}
}
