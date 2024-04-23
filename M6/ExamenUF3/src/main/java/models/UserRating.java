package models;

public class UserRating {
	private String $numberDouble;

	public UserRating() {
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
		return "UserRating [$numberDouble=" + $numberDouble + "]";
	}

}
