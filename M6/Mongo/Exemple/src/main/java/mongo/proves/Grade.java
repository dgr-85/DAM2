package mongo.proves;

import java.util.List;
import java.util.Objects;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Grade {
	private ObjectId id;
	@BsonProperty(value = "student_id")
	private Double studentId;
	@BsonProperty(value = "class_id")
	private Double classId;
	private List<Score> scores;

	public Grade(ObjectId id, Double studentId, Double classId, List<Score> scores) {
		super();
		this.id = id;
		this.studentId = studentId;
		this.classId = classId;
		this.scores = scores;
	}

	public Grade() {
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Double getStudentId() {
		return studentId;
	}

	public void setStudentId(Double studentId) {
		this.studentId = studentId;
	}

	public Double getClassId() {
		return classId;
	}

	public void setClassId(Double classId) {
		this.classId = classId;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	@Override
	public int hashCode() {
		return Objects.hash(classId, id, scores, studentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grade other = (Grade) obj;
		return Objects.equals(classId, other.classId) && Objects.equals(id, other.id)
				&& Objects.equals(scores, other.scores) && Objects.equals(studentId, other.studentId);
	}

	@Override
	public String toString() {
		return "Grade [id=" + id + ", studentId=" + studentId + ", classId=" + classId + ", scores=" + scores + "]";
	}

}
