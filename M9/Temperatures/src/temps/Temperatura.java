package temps;

public class Temperatura {

	private static final int SALT = 365;
	private static final int NUM_FILS = 10;
	private static final int TOTAL_DIES = 3650;
	private static int[] temperatures;

	public static void main(String[] args) {

		// Crear marcador de temperatures
		MarcadorTemperatures marcadorTemperatures = new MarcadorTemperatures(NUM_FILS);

		// Crear array de temperatures
		determinarEstacio();
		for (int i = 0; i < temperatures.length; i++) {
			System.out.println(temperatures[i]);
		}

		// Crear fils
		Thread[] sectors = new Thread[NUM_FILS];

		// Assignar a cada fil un tros de l'array
		for (int i = 0; i < sectors.length; i++) {
			sectors[i] = new Thread(new Any(SALT * i, SALT * i + SALT - 1, temperatures, marcadorTemperatures));
			sectors[i].setName("Sector " + i);
		}

		// Activar fils
		for (Thread t : sectors) {
			t.start();
		}

		// Ajuntar fils
		for (Thread t : sectors) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int j = 1;
		for (int i : marcadorTemperatures.obtenirTemperaturesMaximes()) {
			System.out.println("Temperatura " + (j++) + ": " + i);
		}
	}

	private static void determinarEstacio() {
		temperatures = new int[TOTAL_DIES];

		for (int any = 0; any < NUM_FILS; any++) {

			for (int dia = 0; dia < TOTAL_DIES; dia++) {

				if ((any * dia >= any * 1 && any * dia <= any * 81)
						|| (any * dia >= any * 356 && any * dia <= any * SALT)) { // Hivern
					temperatures[dia] = (int) (Math.random() * (15 - 5 + 1)) - 5;
				} else if ((any * dia >= any * 82 && any * dia <= any * 173)
						|| (any * dia >= any * 265 && any * dia <= any * 355)) { // Primavera i Tardor
					temperatures[dia] = (int) (Math.random() * (25 - 10 + 1)) + 10;
				} else if (any * dia >= any * 174 && any * dia <= any * 264) { // Estiu
					temperatures[dia] = (int) (Math.random() * (40 - 20 + 1)) + 20;
				}
			}
		}
	}
}
