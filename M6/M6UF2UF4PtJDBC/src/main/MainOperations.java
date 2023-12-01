package main;

import java.util.ArrayList;

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

		// Add existing candidate (causes error)
		System.out.println("==============================================");
		System.out.println("Adding new Candidate...");
		addingCandidate = new Candidate(99, "Newer", "RepeatedCandidate", "934444444", "newercandidate@gmail.com");
		resAddCandidate = cDAO.addCandidate(addingCandidate);
		if (resAddCandidate > 0) {
			System.out.println(resAddCandidate + " Candidate added.");
		} else {
			System.out.println(ErrorManager.getMessage(resAddCandidate, "This Candidate"));
		}

		// Add prize with existing type
		System.out.println("==============================================");
		System.out.println("Adding new Prize...");
		PrizeType existingPrizeType = ptDAO.getPrizeTypeById(2, false);
		Prize addingOkPrize = new Prize(10, addingCandidate, existingPrizeType, 2140);
		int resAddOkPrize = pDAO.addPrize(addingOkPrize);
		if (resAddOkPrize > 0) {
			System.out.println(resAddOkPrize + " Prize added.");
		} else {
			System.out.println(ErrorManager.getMessage(resAddOkPrize, "This Prize"));
		}

		// Add existing prize with existing type (causes error)
		System.out.println("==============================================");
		System.out.println("Adding new Prize...");
		addingOkPrize = new Prize(10, addingCandidate, existingPrizeType, 2590);
		resAddOkPrize = pDAO.addPrize(addingOkPrize);
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
		PrizeType readingPrizeType = ptDAO.getPrizeTypeById(idReadingPrizeType, false);
		if (readingPrizeType.getPrizeTypeId() != null) {
			System.out.println("Prize Type found. (Prizes not included)");
			System.out.println(readingPrizeType.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Prize Type"));
		}

		// Get prize type by id (prizes included)
		System.out.println("==============================================");
		System.out.println("Retrieving Prize Type " + idReadingPrizeType + "...");
		readingPrizeType = ptDAO.getPrizeTypeById(idReadingPrizeType, true);
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
		readingPrizeType = ptDAO.getPrizeTypeById(errorPrizeType, false);
		if (readingPrizeType.getPrizeTypeId() != null) {
			System.out.println("Prize Type found. (Prizes not included)");
			System.out.println(readingPrizeType.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Prize Type"));
		}

		// List all candidates (prizes not included)
		System.out.println("==============================================");
		System.out.println("Retrieving all Candidates...");
		ArrayList<Candidate> listCandidates = cDAO.listAllCandidates(false);
		if (listCandidates != null) {
			System.out.println(listCandidates.size() + " Candidates found. (Prizes not included)");
			for (Candidate candidate : listCandidates) {
				System.out.println(candidate.toString());
			}
		} else {
			System.out.println("No candidates found.");
		}

		// List all candidates (prizes included)
		System.out.println("==============================================");
		System.out.println("Retrieving all Candidates...");
		listCandidates = cDAO.listAllCandidates(true);
		if (listCandidates != null) {
			System.out.println(listCandidates.size() + " Candidates found. (Prizes included)");
			for (Candidate candidate : listCandidates) {
				System.out.println(candidate.toString());
			}
		} else {
			System.out.println("No candidates found.");
		}

		// Update prize
		System.out.println("==============================================");
		int idUpdatingPrize = 10;
		System.out.println("Updating Prize " + idUpdatingPrize + "...");
		Prize updatingPrize = pDAO.getPrizeById(idUpdatingPrize);
		PrizeType typeOfUpdatingPrize = ptDAO.getPrizeTypeById(3, false);
		if (updatingPrize.getPrizeId() != null && typeOfUpdatingPrize.getPrizeTypeId() != null) {
			updatingPrize.setPrizeCandidate(cDAO.getCandidateById(4, false));
			updatingPrize.setTypeOfPrize(ptDAO.getPrizeTypeById(typeOfUpdatingPrize.getPrizeTypeId(), false));
			updatingPrize.setYear(2030);
			int resUpdatePrize = pDAO.updatePrize(updatingPrize);
			if (resUpdatePrize > 0) {
				System.out.println(resUpdatePrize + " Prize updated.");
			} else {
				System.out.println(ErrorManager.getMessage(0, "This Prize"));
			}
		} else if (typeOfUpdatingPrize.getPrizeTypeId() == null) {
			System.out.println(ErrorManager.getMessage(-1452, "This Prize"));
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Prize"));
		}

		// Update prize with non-existing prize type (causes error)
		System.out.println("==============================================");
		idUpdatingPrize = 10;
		System.out.println("Updating Prize " + idUpdatingPrize + "...");
		updatingPrize = pDAO.getPrizeById(idUpdatingPrize);
		typeOfUpdatingPrize = ptDAO.getPrizeTypeById(5353, false);
		if (updatingPrize.getPrizeId() != null && typeOfUpdatingPrize.getPrizeTypeId() != null) {
			updatingPrize.setPrizeCandidate(cDAO.getCandidateById(4, false));
			updatingPrize.setTypeOfPrize(ptDAO.getPrizeTypeById(typeOfUpdatingPrize.getPrizeTypeId(), false));
			updatingPrize.setYear(2030);
			int resUpdatePrize = pDAO.updatePrize(updatingPrize);
			if (resUpdatePrize > 0) {
				System.out.println(resUpdatePrize + " Prize updated.");
			} else {
				System.out.println(ErrorManager.getMessage(0, "This Prize"));
			}
		} else if (typeOfUpdatingPrize.getPrizeTypeId() == null) {
			System.out.println(ErrorManager.getMessage(-1452, "This Prize"));
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Prize"));
		}

		// Update non-existing prize (causes error)
		System.out.println("==============================================");
		idUpdatingPrize = 1919;
		System.out.println("Updating Prize " + idUpdatingPrize + "...");
		updatingPrize = pDAO.getPrizeById(idUpdatingPrize);
		if (updatingPrize.getPrizeId() != null) {
			updatingPrize.setPrizeCandidate(cDAO.getCandidateById(4, false));
			updatingPrize.setTypeOfPrize(ptDAO.getPrizeTypeById(4, false));
			updatingPrize.setYear(2030);
			int resUpdatePrize = pDAO.updatePrize(updatingPrize);
			if (resUpdatePrize > 0) {
				System.out.println(resUpdatePrize + " Prize updated.");
			} else {
				System.out.println(ErrorManager.getMessage(0, "This Prize"));
			}
		} else {
			System.out.println(ErrorManager.getMessage(-1, "This Prize"));
		}

		// Delete candidate
		System.out.println("==============================================");
		int idDeletingCandidate = 99;
		System.out.println("Deleting Candidate " + idDeletingCandidate + "...");
		int resDeleteCandidate = cDAO.deleteCandidate(idDeletingCandidate);
		if (resDeleteCandidate > 0) {
			System.out.println(resDeleteCandidate + " Candidate deleted.");
		} else {
			System.out.println(ErrorManager.getMessage(resDeleteCandidate, "This Candidate"));
		}

		// Delete non-existing candidate (causes error)
		System.out.println("==============================================");
		idDeletingCandidate = 2626;
		System.out.println("Deleting Candidate " + idDeletingCandidate + "...");
		resDeleteCandidate = cDAO.deleteCandidate(idDeletingCandidate);
		if (resDeleteCandidate > 0) {
			System.out.println(resDeleteCandidate + " Candidate deleted.");
		} else {
			System.out.println(ErrorManager.getMessage(resDeleteCandidate, "This Candidate"));
		}

		// Delete prize type
		System.out.println("==============================================");
		int idDeletingPrizeType = 4;
		System.out.println("Deleting Prize Type " + idDeletingPrizeType + "...");
		int resDeletePrizeType = ptDAO.deletePrizeType(idDeletingPrizeType);
		if (resDeletePrizeType > 0) {
			System.out.println(resDeletePrizeType + " Prize Type deleted.");
		} else {
			System.out.println(ErrorManager.getMessage(resDeletePrizeType, "This Prize Type"));
		}

		// Delete prize type with foreign references (causes error)
		System.out.println("==============================================");
		idDeletingPrizeType = 1;
		System.out.println("Deleting Prize Type " + idDeletingPrizeType + "...");
		resDeletePrizeType = ptDAO.deletePrizeType(idDeletingPrizeType);
		if (resDeletePrizeType > 0) {
			System.out.println(resDeletePrizeType + " Prize Type deleted.");
		} else {
			System.out.println(ErrorManager.getMessage(resDeletePrizeType, "This Prize Type"));
		}

		// Delete non-existing prize type (causes error)
		System.out.println("==============================================");
		idDeletingPrizeType = 1010;
		System.out.println("Deleting Prize Type " + idDeletingPrizeType + "...");
		resDeletePrizeType = ptDAO.deletePrizeType(idDeletingPrizeType);
		if (resDeletePrizeType > 0) {
			System.out.println(resDeletePrizeType + " Prize Type deleted.");
		} else {
			System.out.println(ErrorManager.getMessage(resDeletePrizeType, "This Prize Type"));
		}
	}
}
