package temps;

public class MarcadorTemperatures {
	private int proxima;
	private int[] decada;

	public MarcadorTemperatures(int num) {
		decada = new int[num];
		this.proxima = 0;
	}

	public void afegirTemperaturaMaxima(int max) {
		decada[proxima++] = max;
	}

	public synchronized int[] obtenirTemperaturesMaximes() {
		return decada;
	}
}