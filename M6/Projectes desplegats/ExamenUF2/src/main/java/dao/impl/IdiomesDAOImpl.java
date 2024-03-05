package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.IdiomesDAO;
import managers.ConnectionManagerJDBC;
import managers.DAOManager;
import pojos.Idiomes;
import pojos.Llibres;

public class IdiomesDAOImpl extends DAOManager implements IdiomesDAO {

	@Override
	public Integer addIdioma(Idiomes idioma) {
		Boolean isConnectionOpen = false;
		String sql = "insert into idiomes values(?,?,?)";
		try {
			isConnectionOpen = ConnectionManagerJDBC.isConnected();
			Connection con = ConnectionManagerJDBC.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(sql);

			prepStmt.setInt(1, idioma.getCodi());
			prepStmt.setString(2, idioma.getDescripcio());

			if (idioma.getLlibreses() != null && idioma.getLlibreses().size() > 0) {
				for (Llibres ll : idioma.getLlibreses()) {
					getLlibresDAO().addLlibre(ll);
				}
			}
			return prepStmt.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				return e.getErrorCode() * -1;
			} else {
				e.printStackTrace();
				System.out.println("Error: " + e.getErrorCode());
				return -1;
			}
		} finally {
			if (!isConnectionOpen) {
				ConnectionManagerJDBC.closeConnection();
			}
		}
	}

	@Override
	public Idiomes getIdiomaById(Integer id) {
		Boolean isConnectionOpen = false;
		String sql = "select * from idiomes where codi=?";
		try {
			isConnectionOpen = ConnectionManagerJDBC.isConnected();
			Connection con = ConnectionManagerJDBC.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setInt(1, id);
			ResultSet rs = prepStmt.executeQuery();

			Idiomes idioma = new Idiomes();
			while (rs.next()) {
				idioma.setCodi(rs.getInt(1));
				idioma.setDescripcio(rs.getString(2));
			}
			return idioma;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error: " + e.getErrorCode());
			return null;
		} finally {
			if (!isConnectionOpen) {
				ConnectionManagerJDBC.closeConnection();
			}
		}
	}

	@Override
	public Integer updateIdioma(Idiomes idioma) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteIdioma(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Idiomes> listAllIdiomes() {
		// TODO Auto-generated method stub
		return null;
	}

}
