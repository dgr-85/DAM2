package junit.cua;

public class Cua {
	final int MIDA_MAX;
	int primer;
	int darrer;
	int mida;
	int elements[];

	public Cua(int midaMax) {
		assert midaMax > 0;
		MIDA_MAX = midaMax;
		primer = 0;
		darrer = 0;
		mida = 0;
		elements = new int[MIDA_MAX];
	}

	public boolean esBuida() {
		checkRep();
		return mida == 0;
	}

	public boolean esPlena() {
		checkRep();
		return mida == MIDA_MAX;
	}

	public void encuar(int element) throws Exception {
		checkRep();
		if (esPlena()) {
			throw new Exception("La cua és plena");
		}
		elements[darrer] = element;
		mida++;
		darrer++;
		if (darrer == MIDA_MAX) {
			darrer = 0;
		}
	}

	public int desencuar() throws Exception {
		checkRep();
		if (esBuida()) {
			throw new Exception("La cua és buida");
		}
		int element = elements[primer];
		mida--;
		primer++;
		if (primer == MIDA_MAX) {
			primer = 0;
		}
		return element;
	}

	private void checkRep() {
		assert 0 <= mida && mida <= MIDA_MAX;
		if (primer < darrer) {
			assert mida == darrer - primer;
		} else if (primer > darrer) {
			assert mida == MIDA_MAX - (primer - darrer);
		} else {
			assert mida == 0 || mida == MIDA_MAX;
		}
	}
}
