package cat.institutmarianao.part1;

public class Race {

	public static final int TOTAL_LAPS = 70;
	private static final String[] PILOT_NAMES = { "Norris", "Sainz", "LeClerc", "Bottas", "Perez", "Verstappen",
			"Ricciardo", "Hammilton" };
	private static Thread[] pilots;

	public static void main(String[] args) {

		// Instantiate RaceStatus
		RaceStatus raceStatus = new RaceStatus();

		// Instantiate Thread array according to number of pilot names
		pilots = new Thread[PILOT_NAMES.length];

		// Create a Thread for each Pilot
		for (int i = 0; i < PILOT_NAMES.length; i++) {
			pilots[i] = new Thread(new Pilot(PILOT_NAMES[i], raceStatus));
		}

		// Start race
		for (Thread t : pilots) {
			t.start();
		}

		// End race
		for (Thread t : pilots) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		// Print results
		System.out.println(raceStatus.toString());
	}
}
