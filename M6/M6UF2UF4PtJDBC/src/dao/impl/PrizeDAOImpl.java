package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PrizeDAO;
import managers.ConnectionManager;
import managers.DAOManager;
import model.Prize;

public class PrizeDAOImpl extends DAOManager implements PrizeDAO {

	@Override
	public Integer addPrize(Prize p) {
		Boolean isConnectionOpen = false;
		String sql = "insert into prizes values(?,?,?,?)";
		try {
			isConnectionOpen = ConnectionManager.isConnected();
			Connection con = ConnectionManager.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(sql);

			prepStmt.setInt(1, p.getPrizeId());
			prepStmt.setInt(2, p.getCandidateId().getCandidateId());
			prepStmt.setInt(3, p.getPrizeTypeId().getPrizeTypeId());
			prepStmt.setInt(4, p.getYear());

			return prepStmt.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				return e.getErrorCode() * -1;
			} else {
				e.printStackTrace();
				return -1;
			}
		} finally {
			if (isConnectionOpen) {
				ConnectionManager.closeConnection();
			}
		}
	}

	@Override
	public Prize getPrizeById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updatePrize(Prize p) {
		Boolean isConnectionOpen = false;
		String sql = "update prizes set lower(candidateid)=?,lower(prizetypeid)=?,lower(year)=? where lower(prizeid)=?";
		try {
			isConnectionOpen = ConnectionManager.isConnected();
			Connection con = ConnectionManager.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(sql);

			prepStmt.setInt(1, p.getCandidateId().getCandidateId());
			prepStmt.setInt(2, p.getPrizeTypeId().getPrizeTypeId());
			prepStmt.setInt(3, p.getYear());
			prepStmt.setInt(4, p.getPrizeId());

			return prepStmt.executeUpdate();
		} catch (SQLException e) {
			if (e.getErrorCode() == 1062) {
				return e.getErrorCode() * -1;
			} else {
				e.printStackTrace();
				return -1;
			}
		} finally {
			if (isConnectionOpen) {
				ConnectionManager.closeConnection();
			}
		}
	}

	@Override
	public Integer deletePrize(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Prize> listAllPrizes() {
		// TODO Auto-generated method stub
		return null;
	}

}
