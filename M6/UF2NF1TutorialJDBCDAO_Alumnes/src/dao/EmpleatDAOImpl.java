package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Departament;
import model.Empleat;

public class EmpleatDAOImpl implements EmpleatDAO {

	@Override
	public int addEmpleat(Empleat e) {
		Boolean isConnectionOpen = false;
		String sql = "insert into empleados values(?,?,?,?,?,?,?,?)";
		try {
			isConnectionOpen = GestorConnexions.isConnected();
			Connection conexio = GestorConnexions.obtenirConnexio();
			PreparedStatement sentencia = conexio.prepareStatement(sql);
			sentencia.setInt(1, e.getCodiEmpleat());
			sentencia.setString(2, e.getCognom());
			sentencia.setString(3, e.getOfici());
			sentencia.setInt(4, e.getDirector().getCodiEmpleat());
			sentencia.setDate(5, e.getDataAlta());
			sentencia.setFloat(6, e.getSalari());
			sentencia.setFloat(7, e.getComissio());
			sentencia.setInt(8, e.getDepartamentEmpleat().getCodiDepartament());
			int resultat = sentencia.executeUpdate();
			return resultat;
		} catch (SQLException ex) {
			if (ex.getErrorCode() == 1062) {
				// PK repetida
				return ex.getErrorCode() * -1;
			} else {
				ex.printStackTrace();
				return -1;
			}
		} finally {
			if (!isConnectionOpen) {
				GestorConnexions.tancarConnexio();
			}
		}
	}

	@Override
	public Empleat getEmpleatById(int id, Boolean buscarDirector) {
		Boolean isConnectionOpen = false;

		String sql = "select * from empleados where emp_no=?";
		try {
			isConnectionOpen = GestorConnexions.isConnected();
			Connection conexio = GestorConnexions.obtenirConnexio();
			PreparedStatement sentencia = conexio.prepareStatement(sql);
			sentencia.setInt(1, id);
			ResultSet rs = sentencia.executeQuery();
			Empleat rsEmp = new Empleat();

			while (rs.next()) {
				rsEmp.setCodiEmpleat(rs.getInt(1));
				rsEmp.setCognom(rs.getString(2));
				rsEmp.setOfici(rs.getString(3));

				if (buscarDirector) {
					Empleat e = getEmpleatById(rs.getInt(4), false);
					rsEmp.setDirector(e);
				}

				rsEmp.setDataAlta(rs.getDate(5));
				rsEmp.setSalari(rs.getFloat(6));
				rsEmp.setComissio(rs.getFloat(7));

				DepartamentDAO depDAO = DAOManager.getDepDAO();
				Departament d = depDAO.getDepartamentById(rs.getInt(8), false);
				rsEmp.setDepartamentEmpleat(d);
			}
			return rsEmp;

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
	public int updateEmpleat(Empleat e) {
		Boolean isConnectionOpen = false;
		String sql = "update empleados set apellido=?,oficio=?,dir=?,fecha_alt=?,salario=?,comision=?,dept_no=? where emp_no=?";
		try {
			isConnectionOpen = GestorConnexions.isConnected();
			Connection conexio = GestorConnexions.obtenirConnexio();
			PreparedStatement sentencia = conexio.prepareStatement(sql);
			sentencia.setString(1, e.getCognom());
			sentencia.setString(2, e.getOfici());
			sentencia.setInt(3, e.getDirector().getCodiEmpleat());
			sentencia.setDate(4, e.getDataAlta());
			sentencia.setFloat(5, e.getSalari());
			sentencia.setFloat(6, e.getComissio());
			sentencia.setInt(7, e.getDepartamentEmpleat().getCodiDepartament());
			sentencia.setInt(8, e.getCodiEmpleat());
			int resultat = sentencia.executeUpdate();
			return resultat;
		} catch (SQLException ex) {
			if (ex.getErrorCode() == 1062) {
				// PK repetida
				return ex.getErrorCode() * -1;
			} else {
				ex.printStackTrace();
				return -1;
			}
		} finally {
			if (!isConnectionOpen) {
				GestorConnexions.tancarConnexio();
			}
		}
	}

	@Override
	public int deleteEmpleat(int id) {
		Boolean isConnectionOpen = false;

		String sql = "delete from empleados where emp_no=?";
		try {
			isConnectionOpen = GestorConnexions.isConnected();
			Connection conexio = GestorConnexions.obtenirConnexio();
			PreparedStatement sentencia = conexio.prepareStatement(sql);
			sentencia.setInt(1, id);
			int resultat = sentencia.executeUpdate();
			return resultat;
		} catch (SQLException ex) {
			if (ex.getErrorCode() == 1062) {
				// PK repetida
				return ex.getErrorCode() * -1;
			} else {
				ex.printStackTrace();
				return -1;
			}
		} finally {
			if (!isConnectionOpen) {
				GestorConnexions.tancarConnexio();
			}
		}
	}

	@Override
	public ArrayList<Empleat> listEmpleats() {
		ArrayList<Empleat> empleats = new ArrayList<>();
		Boolean isConnectionOpen = false;
		Statement sentencia;
		String sql = "select * from empleados";
		try {
			isConnectionOpen = GestorConnexions.isConnected();
			Connection conexio = GestorConnexions.obtenirConnexio();
			sentencia = conexio.createStatement();
			ResultSet resultat = sentencia.executeQuery(sql);

			while (resultat.next()) {
				Empleat emp = new Empleat();
				emp.setCodiEmpleat(resultat.getInt(1));
				emp.setCognom(resultat.getString(2));
				emp.setOfici(resultat.getString(3));
				Empleat e = getEmpleatById(resultat.getInt(4), false);
				emp.setDirector(e);
				emp.setDataAlta(resultat.getDate(5));
				emp.setSalari(resultat.getFloat(6));
				emp.setComissio(resultat.getFloat(7));

				DepartamentDAO depDAO = DAOManager.getDepDAO();
				Departament d = depDAO.getDepartamentById(resultat.getInt(8), false);
				emp.setDepartamentEmpleat(d);

				empleats.add(emp);
			}
			return empleats;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (!isConnectionOpen) {
				GestorConnexions.tancarConnexio();
			}
		}
	}

}
