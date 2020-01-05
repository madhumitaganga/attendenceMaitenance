package viewAttendance;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ConnectionToDB.sqliteConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import AttendanceApp.StartMenu;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewAttendanceDetails {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAttendanceDetails window = new ViewAttendanceDetails();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection=null;
	private JTable table;
	//private JTable table;
	/**
	 * Create the application.
	 */
	public ViewAttendanceDetails() {
		connection=sqliteConnection.dbConnector();
		initialize();
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(302, 50, 558, 303);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		String tableName=ViewAttedanceMenu.tableName;
		String query="Select * from "+tableName;
		try {
			PreparedStatement pst1=connection.prepareStatement(query);
			ResultSet rs2=pst1.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs2));
			
			JButton btnMainMenu = new JButton("Main Menu");
			btnMainMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						connection.close();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Oops! Something went wrong");
						StartMenu.main(null);
					}
					StartMenu.main(null);
					frame.setVisible(false);
					
				}
			});
			btnMainMenu.setBounds(725, 19, 112, 21);
			panel_1.add(btnMainMenu);
			
			JButton btnEnterAnotherSubject = new JButton("Enter Another Subject");
			btnEnterAnotherSubject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						connection.close();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Oops! Something went wrong");
						StartMenu.main(null);
					}
					ViewAttedanceMenu.main(null);
					frame.setVisible(false);
					
				}
			});
			btnEnterAnotherSubject.setBounds(542, 19, 173, 21);
			panel_1.add(btnEnterAnotherSubject);
			
		}catch (Exception e2) {
			
			JOptionPane.showMessageDialog(null, e2);
			
		}
	}
}
