package model;

public class Prize {
	private Integer prizeId;
	private Candidate prizeCandidate;
	private PrizeType prizeTypeId;
	private Integer year;

	public Prize() {

	}

	public Prize(Integer prizeId, Candidate candidateId, PrizeType prizeTypeId, Integer year) {
		super();
		this.prizeId = prizeId;
		this.prizeCandidate = candidateId;
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
		return prizeCandidate;
	}

	public void setCandidateId(Candidate candidateId) {
		this.prizeCandidate = candidateId;
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

	@Override
	public String toString() {
		return "Prize [prizeId=" + prizeId + ", candidateId=" + prizeCandidate + ", prizeTypeId=" + prizeTypeId + ", year="
				+ year + "]";
	}

}
