package model;

public class Prize {
	private Integer prizeId;
	private Candidate prizeCandidate;
	private PrizeType typeOfPrize;
	private Integer year;

	public Prize() {

	}

	public Prize(Integer prizeId, Candidate prizeCandidate, PrizeType prizeTypeId, Integer year) {
		super();
		this.prizeId = prizeId;
		this.prizeCandidate = prizeCandidate;
		this.typeOfPrize = prizeTypeId;
		this.year = year;
	}

	public Integer getPrizeId() {
		return prizeId;
	}

	public void setPrizeId(Integer prizeId) {
		this.prizeId = prizeId;
	}

	public Candidate getPrizeCandidate() {
		return prizeCandidate;
	}

	public void setPrizeCandidate(Candidate prizeCandidate) {
		this.prizeCandidate = prizeCandidate;
	}

	public PrizeType getTypeOfPrize() {
		return typeOfPrize;
	}

	public void setTypeOfPrize(PrizeType typeOfPrize) {
		this.typeOfPrize = typeOfPrize;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Prize [Id=" + prizeId + ", Candidate=" + prizeCandidate + ", Type=" + typeOfPrize + ", Year=" + year
				+ "]";
	}

}
