package model;

import java.util.ArrayList;

public class PrizeType {
	private Integer prizeTypeId;
	private String prizeName;
	private String prizeDescription;
	private double prizeValue;
	ArrayList<Prize> prizes;

	public PrizeType() {

	}

	public PrizeType(Integer prizeTypeId, String prizeName, String prizeDescription, double prizeValue) {
		super();
		this.prizeTypeId = prizeTypeId;
		this.prizeName = prizeName;
		this.prizeDescription = prizeDescription;
		this.prizeValue = prizeValue;
		this.prizes = null;
	}

	public Integer getPrizeTypeId() {
		return prizeTypeId;
	}

	public void setPrizeTypeId(Integer prizeTypeId) {
		this.prizeTypeId = prizeTypeId;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getPrizeDescription() {
		return prizeDescription;
	}

	public void setPrizeDescription(String prizeDescription) {
		this.prizeDescription = prizeDescription;
	}

	public double getPrizeValue() {
		return prizeValue;
	}

	public void setPrizeValue(double prizeValue) {
		this.prizeValue = prizeValue;
	}

	public ArrayList<Prize> getPrizes() {
		return prizes;
	}

	public void setPrizes(ArrayList<Prize> prizes) {
		this.prizes = prizes;
	}

	@Override
	public String toString() {
		return "PrizeType [Id=" + prizeTypeId + ", Name=" + prizeName + ", Description=" + prizeDescription + ", Value="
				+ prizeValue + ", Prizes=" + prizes + "]";
	}

}
