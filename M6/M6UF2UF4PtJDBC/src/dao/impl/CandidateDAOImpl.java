package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.CandidateDAO;
import managers.ConnectionManager;
import managers.DAOManager;
import model.Candidate;

public class CandidateDAOImpl extends DAOManager implements CandidateDAO {

	@Override
	public Integer addCandidate(Candidate c) {
		Boolean isConnectionOpen = false;
		String sql = "insert into candidates values(?,?,?,?,?)";
		try {
			isConnectionOpen = ConnectionManager.isConnected();
			Connection con = ConnectionManager.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(sql);

			prepStmt.setInt(1, c.getCandidateId());
			prepStmt.setString(2, c.getFirstName());
			prepStmt.setString(3, c.getLastName());
			prepStmt.setString(4, c.getPhoneNumber());
			prepStmt.setString(5, c.getEmail());

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
	public Candidate getCandidateById(int id, Boolean includePrizes) {
		Boolean isConnectionOpen = false;
		String sql = "select * from candidates where lower('candidateid')=?";
		try {
			isConnectionOpen = ConnectionManager.isConnected();
			Connection con = ConnectionManager.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setInt(1, id);
			ResultSet rs = prepStmt.executeQuery();

			Candidate candidate = new Candidate();
			while (rs.next()) {
				candidate.setCandidateId(rs.getInt(1));
				candidate.setFirstName(rs.getString(2));
				candidate.setLastName(rs.getString(3));
				candidate.setPhoneNumber(rs.getString(4));
				candidate.setEmail(rs.getString(5));
				if (includePrizes) {
					sql = "select * from prizetype where lower(prizetypeid) in (select lower(prizetypeid) from prizes where candidateid=?)";
					prepStmt = con.prepareStatement(sql);
					prepStmt.setInt(1, id);
					ResultSet rsPrizes = prepStmt.executeQuery();

				}
			}
			return candidate;
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
	public Integer updateCandidate(Candidate c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteCandidate(int id) {
		Boolean isConnectionOpen = false;
		String sql = "delete from candidates where lower('candidateid')=?";
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
	public ArrayList<Candidate> listAllCandidates(Boolean includePrizes) {
		ArrayList<Candidate> candidates = new ArrayList<>();
		Boolean isConnectionOpen = false;
		String sql = "select * from candidates";
		Statement statement;
		try {
			isConnectionOpen = ConnectionManager.isConnected();
			Connection con = ConnectionManager.getConnection();
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				Candidate candidate = new Candidate();
				candidate.setCandidateId(rs.getInt(1));
				candidate.setFirstName(rs.getString(2));
				candidate.setLastName(rs.getString(3));
				candidate.setPhoneNumber(rs.getString(4));
				candidate.setEmail(rs.getString(5));
				candidates.add(candidate);
			}
			return candidates;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (isConnectionOpen) {
				ConnectionManager.closeConnection();
			}
		}
	}

}
