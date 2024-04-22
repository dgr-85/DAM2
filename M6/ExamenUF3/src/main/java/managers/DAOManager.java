package managers;

import dao.MoviesDAO;
import dao.impl.MoviesDAOImpl;

public class DAOManager {

	private static MoviesDAO mDAO;

	public static MoviesDAO getMoviesDAO() {
		if (mDAO == null) {
			mDAO = new MoviesDAOImpl();
		}
		return mDAO;
	}

}
