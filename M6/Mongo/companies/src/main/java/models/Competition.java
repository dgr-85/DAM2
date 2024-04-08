package models;

import java.util.List;

public class Competition {
	private List<Competitor> competitors;

	public Competition() {
		super();
	}

	public Competition(List<Competitor> competitors) {
		super();
		this.competitors = competitors;
	}

	public List<Competitor> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(List<Competitor> competitors) {
		this.competitors = competitors;
	}

}
