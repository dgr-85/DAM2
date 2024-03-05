package managers;

import dao.DepartamentsDAO;
import dao.DepartamentsDAOImpl;
import dao.EmpleatsDAO;
import dao.EmpleatsDAOImpl;
import dao.UsuarisDAO;
import dao.UsuarisDAOImpl;

public abstract class DAOManager {
	private static UsuarisDAO uDAO;
	private static EmpleatsDAO eDAO;
	private static DepartamentsDAO dDAO;

	public static UsuarisDAO getUsuarisDAO() {
		if (uDAO == null) {
			uDAO = new UsuarisDAOImpl();
		}
		return uDAO;
	}

	public static EmpleatsDAO getEmpleatsDAO() {
		if (eDAO == null) {
			eDAO = new EmpleatsDAOImpl();
		}
		return eDAO;
	}

	public static DepartamentsDAO getDepartamentsDAO() {
		if (dDAO == null) {
			dDAO = new DepartamentsDAOImpl();
		}
		return dDAO;
	}
}
