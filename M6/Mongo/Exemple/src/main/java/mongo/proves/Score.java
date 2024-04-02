package mongo.proves;

import java.util.Objects;

public class Score {
	private String type;
	private Double score;

	public Score(String type, Double score) {
		super();
		this.type = type;
		this.score = score;
	}

	public Score() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Score other = (Score) obj;
		return Objects.equals(score, other.score) && Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "[type=" + type + ", score=" + score + "]";
	}
}
