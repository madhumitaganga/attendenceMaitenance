package AttendanceApp;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JLabel;

import loginSys.LoginStudent;
import loginSys.LoginSystem;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartMenu window = new StartMenu();
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
	public StartMenu() {
		initialize();
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
		
		JLabel lblMenu = new JLabel("Menu");
		lblMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMenu.setBounds(97, 182, 45, 13);
		panel.add(lblMenu);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(264, 0, 722, 363);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnTeacherLogin = new JButton("Teacher Login");
		btnTeacherLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginSystem.main(null);
				frame.setVisible(false);
				
			}
		});
		btnTeacherLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnTeacherLogin.setBounds(426, 120, 259, 32);
		panel_1.add(btnTeacherLogin);
		
		JButton btnStudentLogin = new JButton("Student Login");
		btnStudentLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginStudent.main(null);
				frame.setVisible(false);
			}
		});
		btnStudentLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStudentLogin.setBounds(426, 198, 259, 32);
		panel_1.add(btnStudentLogin);
	}
}
