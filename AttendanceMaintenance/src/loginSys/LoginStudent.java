package loginSys;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import AttendanceApp.Menu;
import viewAttendance.ViewAttedanceMenu;
import viewAttendance.ViewAttendanceStudent;

public class LoginStudent {

	private JFrame frame;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginStudent window = new LoginStudent();
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
	public LoginStudent() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(200, 200, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Attendance Maintenace Student Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLogin.setBounds(62, 23, 341, 31);
		frame.getContentPane().add(lblLogin);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(62, 79, 106, 31);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(62, 153, 106, 21);
		frame.getContentPane().add(lblPassword);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(198, 87, 215, 23);
		frame.getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(198, 150, 215, 24);
		frame.getContentPane().add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String password = txtPassword.getText();
				String username = txtUsername.getText();
				
				if(password.contains("student") && username.contains("student")) {
					txtPassword.setText(null);
					txtUsername.setText(null);
					frame.setVisible(false);
					ViewAttendanceStudent.main(null);
					
				}
				else {
					JOptionPane.showMessageDialog(null,"Invalid Credentials, Please Check Again.","LogIn Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnLogin.setBounds(62, 216, 101, 37);
		frame.getContentPane().add(btnLogin);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsername.setText(null);
				txtPassword.setText(null);
			}
		});
		btnReset.setBounds(190, 216, 101, 37);
		frame.getContentPane().add(btnReset);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFrame frmLoginSystem = new JFrame("Cancel");
				if(JOptionPane.showConfirmDialog(frmLoginSystem, "Are you sure you want to exit?","Login Systems", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
				}
		});
		btnCancel.setBounds(312, 216, 101, 37);
		frame.getContentPane().add(btnCancel);
	}

}
