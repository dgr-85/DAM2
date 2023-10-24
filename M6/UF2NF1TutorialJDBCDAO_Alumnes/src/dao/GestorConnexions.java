package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestorConnexions {
	private static String url = "jdbc:mysql://localhost/Empleats";
	private static String user = "cfgs";
	private static String pwd = "ira491";

	/*
	 * private static String url = "jdbc:mysql://localhost/Empleats"; private static
	 * String user = "root" ; private static String pwd = "";
	 */
	private static Connection connexio = null;

	private static int connectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexio = DriverManager.getConnection(url, user, pwd);
			return 0;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	public static boolean isConnected() throws SQLException {
		/*
		 * if (connexio == null || connexio.isClosed()){ return false; }else { return
		 * true; }
		 */
		return !(connexio == null || connexio.isClosed());

	}

	public static Connection obtenirConnexio() {
		try {
			if (!isConnected()) {
				connectar();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connexio;
	}

	public static void tancarConnexio() {
		try {
			connexio.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
