package models;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Company {
	private ObjectId id; // només el document arrel de Mongo l'ha de dur
	private String name;
	private String permalink;
	@BsonProperty(value = "homepage_url")
	private String homepageUrl;
	@BsonProperty(value = "blog_url")
	private String blogUrl;
	@BsonProperty(value = "blog_feed_url")
	private String blogFeedUrl;
	@BsonProperty(value = "twitter_username")
	private String twitterUsername;
	@BsonProperty(value = "category_code")
	private String categoryCode;
	@BsonProperty(value = "number_of_employees")
	private Integer numberOfEmployees;
	@BsonProperty(value = "founded_year")
	private Integer foundedYear;
	@BsonProperty(value = "tag_list")
	private String tagList;
	@BsonProperty(value = "alias_list")
	private String aliasList;
	@BsonProperty(value = "email_address")
	private String emailAddress;
	@BsonProperty(value = "phone_number")
	private String phoneNumber;
	private String description;
	private String overview;
	private List<Product> products;
	private List<Relationship> relationships;
	private List<Competition> competitions;
	@BsonProperty(value = "total_money_raised")
	private String totalMoneyRaised;
	private List<Office> offices;
	@BsonProperty(value = "founded_month")
	private Integer foundedMonth;
	@BsonProperty(value = "founded_day")
	private Integer foundedDay;

	public Company() {
		super();
	}

	public Company(ObjectId id, String name, String permalink, String homepageUrl, String blogUrl, String blogFeedUrl,
			String twitterUsername, String categoryCode, Integer numberOfEmployees, Integer foundedYear, String tagList,
			String aliasList, String emailAddress, String phoneNumber, String description, String overview,
			List<Product> products, List<Relationship> relationships, List<Competition> competitions,
			String totalMoneyRaised, List<Office> offices, Integer foundedMonth, Integer foundedDay) {
		super();
		this.id = id;
		this.name = name;
		this.permalink = permalink;
		this.homepageUrl = homepageUrl;
		this.blogUrl = blogUrl;
		this.blogFeedUrl = blogFeedUrl;
		this.twitterUsername = twitterUsername;
		this.categoryCode = categoryCode;
		this.numberOfEmployees = numberOfEmployees;
		this.foundedYear = foundedYear;
		this.tagList = tagList;
		this.aliasList = aliasList;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.description = description;
		this.overview = overview;
		this.products = products;
		this.relationships = relationships;
		this.competitions = competitions;
		this.totalMoneyRaised = totalMoneyRaised;
		this.offices = offices;
		this.foundedMonth = foundedMonth;
		this.foundedDay = foundedDay;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getHomepageUrl() {
		return homepageUrl;
	}

	public void setHomepageUrl(String homepageUrl) {
		this.homepageUrl = homepageUrl;
	}

	public String getBlogUrl() {
		return blogUrl;
	}

	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}

	public String getBlogFeedUrl() {
		return blogFeedUrl;
	}

	public void setBlogFeedUrl(String blogFeedUrl) {
		this.blogFeedUrl = blogFeedUrl;
	}

	public String getTwitterUsername() {
		return twitterUsername;
	}

	public void setTwitterUsername(String twitterUsername) {
		this.twitterUsername = twitterUsername;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Integer getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public void setNumberOfEmployees(Integer numberOfEmployees) {
		this.numberOfEmployees = numberOfEmployees;
	}

	public Integer getFoundedYear() {
		return foundedYear;
	}

	public void setFoundedYear(Integer foundedYear) {
		this.foundedYear = foundedYear;
	}

	public String getTagList() {
		return tagList;
	}

	public void setTagList(String tagList) {
		this.tagList = tagList;
	}

	public String getAliasList() {
		return aliasList;
	}

	public void setAliasList(String aliasList) {
		this.aliasList = aliasList;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}

	public List<Competition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(List<Competition> competitions) {
		this.competitions = competitions;
	}

	public String getTotalMoneyRaised() {
		return totalMoneyRaised;
	}

	public void setTotalMoneyRaised(String totalMoneyRaised) {
		this.totalMoneyRaised = totalMoneyRaised;
	}

	public List<Office> getOffices() {
		return offices;
	}

	public void setOffices(List<Office> offices) {
		this.offices = offices;
	}

	public Integer getFoundedMonth() {
		return foundedMonth;
	}

	public void setFoundedMonth(Integer foundedMonth) {
		this.foundedMonth = foundedMonth;
	}

	public Integer getFoundedDay() {
		return foundedDay;
	}

	public void setFoundedDay(Integer foundedDay) {
		this.foundedDay = foundedDay;
	}

	@Override
	public String toString() {
		return System.lineSeparator() + "id=" + id + System.lineSeparator() + "name=" + name + System.lineSeparator()
				+ "permalink=" + permalink + System.lineSeparator() + "homepageUrl=" + homepageUrl
				+ System.lineSeparator() + "blogUrl=" + blogUrl + System.lineSeparator() + "blogFeedUrl=" + blogFeedUrl
				+ System.lineSeparator() + "twitterUsername=" + twitterUsername + System.lineSeparator()
				+ "categoryCode=" + categoryCode + System.lineSeparator() + "numberOfEmployees=" + numberOfEmployees
				+ System.lineSeparator() + "foundedYear=" + foundedYear + System.lineSeparator() + "tagList=" + tagList
				+ System.lineSeparator() + "aliasList=" + aliasList + System.lineSeparator() + "emailAddress="
				+ emailAddress + System.lineSeparator() + "phoneNumber=" + phoneNumber + System.lineSeparator()
				+ "description=" + description + System.lineSeparator() + "overview=" + overview
				+ System.lineSeparator() + "products=" + products + System.lineSeparator() + "relationships="
				+ relationships + System.lineSeparator() + "competitions=" + competitions + System.lineSeparator()
				+ "totalMoneyRaised=" + totalMoneyRaised + System.lineSeparator() + "offices=" + offices
				+ System.lineSeparator() + "foundedMonth=" + foundedMonth + System.lineSeparator() + "foundedDay="
				+ foundedDay + System.lineSeparator();
	}
}
