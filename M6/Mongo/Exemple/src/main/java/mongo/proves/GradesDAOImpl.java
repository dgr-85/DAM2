package mongo.proves;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.InsertOneResult;

public class GradesDAOImpl implements GradesDAO {

	// Connexions a MongoDB són costoses, millor tancar-la 1 sol cop al final del
	// main

	@Override
	public InsertOneResult addGrade(Grade grade) {
		try {
			MongoClient mongoClient = ConnectionManager.getConnection();
			MongoDatabase db = mongoClient.getDatabase("grades");
			MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);
			return grades.insertOne(grade);
		} catch (Exception e) {
			System.out.println("Error adding Grade with id " + grade.getStudentId() + ".");
			return null;
		}
	}

	@Override
	public Grade findGradeById(Double id) {
		try {
			MongoClient mongoClient = ConnectionManager.getConnection();
			MongoDatabase db = mongoClient.getDatabase("grades");
			MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);
			Grade grade = grades.find(Filters.eq("student_id", id)).first();
			return grade;
		} catch (Exception e) {
			System.out.println("Error retrieving grade with id " + id + ".");
			return null;
		}
	}

	@Override
	public Grade updateGrade(Grade grade) {
		try {
			MongoClient mongoClient = ConnectionManager.getConnection();
			MongoDatabase db = mongoClient.getDatabase("grades");
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
			System.out.println("Error updating Grade " + grade);
			return null;
		}
	}

	@Override
	public void deleteGrade(Double id) {
		try {
			MongoClient mongoClient = ConnectionManager.getConnection();
			MongoDatabase db = mongoClient.getDatabase("grades");
			MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);
			Document filterByGradeId = new Document("_id", id);
			System.out.println(grades.deleteOne(filterByGradeId));
		} catch (Exception e) {
			System.out.println("Error deleting Grade with id " + id + ".");
		}
	}

}
