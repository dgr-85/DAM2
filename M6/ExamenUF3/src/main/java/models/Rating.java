package models;

public class Rating {
	private String $numberDouble;

	public Rating() {
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
		return "Rating [$numberDouble=" + $numberDouble + "]";
	}

}
