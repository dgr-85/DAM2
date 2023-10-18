package cat.institutmarianao.viatges;

//Implementem runnable per poder crear fils sobre les seves instàncies
public class AgenciaViatges implements Runnable {

	private SeientsAvio sa = new SeientsAvio();

	@Override
	public void run() {
		gestioSeientsAvio(3);
		if (sa.getSeientsLliures() <= 0)
			System.out.println("No hi ha seients lliures");
	}

	public void gestioSeientsAvio(int numSeientsReserva) {

		// Comprovem si hi ha seients suficients
		if (sa.getSeientsLliures() >= numSeientsReserva) {
			System.out.println(
					Thread.currentThread().getName() + " farà una reserva de " + numSeientsReserva + " places");
			try {
				Thread.sleep(1000); // adormim el fil 1 segon
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			// Fem la reserva dels seients
			sa.reservaSeients(numSeientsReserva);

			System.out.println(Thread.currentThread().getName() + " Reserva realitzada amb èxit."
					+ " Les places lliures són " + sa.getSeientsLliures());
		} else {
			System.out.println("No hi ha places suficients pel client." + Thread.currentThread().getName()
					+ " Les places lliures són " + sa.getSeientsLliures());
			try {
				Thread.sleep(1000); // adormim el fil 1 segon
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		AgenciaViatges objAg = new AgenciaViatges();

		// creem els dos fils sobre la mateixa instància
		Thread fil_1 = new Thread(objAg);
		Thread fil_2 = new Thread(objAg);
		fil_1.setName("Client 1");
		fil_2.setName("Client 2");

		fil_1.start();
		fil_2.start();
	}
}

//Monitor que controla secció crítica
class SeientsAvio {
	// comencem amb 5 seients lliures a l'avió
	private int seientsLliures = 5;

	public int getSeientsLliures() {
		return seientsLliures;
	}

	public boolean hiHaSeientsLliures(int numPlaces) {
		return numPlaces <= seientsLliures;
	}

	public void reservaSeients(int numSeientsReserva) {
		seientsLliures = seientsLliures - numSeientsReserva;
	}

}