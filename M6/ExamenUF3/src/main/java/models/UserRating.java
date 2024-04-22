package models;

public class UserRating {
	private String $numberDouble;

	public UserRating() {
		super();
	}

	public String get$numberDouble() {
		return $numberDouble;
	}

	public void set$numberDouble(String $numberDouble) {
		this.$numberDouble = $numberDouble;
	}

	@Override
	public String toString() {
		return "UserRating [$numberDouble=" + $numberDouble + "]";
	}

}
