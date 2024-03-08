package mongo.proves;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MappingPOJOs {
	ConnectionString connectionString = new ConnectionString(System.getProperty("mongodb.uri"));

	CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());

	CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

	MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
			.codecRegistry(codecRegistry).build();

	try(
	MongoClient mongoClient = MongoClients.create(clientSettings))
	{
		MongoDatabase db = mongoClient.getDatabase("grades");
		MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);
	}

	private CodecRegistry fromProviders(PojoCodecProvider pojoCodecProvider) {
		// TODO Auto-generated method stub
		return null;
	}

	private CodecRegistry fromRegistries(CodecRegistry defaultCodecRegistry, CodecRegistry pojoCodecRegistry2) {
		// TODO Auto-generated method stub
		return null;
	}

}
