package main;

import dao.CandidateDAO;
import dao.PrizeDAO;
import dao.PrizeTypeDAO;
import managers.DAOManager;
import managers.ErrorManager;
import model.Candidate;
import model.Prize;
import model.PrizeType;

public class MainOperations {
	public static void main(String[] args) {

		CandidateDAO cDAO = DAOManager.getCandidateDAO();
		PrizeDAO pDAO = DAOManager.getPrizeDAO();
		PrizeTypeDAO ptDAO = DAOManager.getPrizeTypeDAO();

		// Add candidate
		System.out.println("==============================================");
		System.out.println("Adding new Candidate...");
		Candidate addingCandidate = new Candidate(99, "New", "Candidate", "931111111", "newcandidate@gmail.com");
		int resAddCandidate = cDAO.addCandidate(addingCandidate);
		if (resAddCandidate > 0) {
			System.out.println(resAddCandidate + " Candidate added.");
		} else {
			System.out.println(ErrorManager.getMessage(resAddCandidate, "This Candidate"));
		}

		// Add prize with existing type
		System.out.println("==============================================");
		System.out.println("Adding new Prize...");
		PrizeType existingPrizeType = ptDAO.getPrizetypeById(2, false);
		Prize addingOkPrize = new Prize(10, addingCandidate, existingPrizeType, 2140);
		int resAddOkPrize = pDAO.addPrize(addingOkPrize);
		if (resAddOkPrize > 0) {
			System.out.println(resAddOkPrize + " Prize added.");
		} else {
			System.out.println(ErrorManager.getMessage(resAddOkPrize, "This Prize"));
		}

		// Add prize with non-existing type (causes error)
		System.out.println("==============================================");
		System.out.println("Adding new Prize...");
		Candidate nonExistingCandidate = new Candidate(101, "NotSoNew", "CandidateVoid", "932222222",
				"nocandidate@gmail.com");
		PrizeType nonExistingPrizeType = new PrizeType(99, "Wrong", "Error", 99.99);
		Prize addingErrorPrize = new Prize(12, nonExistingCandidate, nonExistingPrizeType, 2139);

		int resAddErrorPrize = pDAO.addPrize(addingErrorPrize);
		if (resAddErrorPrize > 0) {
			System.out.println(resAddErrorPrize + " Prize added.");
		} else {
			System.out.println(ErrorManager.getMessage(resAddErrorPrize, "This Prize"));
		}

		// Get candidate by id (prizes not included)
		System.out.println("==============================================");
		int idReadingCandidate = 99;
		System.out.println("Retrieving Candidate " + idReadingCandidate + "...");
		Candidate readingCandidate = cDAO.getCandidateById(idReadingCandidate, false);
		if (readingCandidate.getCandidateId() != null) {
			System.out.println("Candidate found. (Prizes not included)");
			System.out.println(readingCandidate.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Candidate"));
		}

		// Get candidate by id (prizes included)
		System.out.println("==============================================");
		System.out.println("Retrieving Candidate " + idReadingCandidate + "...");
		readingCandidate = cDAO.getCandidateById(idReadingCandidate, true);
		if (readingCandidate.getCandidateId() != null) {
			System.out.println("Candidate found. (Prizes included)");
			System.out.println(readingCandidate.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Candidate"));
		}

		// Get candidate by non-existing id (causes error)
		System.out.println("==============================================");
		int errorCandidate = 5454;
		System.out.println("Retrieving Candidate " + errorCandidate + "...");
		readingCandidate = cDAO.getCandidateById(errorCandidate, false);
		if (readingCandidate.getCandidateId() != null) {
			System.out.println("Candidate found. (Prizes not included)");
			System.out.println(readingCandidate.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Candidate"));
		}

		// Get prize type by id (prizes not included)
		System.out.println("==============================================");
		int idReadingPrizeType = 3;
		System.out.println("Retrieving Prize Type " + idReadingPrizeType + "...");
		PrizeType readingPrizeType = ptDAO.getPrizetypeById(idReadingPrizeType, false);
		if (readingPrizeType.getPrizeTypeId() != null) {
			System.out.println("Prize Type found. (Prizes not included)");
			System.out.println(readingPrizeType.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Prize Type"));
		}

		// Get prize type by id (prizes included)
		System.out.println("==============================================");
		System.out.println("Retrieving Prize Type " + idReadingPrizeType + "...");
		readingPrizeType = ptDAO.getPrizetypeById(idReadingPrizeType, true);
		if (readingPrizeType.getPrizeTypeId() != null) {
			System.out.println("Prize Type found. (Prizes included)");
			System.out.println(readingPrizeType.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Prize Type"));
		}

		// Get prize type by non-existing id (causes error)
		System.out.println("==============================================");
		int errorPrizeType = 6767;
		System.out.println("Retrieving Prize Type " + errorPrizeType + "...");
		readingPrizeType = ptDAO.getPrizetypeById(errorPrizeType, false);
		if (readingPrizeType.getPrizeTypeId() != null) {
			System.out.println("Prize Type found. (Prizes not included)");
			System.out.println(readingPrizeType.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Prize Type"));
		}
		// TODO List all candidates (includePrizes true/false)
		// TODO Update prize
		// TODO Delete candidate
		// TODO Delete prize type

	}
}
