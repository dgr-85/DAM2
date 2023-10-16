package cat.institutmarianao.cursa;

public class Marcador {
	private int ultim;
	private Corredor[] corredors;

	public Marcador(int num) {
		corredors = new Corredor[num];
		this.ultim = 0;
	}

	// Setter
	public void afegirCorredor(Corredor corredor) {
		corredors[ultim++] = corredor;
	}

	// Getter (no caldria en aquest cas, però convé de cara a futur)
	public synchronized Corredor[] getCorredors() {
		return corredors;
	}

}
