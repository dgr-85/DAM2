package mongo.proves;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.result.InsertOneResult;

public class MainOperations {
	public static void main(String[] args) {

		/*
		 * Create sense paràmetres connecta a localhost. Si volem connectar a un altre
		 * se li ha de passar per paràmetres el string de connexió.:
		 * MongoClients.create(
		 * "mongodb+srv://m001-student:m001-student@marianao.zmw5t.mongodb.net/?retryWrites=true&w=majority")
		 */

		// String connectionString = System.getProperty("mongodb.uri");
		try (MongoClient mongoClient = MongoClients.create(/* connectionString */)) {
			System.out.println("=> Connection successful: " + preFlightChecks(mongoClient));
			System.out.println("=> Print list of databases:");
			List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
			databases.forEach(db -> System.out.println(db.toJson()));
		}

		// MongoClient con = ConnectionManager.getConnection();

		// Add new Grade
		System.out.println("Adding new Grade...");
		GradesDAO gDAO = DAOManager.getGradesDAO();
		Grade newGrade = new Grade();
		newGrade.setStudentId(10003d);
		newGrade.setClassId(10d);
		Score newScore = new Score();
		newScore.setType("homework");
		newScore.setScore(50d);
		newGrade.setScores(Arrays.asList(newScore));
		InsertOneResult addResult = gDAO.addGrade(newGrade);
		if (addResult != null) {
			System.out.println("Grade added successfully.");
		}

		// Get Grade by Id
		Double idFindGrade = 10003d;
		System.out.println("Retrieving Grade with id " + idFindGrade + "...");
		Grade retrievedGrade = gDAO.findGradeById(idFindGrade);
		if (retrievedGrade != null) {
			System.out.println("Grade found: " + retrievedGrade.toString());
		} else {
			System.out.println("Grade not found.");
		}

		// Update Grade
		System.out.println("Updating Grade with id " + idFindGrade + "...");
		Grade updatedGrade = gDAO.updateGrade(retrievedGrade);
		if (updatedGrade != null) {
			System.out.println("Grade updated: " + updatedGrade.toString());
		} else {
			System.out.println("Error updating Grade.");
		}

		// Delete Grade
		System.out.println("Deleting Grade with id " + idFindGrade + "...");
		gDAO.deleteGrade(idFindGrade);

		ConnectionManager.closeConnection();
	}

	static boolean preFlightChecks(MongoClient mongoClient) {
		Document pingCommand = new Document("ping", 1);
		Document response = mongoClient.getDatabase("admin").runCommand(pingCommand);
		System.out.println("=> Print result of the '{ping: 1}' command.");
		System.out.println(response.toJson(JsonWriterSettings.builder().indent(true).build()));
		return response.get("ok", Number.class).intValue() == 1;
	}

}
