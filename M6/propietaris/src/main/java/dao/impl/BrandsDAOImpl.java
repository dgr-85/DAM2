package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.BrandsDAO;
import managers.ConnectionManagerJDBC;
import managers.DAOManager;
import pojos.Brands;
import pojos.Vehicles;

public class BrandsDAOImpl extends DAOManager implements BrandsDAO {

	@Override
	public Integer addBrand(Brands brand) {
		Boolean isConnectionOpen = false;
		String sql = "insert into brands values(?,?,?)";
		try {
			isConnectionOpen = ConnectionManagerJDBC.isConnected();
			Connection con = ConnectionManagerJDBC.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(sql);

			prepStmt.setInt(1, brand.getBrandId());
			prepStmt.setString(2, brand.getBrandName());
			prepStmt.setString(3, brand.getCountry());

			if (brand.getVehicleses() != null && brand.getVehicleses().size() > 0) {
				for (Vehicles v : brand.getVehicleses()) {
					getVehiclesDAO().addVehicle(v);
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
	public Brands getBrandById(Integer id, Boolean includeVehicles) {
		Boolean isConnectionOpen = false;
		String sql = "select * from brands where brandid=?";
		try {
			isConnectionOpen = ConnectionManagerJDBC.isConnected();
			Connection con = ConnectionManagerJDBC.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setInt(1, id);
			ResultSet rs = prepStmt.executeQuery();

			Brands brand = new Brands();
			while (rs.next()) {
				brand.setBrandId(rs.getInt(1));
				brand.setBrandName(rs.getString(2));
				brand.setCountry(rs.getString(3));
				brand.setVehicleses(null);

				if (includeVehicles) {
					sql = "select * from vehicles where brandid=?";
					prepStmt = con.prepareStatement(sql);
					prepStmt.setInt(1, id);

					ResultSet rsVehicles = prepStmt.executeQuery();

					Set<Vehicles> vehicles = new HashSet<>();
					while (rsVehicles.next()) {
						Vehicles v = new Vehicles();
						v.setVehicleId(rsVehicles.getInt(1));
						v.setModel(rsVehicles.getString(2));
						v.setLicensePlate(rsVehicles.getString(3));
						v.setOwners(getOwnersDAO().getOwnerById(rsVehicles.getInt(4), false));
						v.setBrands(getBrandsDAO().getBrandById(rsVehicles.getInt(5), false));
						vehicles.add(v);
					}
					brand.setVehicleses(vehicles);
				}
			}
			return brand;
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
	public Integer updateBrand(Brands brand) {
		Boolean isConnectionOpen = false;
		String sqlcheckBrand = "select brandid from brands where brandid=?";
		String sql = "update brands set brandname=?, country=? where brandid=?";
		try {
			isConnectionOpen = ConnectionManagerJDBC.isConnected();
			Connection con = ConnectionManagerJDBC.getConnection();

			// If the provided brand isn't found in the database, the method stops with an
			// exception
			PreparedStatement prepStmt = con.prepareStatement(sqlcheckBrand);
			prepStmt.executeQuery();
			// Otherwise, the brand is updated
			prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, brand.getBrandName());
			prepStmt.setString(2, brand.getCountry());
			prepStmt.setInt(3, brand.getBrandId());

			if (brand.getVehicleses() != null && brand.getVehicleses().size() > 0) {
				for (Vehicles v : brand.getVehicleses()) {
					if (getVehiclesDAO().getVehicleById(v.getVehicleId()) == null) {
						getVehiclesDAO().addVehicle(v);
					} else {
						getVehiclesDAO().updateVehicle(v);
					}
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
	public Integer deleteBrand(Integer id) {
		Boolean isConnectionOpen = false;
		String sql = "delete from brands where brandid=?";
		try {
			isConnectionOpen = ConnectionManagerJDBC.isConnected();
			Connection con = ConnectionManagerJDBC.getConnection();
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setInt(1, id);
			try {
				con.setAutoCommit(false);
				String sqlCascade = "delete from vehicles where brandid=?";
				PreparedStatement deleteVehicles = con.prepareStatement(sqlCascade);
				deleteVehicles.setInt(1, id);
				deleteVehicles.executeUpdate();
				con.commit();
				con.setAutoCommit(true);
			} catch (SQLException e) {
				System.out.println("An error has occurred while deleting the brand. Rolling back.");
				con.rollback();
			}
			return prepStmt.executeUpdate();
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
	public List<Brands> listAllBrands(Boolean includeVehicles) {
		List<Brands> brands = new ArrayList<>();
		Boolean isConnectionOpen = false;
		String sql = "select * from brands";
		Statement statement;
		try {
			isConnectionOpen = ConnectionManagerJDBC.isConnected();
			Connection con = ConnectionManagerJDBC.getConnection();
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				Brands brand = new Brands();
				brand.setBrandId(rs.getInt(1));
				brand.setBrandName(rs.getString(2));
				brand.setCountry(rs.getString(3));
				brand.setVehicleses(null);

				if (includeVehicles) {
					sql = "select * from vehicles where brandid=?";
					PreparedStatement prepStmt = con.prepareStatement(sql);
					prepStmt.setInt(1, brand.getBrandId());

					ResultSet rsVehicles = prepStmt.executeQuery();

					Set<Vehicles> vehicles = new HashSet<>();
					while (rsVehicles.next()) {
						Vehicles v = new Vehicles();
						v.setVehicleId(rsVehicles.getInt(1));
						v.setModel(rsVehicles.getString(2));
						v.setLicensePlate(rsVehicles.getString(3));
						v.setOwners(getOwnersDAO().getOwnerById(rsVehicles.getInt(4), false));
						v.setBrands(getBrandsDAO().getBrandById(rsVehicles.getInt(5), false));
						vehicles.add(v);
					}
					brand.setVehicleses(vehicles);
				}
				brands.add(brand);
			}
			return brands;
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

}
