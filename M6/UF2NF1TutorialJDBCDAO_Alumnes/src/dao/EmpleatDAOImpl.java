package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			sentencia.setObject(4, e.getDirector());
			sentencia.setDate(5, e.getDataAlta());
			sentencia.setFloat(6, e.getSalari());
			sentencia.setFloat(7, e.getComissio());
			sentencia.setObject(8, e.getDepartamentEmpleat());
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
	public Empleat getEmpleatById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateEmpleat(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteEmpleat(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
