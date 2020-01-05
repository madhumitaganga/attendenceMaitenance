package AttendanceApp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JLabel;

import StudentDetails.StudentDetailEntry;
import TeacherDetails.TeacherAndSubjectEntry;
import enterAttendance.EnterAttendanceMenu;
import loginSys.LoginSystem;
import viewAttendance.ViewAttedanceMenu;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 300, 900, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Panel panel = new Panel();
		panel.setBackground(new Color(0, 206, 209));
		panel.setBounds(0, 0, 264, 363);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblAttendanceMaintenanceSystem = new JLabel("Attendance Maintenance System");
		lblAttendanceMaintenanceSystem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAttendanceMaintenanceSystem.setBounds(10, 127, 254, 31);
		panel.add(lblAttendanceMaintenanceSystem);
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMenu.setBounds(97, 182, 45, 13);
		panel.add(lblMenu);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(260, 0, 732, 363);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnEnterAttendance = new JButton("Enter Attendance");
		btnEnterAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EnterAttendanceMenu.main(null);
				frame.setVisible(false);
				
				
			}
		});
		btnEnterAttendance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEnterAttendance.setBounds(167, 206, 319, 35);
		panel_1.add(btnEnterAttendance);
		
		JButton btnUpdateAttendance = new JButton("View Attendance");
		btnUpdateAttendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ViewAttedanceMenu.main(null);
				frame.setVisible(false);
				
			}
		});
		btnUpdateAttendance.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpdateAttendance.setBounds(167, 282, 319, 35);
		panel_1.add(btnUpdateAttendance);
		
		JButton btnEnterStudentDetails = new JButton("Enter Student Details");
		btnEnterStudentDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				StudentDetailEntry.main(null);
			}
		});
		btnEnterStudentDetails.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEnterStudentDetails.setBounds(167, 48, 319, 35);
		panel_1.add(btnEnterStudentDetails);
		
		JButton btnEnterTeacherAnd = new JButton("Enter Teacher and Subject details");
		btnEnterTeacherAnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				TeacherAndSubjectEntry.main(null);
				
			}
		});
		btnEnterTeacherAnd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEnterTeacherAnd.setBounds(167, 127, 319, 35);
		panel_1.add(btnEnterTeacherAnd);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginSystem.main(null);
			}
		});
		btnLogout.setBounds(522, 10, 85, 21);
		panel_1.add(btnLogout);
		
		JButton btnHome = new JButton("Home");
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StartMenu.main(null);
				frame.setVisible(false);
				
			}
		});
		btnHome.setBounds(428, 10, 85, 21);
		panel_1.add(btnHome);
	}
}
