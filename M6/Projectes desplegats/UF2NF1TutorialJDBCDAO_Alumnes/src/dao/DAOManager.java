package dao;

public abstract class DAOManager {
	private static DepartamentDAO depDAO;
	private static EmpleatDAO empDAO;

	public static DepartamentDAO getDepDAO() {
		if (depDAO == null) {
			depDAO = new DepartamentDAOImpl();
		}
		return depDAO;
	}

	public static EmpleatDAO getEmpDAO() {
		if (empDAO == null) {
			empDAO = new EmpleatDAOImpl();
		}
		return empDAO;
	}

}
