package cat.institutmarianao.comandes;

public class Cambrer implements Runnable {

	private TaulerComandes taulerComandes;

	public Cambrer(TaulerComandes taulerComandes) {
		this.taulerComandes = taulerComandes;
	}

	@Override
	public void run() {
		for (int i = 0; i < ServeiSopars.CLIENTS_TOTALS; i++) {
			taulerComandes.afegirComanda(ServeiSopars.plats[(int) (Math.random() * ServeiSopars.plats.length)]);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
