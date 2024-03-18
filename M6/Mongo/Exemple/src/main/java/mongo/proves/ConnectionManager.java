package mongo.proves;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class ConnectionManager {

	private static MongoClient connection = null;

	private static int connect() {

		/*
		 * Create sense paràmetres connecta a localhost. Si volem connectar a un altre
		 * se li ha de passar per paràmetres el string de connexió.:
		 * MongoClients.create(
		 * "mongodb+srv://m001-student:m001-student@marianao.zmw5t.mongodb.net/?retryWrites=true&w=majority")
		 */

		ConnectionString connectionString = new ConnectionString(System.getProperty("mongodb://localhost:27017"));

		CodecRegistry pojoCodecRegistry = CodecRegistries
				.fromProviders(PojoCodecProvider.builder().automatic(true).build());

		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);

		MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();

		connection = MongoClients.create(clientSettings);

		return 1;
	}

	public static MongoClient getConnection() {
		if (connection == null) {
			connect();
		}
		return connection;
	}

	public static void closeConnection() {
		connection.close();
	}

}
