package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Departament;
import model.Empleat;

public class DepartamentDAOImpl implements DepartamentDAO {

	@Override
	public int addDepartament(Departament d) {
		Boolean isConnectionOpen = false;
		String sql = "insert into departamentos(dept_no,dnombre,loc) values (?,?,?)";
		try {
			isConnectionOpen = GestorConnexions.isConnected();
			Connection conexio = GestorConnexions.obtenirConnexio();
			PreparedStatement sentencia = conexio.prepareStatement(sql);
			sentencia.setInt(1, d.getCodiDepartament());
			sentencia.setString(2, d.getNomDepartament());
			sentencia.setString(3, d.getLlocDepartament());
			int resultat = sentencia.executeUpdate();
			return resultat;
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				// PK repetida
				return e.getErrorCode() * -1;
			} else {
				e.printStackTrace();
				return -1;
			}
		} finally {
			if (!isConnectionOpen) {
				GestorConnexions.tancarConnexio();
			}
		}
	}

	@Override
	public Departament getDepartamentById(Integer Id, boolean ambEmpleats) {
		Boolean isConnectionOpen = false;

		String sql = "select * from departamentos where dept_no=?";
		try {
			isConnectionOpen = GestorConnexions.isConnected();
			Connection conexio = GestorConnexions.obtenirConnexio();
			PreparedStatement sentencia = conexio.prepareStatement(sql);
			sentencia.setInt(1, Id);
			ResultSet rs = sentencia.executeQuery();
			Departament rsDep = new Departament();

			while (rs.next()) {
				rsDep.setCodiDepartament(rs.getInt(1));
				rsDep.setNomDepartament(rs.getString(2));
				rsDep.setLlocDepartament(rs.getString(3));
			}
			return rsDep;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (!isConnectionOpen) {
				GestorConnexions.tancarConnexio();
			}
		}
	}

	@Override
	public int updateDepartament(Departament d) {
		Boolean isConnectionOpen = false;

		String sql = "update departamentos set dnombre=?,loc=? where dept_no=?";
		try {
			isConnectionOpen = GestorConnexions.isConnected();
			Connection conexio = GestorConnexions.obtenirConnexio();
			PreparedStatement sentencia = conexio.prepareStatement(sql);
			sentencia.setString(1, d.getNomDepartament());
			sentencia.setString(2, d.getLlocDepartament());
			sentencia.setInt(3, d.getCodiDepartament());
			int resultat = sentencia.executeUpdate();
			return resultat;
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				// PK repetida
				return e.getErrorCode() * -1;
			} else {
				e.printStackTrace();
				return -1;
			}
		} finally {
			if (!isConnectionOpen) {
				GestorConnexions.tancarConnexio();
			}
		}
	}

	@Override
	public int deleteDepartament(Integer id, boolean cascade) {
		Boolean isConnectionOpen = false;
		String sql = "delete from departamentos where dept_no=?";
		try {
			isConnectionOpen = GestorConnexions.isConnected();
			Connection conexio = GestorConnexions.obtenirConnexio();
			PreparedStatement sentencia = conexio.prepareStatement(sql);
			sentencia.setInt(1, id);
			int resultat = sentencia.executeUpdate();
			return resultat;
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				// PK repetida
				return e.getErrorCode() * -1;
			} else {
				e.printStackTrace();
				return -1;
			}
		} finally {
			if (!isConnectionOpen) {
				GestorConnexions.tancarConnexio();
			}
		}
	}

	@Override
	public ArrayList<Departament> listDepartaments() {
		Boolean isConnectionOpen = false;
		Statement sentencia;
		String sql = "select * from departamentos";
		try {
			isConnectionOpen = GestorConnexions.isConnected();
			Connection conexio = GestorConnexions.obtenirConnexio();
			sentencia = conexio.createStatement();
			ResultSet resultat = sentencia.executeQuery(sql);

			ArrayList<Departament> departaments = new ArrayList<>();
			while (resultat.next()) {
				Departament dep = new Departament();
				dep.setCodiDepartament(resultat.getInt(1));
				dep.setNomDepartament(resultat.getString(2));
				dep.setLlocDepartament(resultat.getString(3));
				departaments.add(dep);
			}
			return departaments;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (!isConnectionOpen) {
				GestorConnexions.tancarConnexio();
			}
		}

	}

	@Override
	public ArrayList<Empleat> listEmpleatsByDepartament() {
		// TODO Auto-generated method stub
		System.out.println("Aquest mètode encara no està implementat.");
		return null;
	}

}
