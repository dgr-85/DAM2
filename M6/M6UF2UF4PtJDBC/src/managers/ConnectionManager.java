package managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static String url = "jdbc:mysql://localhost/empleats";
	private static String user = "cfgs";
	private static String pwd = "ira491";
	private static Connection con = null;

	private static int connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pwd);
			return 0;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public static boolean isConnected() throws SQLException {
		return !(con == null || con.isClosed());
	}

	public static Connection getConnection() {
		try {
			if (!isConnected()) {
				connect();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
