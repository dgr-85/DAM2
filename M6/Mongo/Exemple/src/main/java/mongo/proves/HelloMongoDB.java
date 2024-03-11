package mongo.proves;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class HelloMongoDB {
	public static void main(String[] args) {

		/*
		 * Create sense paràmetres connecta a localhost. Si volem connectar a un altre
		 * se li ha de passar per paràmetres el string de connexió.:
		 * MongoClients.create(
		 * "mongodb+srv://m001-student:m001-student@marianao.zmw5t.mongodb.net/?retryWrites=true&w=majority")
		 */
//		try (MongoClient mongoClient = MongoClients.create()) {
//
//			List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
//			databases.forEach(db -> System.out.println(db.toJson()));
//
//		}

		String connectionString = System.getProperty("mongodb.uri");
		try (MongoClient mongoClient = MongoClients.create(/* connectionString */)) {
			System.out.println("=> Connection successful: " + preFlightChecks(mongoClient));
			System.out.println("=> Print list of databases:");
			List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
			databases.forEach(db -> System.out.println(db.toJson()));
		}
	}

	static boolean preFlightChecks(MongoClient mongoClient) {
		Document pingCommand = new Document("ping", 1);
		Document response = mongoClient.getDatabase("admin").runCommand(pingCommand);
		System.out.println("=> Print result of the '{ping: 1}' command.");
		System.out.println(response.toJson(JsonWriterSettings.builder().indent(true).build()));
		return response.get("ok", Number.class).intValue() == 1;
	}

}
