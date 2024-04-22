package models;

public class Tomato {
	private Integer meter;
	private String image;
	private Rating rating;
	private Integer reviews;
	private Integer fresh;
	private String consensus;
	private Integer userMeter;
	private UserRating userRating;
	private Integer userReviews;

	public Tomato() {
		super();
	}

	public Integer getMeter() {
		return meter;
	}

	public void setMeter(Integer meter) {
		this.meter = meter;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Integer getReviews() {
		return reviews;
	}

	public void setReviews(Integer reviews) {
		this.reviews = reviews;
	}

	public Integer getFresh() {
		return fresh;
	}

	public void setFresh(Integer fresh) {
		this.fresh = fresh;
	}

	public String getConsensus() {
		return consensus;
	}

	public void setConsensus(String consensus) {
		this.consensus = consensus;
	}

	public Integer getUserMeter() {
		return userMeter;
	}

	public void setUserMeter(Integer userMeter) {
		this.userMeter = userMeter;
	}

	public UserRating getUserRating() {
		return userRating;
	}

	public void setUserRating(UserRating userRating) {
		this.userRating = userRating;
	}

	public Integer getUserReviews() {
		return userReviews;
	}

	public void setUserReviews(Integer userReviews) {
		this.userReviews = userReviews;
	}

	@Override
	public String toString() {
		return "Tomato [meter=" + meter + ", image=" + image + ", rating=" + rating + ", reviews=" + reviews
				+ ", fresh=" + fresh + ", consensus=" + consensus + ", userMeter=" + userMeter + ", userRating="
				+ userRating + ", userReviews=" + userReviews + "]";
	}
}
