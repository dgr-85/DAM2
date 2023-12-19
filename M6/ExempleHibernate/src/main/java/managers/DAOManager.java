package managers;

import dao.UsuarisDAO;
import dao.UsuarisDAOImpl;

public abstract class DAOManager {
	private static UsuarisDAO uDAO;

	public static UsuarisDAO getUsuarisDAO() {
		if (uDAO != null) {
			uDAO = new UsuarisDAOImpl();
		}
		return uDAO;
	}
}
