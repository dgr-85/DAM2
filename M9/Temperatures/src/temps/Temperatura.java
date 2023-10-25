package temps;

public class Temperatura {

	private static final int SALT = 365;
	private static final int NUM_FILS = 10;
	private MarcadorTemperatures marcadorTemperatures;

	public static void main(String[] args) {

		// Crear marcador de temperatures
		MarcadorTemperatures marcadorTemperatures = new MarcadorTemperatures(NUM_FILS);

		// Crear array de temperatures
		int[] temperatures = new int[3650];
		for (int i = 0; i < temperatures.length; i++) {
			temperatures[i] = (int) (Math.random() * 20) + (int) (Math.random() * 40);
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
			System.out.println("Temperatura " + j + ": " + i);
			j++;
		}
	}
}
