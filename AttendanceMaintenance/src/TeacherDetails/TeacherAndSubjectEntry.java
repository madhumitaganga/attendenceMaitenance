package TeacherDetails;

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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import AttendanceApp.Menu;
import AttendanceApp.StartMenu;
import ConnectionToDB.sqliteConnection;

public class TeacherAndSubjectEntry {

	private JFrame frame;
	private JTextField txtNameTeacher;
	private JTextField txtSubject;
	private JTextField txtSectionTeacher;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherAndSubjectEntry window = new TeacherAndSubjectEntry();
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
	public TeacherAndSubjectEntry() {
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
		
		JLabel lblEnterStudentDetails = new JLabel("Enter Teacher Details");
		lblEnterStudentDetails.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterStudentDetails.setBounds(51, 166, 175, 33);
		panel.add(lblEnterStudentDetails);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(264, 0, 722, 363);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(314, 87, 58, 25);
		panel_1.add(lblName);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubject.setBounds(314, 163, 85, 25);
		panel_1.add(lblSubject);
		
		JLabel lblSection = new JLabel("Section");
		lblSection.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSection.setBounds(314, 250, 67, 25);
		panel_1.add(lblSection);
		
		txtNameTeacher = new JTextField();
		txtNameTeacher.setBounds(428, 84, 331, 28);
		panel_1.add(txtNameTeacher);
		txtNameTeacher.setColumns(10);
		
		txtSubject = new JTextField();
		txtSubject.setBounds(428, 164, 331, 28);
		panel_1.add(txtSubject);
		txtSubject.setColumns(10);
		
		txtSectionTeacher = new JTextField();
		txtSectionTeacher.setBounds(428, 251, 331, 28);
		panel_1.add(txtSectionTeacher);
		txtSectionTeacher.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Menu.main(null);
			}
		});
		btnBack.setBounds(791, 10, 85, 21);
		panel_1.add(btnBack);
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String query = "insert into TeacherDetailTable (Name,Subject,Section) values(?,?,?)";
				try {
					
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1,txtNameTeacher.getText());
					pst.setString(2,txtSubject.getText());
					pst.setString(3,txtSectionTeacher.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "Entry Successful!");
					
					
					
					
			}catch (Exception e1) {
				
				JOptionPane.showMessageDialog(null, e1);
				
			}
			String tableName=txtSubject.getText()+"in"+txtSectionTeacher.getText();
			String queryNewTeacher="CREATE TABLE "+tableName+" (" + 
					"USN INTEGER NOT NULL," + 
					"PRIMARY KEY(USN)" + 
					")";
			try {
				PreparedStatement pst1=connection.prepareStatement(queryNewTeacher);
				pst1.executeUpdate();
			}
			
			catch (Exception e2) {
				
				JOptionPane.showMessageDialog(null, e2);
				
			}
			
			String usnsOfAllStudents="Select USN from StudentDetailTable where Section = '"+txtSectionTeacher.getText()+"'";
			try {
				PreparedStatement pst2=connection.prepareStatement(usnsOfAllStudents);
				ResultSet rs = pst2.executeQuery();
				while(rs.next()) {
					String copyUsn=rs.getString("USN");
					String insertUsn = "insert into "+tableName+" (USN) values("+copyUsn+")";
					try {
						PreparedStatement pst3=connection.prepareStatement(insertUsn);
						pst3.execute();
					}catch (Exception e2) {
						
						JOptionPane.showMessageDialog(null, e2);
						
					}
					
				}
				
			}catch (Exception e3) {
				
				JOptionPane.showMessageDialog(null, e3);
				
			}
			
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
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnSubmit.setBounds(549, 307, 92, 25);
		panel_1.add(btnSubmit);
	}
}
