package managers;

import dao.CompaniesDAO;
import dao.impl.CompaniesDAOImpl;

public class DAOManager {

	private static CompaniesDAO cDAO;

	public static CompaniesDAO getCompaniesDAO() {
		if (cDAO == null) {
			cDAO = new CompaniesDAOImpl();
		}
		return cDAO;
	}
}
