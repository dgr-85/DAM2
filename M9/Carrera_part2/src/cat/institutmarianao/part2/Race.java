package cat.institutmarianao.part2;

public class Race {

	public static final int TOTAL_LAPS = 70;
	private static final String[] PILOT_NAMES = { "Magnussen", "Hulkenberg", "Guanyu", "Bottas", "Piastri", "Norris",
			"Ocon", "Gasly" };
	private static final String[] TEAM_NAMES = { "Haas F1 Team", "Alfa Romeo", "McLaren", "Alpine" };
	private static Team[] teams;
	private static Pilot[] pilots;
	private static Thread[] threadPilots;

	public static void main(String[] args) {

		RaceStatus raceStatus = new RaceStatus();
		pilots = new Pilot[PILOT_NAMES.length];
		threadPilots = new Thread[PILOT_NAMES.length];
		teams = new Team[TEAM_NAMES.length];

		// Create Pilots
		for (int i = 0; i < PILOT_NAMES.length; i++) {
			pilots[i] = new Pilot(PILOT_NAMES[i], raceStatus);
		}

		// Create a Thread for each Pilot
		for (int i = 0; i < pilots.length; i++) {
			threadPilots[i] = new Thread(pilots[i]);
			threadPilots[i].setName(PILOT_NAMES[i]);
		}

		// Create Teams
		for (int i = 0, j = 0; i < TEAM_NAMES.length; i++, j += 2) {
			teams[i] = new Team(TEAM_NAMES[i], pilots[j], pilots[j + 1], raceStatus);
		}

		// Start race
		for (Thread t : threadPilots) {
			t.start();
		}

		// End race
		for (Thread t : threadPilots) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Stop Boxes
		for (Team team : teams) {
			synchronized (team.getBox()) {
				team.getBox().notify();
			}
		}

		// Print results
		System.out.println(raceStatus.toString());
	}
}
