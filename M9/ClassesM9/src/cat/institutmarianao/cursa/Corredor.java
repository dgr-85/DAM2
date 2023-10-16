package cat.institutmarianao.cursa;

public class Corredor implements Runnable {

	private int metresPerSegon;
	private Marcador marcador;
	private String nom;
	private int temps;

	public Corredor(String nom, Marcador marcador) {
		this.nom = nom;
		this.marcador = marcador;
		this.metresPerSegon = (int) (Math.random() * 3) + 6;
	}

	@Override
	public void run() {
		try {
			temps = Cursa.TOTAL_METRES / metresPerSegon;
			Thread.sleep(temps);
			marcador.afegirCorredor(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getMetresPerSegon() {
		return metresPerSegon;
	}

}
