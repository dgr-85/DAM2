package models;

public class Product {
	private String name;
	private String permalink;

	public Product() {
		super();
	}

	public Product(String name, String permalink) {
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
		return "Product [name=" + name + ", permalink=" + permalink + "]";
	}
}
