package models;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Relationship {
	@BsonProperty(value = "is_past")
	private Boolean isPast;
	private String title;
	private Person person;

	public Relationship() {
		super();
	}

	public Relationship(Boolean isPast, String title, Person person) {
		super();
		this.isPast = isPast;
		this.title = title;
		this.person = person;
	}

	public Boolean getIsPast() {
		return isPast;
	}

	public void setIsPast(Boolean isPast) {
		this.isPast = isPast;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
