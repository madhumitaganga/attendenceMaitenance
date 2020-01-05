package viewAttendance;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import ConnectionToDB.sqliteConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;

import AttendanceApp.StartMenu;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewAttendanceDetailsStudent {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAttendanceDetailsStudent window = new ViewAttendanceDetailsStudent();
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
	Connection connection=null;
	private JTextField txtPercentageForThis;
	private JTable table;
	public ViewAttendanceDetailsStudent() {
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
		
		txtPercentageForThis = new JTextField();
		txtPercentageForThis.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPercentageForThis.setText("Percentage for this subject : ");
		txtPercentageForThis.setBounds(387, 269, 295, 26);
		panel_1.add(txtPercentageForThis);
		txtPercentageForThis.setColumns(10);
		
		JLabel lblPassFail = new JLabel("");
		lblPassFail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassFail.setBounds(513, 305, 53, 26);
		panel_1.add(lblPassFail);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(281, 153, 575, 63);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblUSN = new JLabel("USN :");
		lblUSN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUSN.setBounds(337, 63, 78, 26);
		panel_1.add(lblUSN);
		
		JLabel lblSubject = new JLabel("Subject :");
		lblSubject.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSubject.setBounds(487, 65, 108, 22);
		panel_1.add(lblSubject);
		
		JLabel lblSection = new JLabel("Section :");
		lblSection.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSection.setBounds(678, 63, 78, 26);
		panel_1.add(lblSection);
		
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
				ViewAttendanceStudent.main(null);
				
			}
		});
		btnBack.setBounds(771, 10, 85, 21);
		panel_1.add(btnBack);
		
		String tableName1=ViewAttendanceStudent.tableName1;
		String usn=ViewAttendanceStudent.USN;
		String queryGetAtten="Select * from "+tableName1+" where usn="+usn;
		try {
			PreparedStatement pst1=connection.prepareStatement(queryGetAtten);
			ResultSet rs=pst1.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			ResultSet rs1=pst1.executeQuery();
			ResultSetMetaData rsmd = rs1.getMetaData();
			int count=rsmd.getColumnCount();

			int p=0;
				for(int i=2;i<=count;i++) {
					if(rs.getString(i).equals("p")) {
						p++;
					}
				}
			count=count-1;
			double percentage;
			percentage=((double)p/count)*100;
			txtPercentageForThis.setText("Percentage for this subject : "+percentage);
			if(percentage>=75) {
				lblPassFail.setText("PASS");
			}else {
				lblPassFail.setText("FAIL");
			}
			lblUSN.setText("USN : "+usn);
			lblSubject.setText("Subject : "+ViewAttendanceStudent.sub);
			lblSection.setText("Section : "+ViewAttendanceStudent.Sec);
			
			
		}catch(Exception e1) {
			
			JOptionPane.showMessageDialog(null, e1);
			
		}
		
	}
}
