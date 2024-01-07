package cat.institutmarianao.part2;

public class Team {
	private String name;
	private Box box;
	private Pilot[] pilots;

	public Team(String name, Pilot pilot1, Pilot pilot2, RaceStatus raceStatus) {
		this.name = name;
		this.box = new Box(raceStatus);
		pilots = new Pilot[] { pilot1, pilot2 };

		for (Pilot p : pilots) {
			p.setTeam(this);
		}

		box.setTeamName(name);

		Thread teamBox = new Thread(box);
		teamBox.setName("BOX(" + getName() + ")");
		System.out.println(teamBox.getName() + " started!");
		teamBox.start();
	}

	public Box getBox() {
		return box;
	}

	public String getName() {
		return name;
	}

}
