package models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Movie {
	private ObjectId id;
	private String title;
	private int year;
	private String rated;
	private Integer runtime;
	private String[] countries;
	private String[] genres;
	private String director;
	private String[] writers;
	private String[] actors;
	private String plot;
	private String poster;
	private Imdb imdb;
	private Tomato tomato;
	private Rating rating;
	private int metacritic;
	@BsonProperty(value = "awards")
	private Award award;
	private String type;

	public Movie() {
		super();
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public Integer getRuntime() {
		return runtime;
	}

	public void setRuntime(Integer runtime) {
		this.runtime = runtime;
	}

	public String[] getCountries() {
		return countries;
	}

	public void setCountries(String[] countries) {
		this.countries = countries;
	}

	public String[] getGenres() {
		return genres;
	}

	public void setGenres(String[] genres) {
		this.genres = genres;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String[] getWriters() {
		return writers;
	}

	public void setWriters(String[] writers) {
		this.writers = writers;
	}

	public String[] getActors() {
		return actors;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public Imdb getImdb() {
		return imdb;
	}

	public void setImdb(Imdb imdb) {
		this.imdb = imdb;
	}

	public Tomato getTomato() {
		return tomato;
	}

	public void setTomato(Tomato tomato) {
		this.tomato = tomato;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Integer getMetacritic() {
		return metacritic;
	}

	public void setMetacritic(Integer metacritic) {
		this.metacritic = metacritic;
	}

	public Award getAward() {
		return award;
	}

	public void setAward(Award award) {
		this.award = award;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Movie [_id=" + id + ", title=" + title + ", year=" + year + ", rated=" + rated + ", runtime=" + runtime
				+ ", countries=" + countries + ", genres=" + genres + ", director=" + director + ", writers=" + writers
				+ ", actors=" + actors + ", plot=" + plot + ", poster=" + poster + ", imdb=" + imdb + ", tomato="
				+ tomato + ", rating=" + rating + ", metacritic=" + metacritic + ", award=" + award + ", type=" + type
				+ "]";
	}
}
