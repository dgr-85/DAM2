package main;

import dao.CandidateDAO;
import dao.PrizeDAO;
import dao.PrizeTypeDAO;
import managers.DAOManager;

public class MainOperations {
	public static void main(String[] args) {
		CandidateDAO cDAO = DAOManager.getCandidateDAO();
		PrizeDAO pDAO = DAOManager.getPrizeDAO();
		PrizeTypeDAO ptDAO = DAOManager.getPrizeTypeDAO();
		// TODO Add candidate
		// TODO Add prize
		// TODO Get candidate by id (includePrizes true/false)
		// TODO Get prize type by id (includePrizes true/false)
		// TODO List all candidates (includePrizes true/false)
		// TODO Update prize
		// TODO Delete candidate
		// TODO Delete prize type

	}
}
