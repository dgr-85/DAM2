package temps;

public class Any implements Runnable {
	private int min, max;
	private int[] temperatures;
	private MarcadorTemperatures marcadorTemperatures;

	public Any(int min, int max, int[] temperatures, MarcadorTemperatures marcadorTemperatures) {
		this.min = min;
		this.max = max;
		this.temperatures = temperatures;
		this.marcadorTemperatures = marcadorTemperatures;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	@Override
	public void run() {
		int maxTemp = 0;
		for (int i = this.getMin(); i < this.getMax(); i++) {
			if (maxTemp < temperatures[i]) {
				maxTemp = temperatures[i];
			}
		}
		marcadorTemperatures.afegirTemperaturaMaxima(maxTemp);
	}

}
