package managers;

import dao.AutorsDAO;
import dao.IdiomesDAO;
import dao.LlibresDAO;
import dao.impl.AutorsDAOImpl;
import dao.impl.IdiomesDAOImpl;
import dao.impl.LlibresDAOImpl;

public abstract class DAOManager {

	private static AutorsDAO aDAO;
	private static IdiomesDAO iDAO;
	private static LlibresDAO lDAO;

	public static AutorsDAO getAutorsDAO() {
		if (aDAO == null) {
			aDAO = new AutorsDAOImpl();
		}
		return aDAO;
	}

	public static IdiomesDAO getIdiomesDAO() {
		if (iDAO == null) {
			iDAO = new IdiomesDAOImpl();
		}
		return iDAO;
	}

	public static LlibresDAO getLlibresDAO() {
		if (lDAO == null) {
			lDAO = new LlibresDAOImpl();
		}
		return lDAO;
	}
}
