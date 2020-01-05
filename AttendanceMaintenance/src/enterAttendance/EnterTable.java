package enterAttendance;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import AttendanceApp.Menu;
import AttendanceApp.StartMenu;
import ConnectionToDB.sqliteConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnterTable {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterTable window = new EnterTable();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection=null;
	private int countUSN=0;
	private String count;
	private ResultSet rs;
	private int count1=0;
	private JTable table_3;
	private JTextField txtPreAb;
	/**
	 * Create the application.
	 */
	public EnterTable() {
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
		
		JLabel lblEnterStudentDetails = new JLabel("Enter Attendance");
		lblEnterStudentDetails.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterStudentDetails.setBounds(71, 168, 133, 33);
		panel.add(lblEnterStudentDetails);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(303, 63, 103, 290);
		panel_1.add(scrollPane);
		
		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(405, 63, 103, 290);
		panel_1.add(scrollPane_1);
		
		table_3 = new JTable();
		scrollPane_1.setViewportView(table_3);
		
		txtPreAb = new JTextField();
		txtPreAb.setBounds(671, 154, 53, 31);
		panel_1.add(txtPreAb);
		txtPreAb.setColumns(10);
		
		String[] columnNames= {"USN"};
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table_2.setModel(model);
		
		String[] columnNames1= {"P/A"};
		DefaultTableModel model1 = new DefaultTableModel();
		model1.setColumnIdentifiers(columnNames1);
		table_3.setModel(model1);
		
		String date=EnterDate.date;
		String tableName=EnterAttendanceMenu.tableName;
		String query="Select usn from "+tableName;
		try {
			PreparedStatement pst1=connection.prepareStatement(query);
			ResultSet rs2=pst1.executeQuery();
			table_2.setModel(DbUtils.resultSetToTableModel(rs2));
			
		}catch (Exception e2) {
			
			JOptionPane.showMessageDialog(null, e2);
			
		}
		
		
		Object[] row= new Object[1];
		JButton btnAdd = new JButton("Add");
		try {
			PreparedStatement pst1 = connection.prepareStatement(query);
			rs=pst1.executeQuery();
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String query2="Select count(*) from "+tableName;
				
				
				
				
				try {
					PreparedStatement pst3=connection.prepareStatement(query2);
					ResultSet rs1=pst3.executeQuery();
					count=rs1.getString(1);
					count1=Integer.parseInt(count);
				}catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, e2);
					
				}
				countUSN++;
				if(countUSN>count1) {
					JOptionPane.showMessageDialog(null, "Entry limit!");
					try {
						connection.close();
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Oops! Something went wrong");
						StartMenu.main(null);
					}
					frame.setVisible(false);
					Menu.main(null);
				}else {
				row[0] = txtPreAb.getText();
				model1.addRow(row);
				try {
					
					rs.next();
					String query1="update "+tableName+" set '"+date+"' = \""+txtPreAb.getText()+"\" where USN="+rs.getString("USN");
					PreparedStatement pst2=connection.prepareStatement(query1);
					pst2.execute();
					
				}catch (Exception e2) {
					
					JOptionPane.showMessageDialog(null, e2);
					
				}
				}
				}});} catch (SQLException e1) {
					
					JOptionPane.showMessageDialog(null, e1);
					
			}
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAdd.setBounds(657, 195, 85, 31);
		panel_1.add(btnAdd);
		
		
		
		
		
		}
}
