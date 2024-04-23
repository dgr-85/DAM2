package models;

public class Rating {
	private String $numberDouble;

	public Rating() {
		super();
	}

	public String getNumberDouble() {
		return $numberDouble;
	}

	public void setNumberDouble(String $numberDouble) {
		this.$numberDouble = $numberDouble;
	}

	@Override
	public String toString() {
		return "Rating [numberDouble=" + $numberDouble + "]";
	}

}
