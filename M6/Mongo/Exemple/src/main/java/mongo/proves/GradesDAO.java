package mongo.proves;

import com.mongodb.client.result.InsertOneResult;

public interface GradesDAO {
	public InsertOneResult addGrade(Grade grade);

	public Grade findGradeById(Double id);

	public Grade updateGrade(Grade grade);

	public void deleteGrade(Double id);
}
