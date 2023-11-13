package managers;

import dao.CandidateDAO;
import dao.PrizeDAO;
import dao.PrizeTypeDAO;
import dao_impl.CandidateDAOImpl;
import dao_impl.PrizeDAOImpl;
import dao_impl.PrizeTypeDAOImpl;

public abstract class DAOManager {
	private static CandidateDAO cDAO;
	private static PrizeDAO pDAO;
	private static PrizeTypeDAO ptDAO;

	public static CandidateDAO getCandidateDAO() {
		if (cDAO == null) {
			cDAO = new CandidateDAOImpl();
		}
		return cDAO;
	}

	public static PrizeDAO getPrizeDAO() {
		if (pDAO == null) {
			pDAO = new PrizeDAOImpl();
		}
		return pDAO;
	}

	public static PrizeTypeDAO getPrizeTypeDAO() {
		if (ptDAO == null) {
			ptDAO = new PrizeTypeDAOImpl();
		}
		return ptDAO;
	}
}
