package models;

public class Competitor {
	private String name;
	private String permalink;

	public Competitor() {
		super();
	}

	public Competitor(String name, String permalink) {
		super();
		this.name = name;
		this.permalink = permalink;
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

	@Override
	public String toString() {
		return "Competitor [name=" + name + ", permalink=" + permalink + "]";
	}
}
