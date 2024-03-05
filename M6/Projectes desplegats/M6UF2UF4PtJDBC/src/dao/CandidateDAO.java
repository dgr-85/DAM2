package dao;

import java.util.ArrayList;

import model.Candidate;

public interface CandidateDAO {
	public Integer addCandidate(Candidate c);

	public Candidate getCandidateById(int id, Boolean includePrizes);

	public Integer updateCandidate(Candidate c);

	public Integer deleteCandidate(int id);

	public ArrayList<Candidate> listAllCandidates(Boolean includePrizes);
}
