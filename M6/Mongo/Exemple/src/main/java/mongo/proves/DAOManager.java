package mongo.proves;

public class DAOManager {

	private static GradesDAO gDAO;

	public static GradesDAO getGradesDAO() {
		if (gDAO == null) {
			gDAO = new GradesDAOImpl();
		}
		return gDAO;
	}

}
