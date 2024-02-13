package managers;

import dao.BrandsDAO;
import dao.OwnersDAO;
import dao.VehiclesDAO;
import dao.impl.BrandsDAOImpl;
import dao.impl.OwnersDAOImpl;
import dao.impl.VehiclesDAOImpl;

public abstract class DAOManager {

	private static BrandsDAO bDAO;
	private static OwnersDAO oDAO;
	private static VehiclesDAO vDAO;

	public static BrandsDAO getBrandsDAO() {
		if (bDAO == null) {
			bDAO = new BrandsDAOImpl();
		}
		return bDAO;
	}

	public static OwnersDAO getOwnersDAO() {
		if (oDAO == null) {
			oDAO = new OwnersDAOImpl();
		}
		return oDAO;
	}

	public static VehiclesDAO getVehiclesDAO() {
		if (vDAO == null) {
			vDAO = new VehiclesDAOImpl();
		}
		return vDAO;
	}
}
