package dao;

import com.mongodb.client.result.InsertOneResult;

import models.Movie;

public interface MoviesDAO {
	public InsertOneResult addMovie(Movie movie);

	public Movie getMovieByTitle(String title);

	public Movie updateMovie(Movie movie);

	public void deleteMovie(String title);
}
