package ConnectionToDB;
import java.sql.*;
import javax.swing.*;
public class sqliteConnection {
	Connection conn=null;
	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\madhu\\eclipse-workspacenew\\AttaendanceDB.sqlite");
			//JOptionPane.showMessageDialog(null, "Connection is successful!");
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
}
