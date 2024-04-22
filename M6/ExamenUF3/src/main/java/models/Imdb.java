package models;

public class Imdb {

	private String id;
	private Rating rating;
	private Integer votes;

	public Imdb() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	@Override
	public String toString() {
		return "Imdb [id=" + id + ", rating=" + rating + ", votes=" + votes + "]";
	}

}
