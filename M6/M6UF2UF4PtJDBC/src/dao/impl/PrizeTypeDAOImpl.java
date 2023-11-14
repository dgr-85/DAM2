package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PrizeTypeDAO;
import managers.ConnectionManager;
import managers.DAOManager;
import model.PrizeType;

public class PrizeTypeDAOImpl extends DAOManager implements PrizeTypeDAO {

	@Override
	public int addPrizeType(PrizeType pt) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PrizeType getPrizetypeById(int id, Boolean includePrizes) {
		Boolean isConnectionOpen = false;
		String sql = "select * from prizetype where lower('prizetypeid')=?";
		try {
			isConnectionOpen = ConnectionManager.isConnected();
			Connection con = ConnectionManager.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setInt(1, id);
			ResultSet rs = prepStmt.executeQuery();

			PrizeType prizeType = new PrizeType();
			while (rs.next()) {
				prizeType.setPrizeTypeId(rs.getInt(1));
				prizeType.setPrizeName(rs.getString(2));
				prizeType.setPrizeDescription(rs.getString(3));
				prizeType.setPrizeValue(rs.getDouble(4));
			}
			return prizeType;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (isConnectionOpen) {
				ConnectionManager.closeConnection();
			}
		}
	}

	@Override
	public Integer updatePrizeType(PrizeType pt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deletePrizeType(int id) {
		Boolean isConnectionOpen = false;
		String sql = "delete from prizetype where lower('prizetypeid')=?";
		try {
			isConnectionOpen = ConnectionManager.isConnected();
			Connection con = ConnectionManager.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setInt(1, id);

			return prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (isConnectionOpen) {
				ConnectionManager.closeConnection();
			}
		}
	}

	@Override
	public ArrayList<PrizeType> listAllPrizeTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
