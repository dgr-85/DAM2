package dao;

public class DAOManager {
	private static DepartamentDAO depDAO;


	public static DepartamentDAO getDepDAO() {
		if(depDAO == null){ 
			depDAO = new DepartamentDAOImpl();
		}
		return depDAO;
	}
	
}
