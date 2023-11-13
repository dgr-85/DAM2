package model;

public class Prize {
	private Integer prizeId;
	private Candidate candidateId;
	private PrizeType prizeTypeId;
	private Integer year;

	public Prize() {

	}

	public Prize(Integer prizeId, Candidate candidateId, PrizeType prizeTypeId, Integer year) {
		super();
		this.prizeId = prizeId;
		this.candidateId = candidateId;
		this.prizeTypeId = prizeTypeId;
		this.year = year;
	}

	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	public Candidate getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Candidate candidateId) {
		this.candidateId = candidateId;
	}

	public PrizeType getPrizeTypeId() {
		return prizeTypeId;
	}

	public void setPrizeTypeId(PrizeType prizeTypeId) {
		this.prizeTypeId = prizeTypeId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}
