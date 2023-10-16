package tutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	
	public static Scanner sc;

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost/empleats";
		String user = "cfgs";
		String pwd = "ira491";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexio = DriverManager.getConnection(url, user, pwd);

			if (conexio.isValid(0)) {
				System.out.println("Connexió vàlida");
			}

			FuncionsJDBC gestor = new FuncionsJDBC();
			ModificacionsJDBC modificador =new ModificacionsJDBC();
			sc=new Scanner(System.in);
			// Consulta Departaments
			/*gestor.consultaDepartaments(conexio);
			gestor.consultaEmpleats(conexio);
			gestor.empleatsPerDepartament(conexio);
			gestor.empleatsPerCognom(conexio);
			gestor.empleatsPerOfici(conexio);*/
			modificador.afegirEmpleat(conexio);

			sc.close();
			conexio.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
