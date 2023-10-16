package cat.institutmarianao.cursa;

public class Cursa {

	public static final int TOTAL_METRES = 1500;
	private static Thread[] corredors;
	private static final String[] NOMS_CORREDORS = { "c1", "c2", "c3" };

	public static void main(String[] args) {

		// Crear marcador (únic)
		Marcador marcador = new Marcador(NOMS_CORREDORS.length);

		// Crear corredors
		corredors = new Thread[NOMS_CORREDORS.length];

		//A cada corredor se li assigna un fil, on a més se li passa el marcador comú a tots
		for (int i = 0; i < NOMS_CORREDORS.length; i++) {
			corredors[i] = new Thread(new Corredor(NOMS_CORREDORS[i], marcador));
			corredors[i].setName(NOMS_CORREDORS[i]);
		}

		// TODO començar cursa (els corredors comencen a córrer) == start();
		corredors[0].start();
		corredors[1].start();
		corredors[2].start();
		
		
		// TODO esperar que tots els corredors acabin la carrera == join();
		
		try {
			corredors[0].join();
			corredors[1].join();
			corredors[2].join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO pintar marcador
	}
}
