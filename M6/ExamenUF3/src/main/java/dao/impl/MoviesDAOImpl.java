package dao.impl;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.InsertOneResult;

import dao.MoviesDAO;
import managers.ConnectionManager;
import models.Movie;

public class MoviesDAOImpl implements MoviesDAO {

	@Override
	public InsertOneResult addMovie(Movie movie) {
		try {
			MongoClient mongoClient = ConnectionManager.getConnection();
			MongoDatabase db = mongoClient.getDatabase("examenuf3");
			MongoCollection<Movie> movies = db.getCollection("movies", Movie.class);
			return movies.insertOne(movie);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Movie getMovieByTitle(String title) {
		try {
			MongoClient mongoClient = ConnectionManager.getConnection();
			MongoDatabase db = mongoClient.getDatabase("examenuf3");
			MongoCollection<Movie> movies = db.getCollection("movies", Movie.class);
			return movies.find(Filters.eq("title", title)).first();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Movie updateMovie(Movie movie) {
		try {
			MongoClient mongoClient = ConnectionManager.getConnection();
			MongoDatabase db = mongoClient.getDatabase("examenuf3");
			MongoCollection<Movie> movies = db.getCollection("movies", Movie.class);
			Document filterByMovieTitle = new Document("_id", movie.getId());
			FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions()
					.returnDocument(ReturnDocument.AFTER);
			return movies.findOneAndReplace(filterByMovieTitle, movie, returnDocAfterReplace);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteMovie(String title) {
		try {
			MongoClient mongoClient = ConnectionManager.getConnection();
			MongoDatabase db = mongoClient.getDatabase("examenuf3");
			MongoCollection<Movie> movies = db.getCollection("movies", Movie.class);
			Document filterByMovieTitle = new Document("title", title);
			System.out.println(movies.deleteOne(filterByMovieTitle));
		} catch (Exception e) {
			System.out.println("Error deleting Movie " + title + ".");
		}
	}

}
