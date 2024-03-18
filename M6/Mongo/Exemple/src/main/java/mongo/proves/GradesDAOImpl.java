package mongo.proves;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;

public class GradesDAOImpl implements GradesDAO {

	@Override
	public void addGrade(Grade grade) {

		try (MongoClient mongoClient = MongoClients.create()) {
			MongoDatabase db = mongoClient.getDatabase("sample_training");
			MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);
			grades.insertOne(grade);
		}
	}

	@Override
	public Grade findGradeById(Integer id) {
		try {
			MongoClient mongoClient = ConnectionManager.getConnection();
			MongoDatabase db = mongoClient.getDatabase("grades");
			MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);
			Grade grade = grades.find(Filters.eq("student_id", id)).first();
			System.out.println("Grade found:\t" + grade);
			return grade;
		} catch (Exception e) {
			System.out.println("Error retrieving grade with id " + id);
			return null;
		} finally {
			ConnectionManager.closeConnection();
		}
	}

	@Override
	public Grade updateGrade(Grade grade) {
		try (MongoClient mongoClient = MongoClients.create()) {
			MongoDatabase db = mongoClient.getDatabase("sample_training");
			MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);
			List<Score> newScores = new ArrayList<>(grade.getScores());
			Score newScore = new Score();
			newScore.setType("exam");
			newScore.setScore(42d);
			newScores.add(newScore);
			grade.setScores(newScores);
			Document filterByGradeId = new Document("_id", grade.getId());
			FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions()
					.returnDocument(ReturnDocument.AFTER);
			Grade updatedGrade = grades.findOneAndReplace(filterByGradeId, grade, returnDocAfterReplace);
			System.out.println("Grade replaced:\t" + updatedGrade);
			return updatedGrade;
		} catch (Exception e) {
			System.out.println("Error updating grade " + grade);
			return null;
		}
	}

	@Override
	public void deleteGrade(Integer id) {
		try (MongoClient mongoClient = MongoClients.create()) {
			MongoDatabase db = mongoClient.getDatabase("sample_training");
			MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);
			Document filterByGradeId = new Document("_id", id);
			System.out.println(grades.deleteOne(filterByGradeId));
		}
	}

}
