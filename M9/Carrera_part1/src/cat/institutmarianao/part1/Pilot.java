package cat.institutmarianao.part1;

public class Pilot {
	private String name;
	private int laps;

	public Pilot(String name) {
		this.name = name;
		this.laps = Race.TOTAL_LAPS;
	}

	public int getLaps() {
		return laps;
	}

	public void setLaps(int laps) {
		this.laps = laps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
