package main;

import com.mongodb.client.result.InsertOneResult;

import dao.MoviesDAO;
import managers.ConnectionManager;
import managers.DAOManager;
import models.Imdb;
import models.Movie;
import models.Rating;
import models.Tomato;
import models.UserRating;

public class MainOperations {

	public static void main(String[] args) {
		MoviesDAO mDAO = DAOManager.getMoviesDAO();

		// Add new Movie
		System.out.println("Adding new Movie...");
		Movie newMovie = new Movie();
		newMovie.setTitle("The Great Exam Carnage");

		String[] actors = { "Christine Now, Alex Ruralhouse, Andrew Johnson, Tony Tanned" };
		newMovie.setActors(actors);

		Imdb imdb = new Imdb();
		imdb.setId("rip2024");
		Rating rating = new Rating();
		rating.set$numberDouble("4.9");
		imdb.setRating(rating);
		imdb.setVotes(4);
		newMovie.setImdb(imdb);

		Tomato tomato = new Tomato();
		tomato.setConsensus("Great movie");
		rating.set$numberDouble("5.5");
		tomato.setRating(rating);

		UserRating userRating = new UserRating();
		userRating.set$numberDouble("1.2");
		tomato.setUserRating(userRating);
		newMovie.setTomato(tomato);

		InsertOneResult addResult = mDAO.addMovie(newMovie);
		if (addResult != null) {
			System.out.println("New Movie added succesfully.");
		} else {
			System.out.println("Error adding Movie.");
		}

		// Get existing Movie by name
		String getByExistingTitle = "Journey to the West";
		System.out.println("Retrieving Movie with name " + getByExistingTitle + "...");
		Movie existingMovie = mDAO.getMovieByTitle(getByExistingTitle);
		if (existingMovie != null) {
			System.out.println("Movie found: " + existingMovie.toString());
		} else {
			System.out.println("Movie not found.");
		}

		// Get added Movie by name
		String getByTitle = "The Great Exam Carnage";
		System.out.println("Retrieving Movie with name " + getByTitle + "...");
		Movie retrievedMovie = mDAO.getMovieByTitle(getByTitle);
		if (retrievedMovie != null) {
			System.out.println("Movie found: " + retrievedMovie.toString());
		} else {
			System.out.println("Movie not found.");
		}

		// Get non-existing Movie by name (causes error)
		String getByWrongTitle = "The Great Exam Graduation";
		System.out.println("Retrieving Movie with name " + getByWrongTitle + "...");
		Movie retrievedWrongMovie = mDAO.getMovieByTitle(getByWrongTitle);
		if (retrievedWrongMovie != null) {
			System.out.println("Movie found: " + retrievedWrongMovie.toString());
		} else {
			System.out.println("Movie not found.");
		}

		// Update Movie
		System.out.println("Updating overview of Movie " + retrievedMovie.getTitle() + "...");
		newMovie.setPlot(
				"A group of cruel teachers have decided to kill an entire classroom's future. Will they succeed?");
		retrievedMovie = mDAO.updateMovie(retrievedMovie);
		if (retrievedMovie != null) {
			System.out.println("Movie updated. New plot: " + retrievedMovie.getPlot());
			System.out.println("Full Movie data, for reference: " + retrievedMovie.toString());
		} else {
			System.out.println("Error updating Movie. (maybe it doesn't exist?)");
		}

		// Update non-existing Movie (causes error)
		System.out.println("Updating overview of Movie " + getByWrongTitle + "...");
		newMovie.setPlot(
				"A group of kind teachers have decided to promote an entire classroom's future. Will they succeed?");
		retrievedWrongMovie = mDAO.updateMovie(retrievedWrongMovie);
		if (retrievedWrongMovie != null) {
			System.out.println("Movie updated. New plot: " + retrievedWrongMovie.getPlot());
		} else {
			System.out.println("Error updating Movie. (maybe it doesn't exist?)");
		}

		// Delete Movie by name
		System.out.println("Deleting Movie " + retrievedMovie.getTitle() + "...");
		mDAO.deleteMovie(getByTitle);

		// Delete non-existing Movie by name (nothing happens)
		System.out.println("Deleting Movie " + getByWrongTitle + "...");
		mDAO.deleteMovie(getByWrongTitle);

		ConnectionManager.closeConnection();
	}

}
