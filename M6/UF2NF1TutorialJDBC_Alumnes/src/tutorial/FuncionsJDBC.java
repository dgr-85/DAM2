package tutorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FuncionsJDBC {

	public void consultaDepartaments(Connection conexio) {
		// Consulta Departaments
		Statement sentencia;
		try {
			sentencia = conexio.createStatement();
			String sql = "SELECT * FROM departamentos";
			ResultSet resultat = sentencia.executeQuery(sql);
			System.out.println("*****Consulta de Departaments*****");
			while (resultat.next()) {
				// Accedim a les columnes del resulset per posici� o b� per nom
				System.out.println("Codi departament: " + resultat.getInt(1) + " Nom: " + resultat.getString(2)
						+ " Ciutat: " + resultat.getString("loc"));
			} // getString accepta int com a posició de columna o string com a nom de la
				// columna
			System.out.println("\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void consultaEmpleats(Connection conexio) {
		Statement sentencia;
		try {
			sentencia = conexio.createStatement();
			String sql = "SELECT dnombre,e1.emp_no,e1.apellido,e1.oficio,e2.apellido,e1.salario,e1.comision";
			sql += " from empleados e1 join empleados e2 on e1.dir=e2.emp_no";
			sql += " join departamentos on e1.dept_no=departamentos.dept_no";
			ResultSet resultat = sentencia.executeQuery(sql);
			System.out.println("*****Consulta d'Empleats*****");
			while (resultat.next()) {
				System.out.println("Nom departament: " + resultat.getString(1) + " Codi Empleat: " + resultat.getInt(2)
						+ " Cognom: " + resultat.getString(3) + " Ofici: " + resultat.getString(4)
						+ " Nom del seu cap: " + resultat.getString(5) + " Salari: " + resultat.getInt(6)
						+ " Comissió: " + resultat.getInt(7));
			}
			System.out.println("\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String demanarDades(String enunciat) {
		System.out.println(enunciat);
		return Main.sc.nextLine();
	}

	public void empleatsPerDepartament(Connection connexio) {
		String sql = "SELECT EMP_NO,APELLIDO FROM empleados WHERE DEPT_NO = ?";
		try {
			PreparedStatement sentenciaPreparada = connexio.prepareStatement(sql);
			int departament = Integer.parseInt(demanarDades("Indica el codi de departament: "));
			sentenciaPreparada.setInt(1, departament);

			System.out.println("Empleats del departament " + departament + ":");
			ResultSet res = sentenciaPreparada.executeQuery();

			while (res.next()) {
				System.out.println("Codi empleat: " + res.getInt(1) + " Cognom: " + res.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void empleatsPerCognom(Connection connexio) {
		String sql = "SELECT e1.emp_no,e1.apellido,e1.oficio,e2.apellido,e1.salario,e1.comision";
		sql += " from empleados e1 left join empleados e2 on e1.dir=e2.emp_no";
		sql += " join departamentos on e1.dept_no=departamentos.dept_no";
		sql += " where e1.apellido =?";

		try {
			PreparedStatement sentenciaPreparada = connexio.prepareStatement(sql);
			String cognomEmpleat = demanarDades("Indica el cognom de l'empleat: ");
			sentenciaPreparada.setString(1, cognomEmpleat);

			System.out.println("Empleats per cognom " + cognomEmpleat + ":");
			ResultSet res = sentenciaPreparada.executeQuery();

			while (res.next()) {
				System.out.println("Codi empleat: " + res.getInt(1) + " || Cognom: " + res.getString(2) + " || Ofici: "
						+ res.getString(3) + " || Nom del seu cap: " + res.getString(4) + " || Salari: " + res.getInt(5)
						+ " || Comissió: " + res.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void empleatsPerOfici(Connection connexio) {
		String llistaOficis = "select distinct oficio from empleados";

		try {
			PreparedStatement sentenciaOficis = connexio.prepareStatement(llistaOficis);
			System.out.println("Oficis disponibles:");
			ResultSet resultatOficis = sentenciaOficis.executeQuery(llistaOficis);

			while (resultatOficis.next()) {
				System.out.println(resultatOficis.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "SELECT e1.emp_no,e1.apellido,e2.apellido,e1.salario,e1.comision";
		sql += " from empleados e1 left join empleados e2 on e1.dir=e2.emp_no";
		sql += " join departamentos on e1.dept_no=departamentos.dept_no";
		sql += " where e1.oficio=?";

		try {
			PreparedStatement sentenciaPreparada = connexio.prepareStatement(sql);
			String oficiEmpleat = demanarDades("Tria un ofici per a veure'n els empleats:");
			sentenciaPreparada.setString(1, oficiEmpleat);

			System.out.println("Empleats amb ofici de " + oficiEmpleat + ":");
			ResultSet res = sentenciaPreparada.executeQuery();

			while (res.next()) {
				System.out.println(
						"Codi empleat: " + res.getInt(1) + " || Cognom: " + res.getString(2) + " || Nom del seu cap: "
								+ res.getString(3) + " || Salari: " + res.getInt(4) + " || Comissió: " + res.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
