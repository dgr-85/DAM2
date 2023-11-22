package cat.institutmarianao.comandes;

public class Cuiner implements Runnable {

	private TaulerComandes taulerComandes;

	public Cuiner(TaulerComandes taulerComandes) {
		this.taulerComandes = taulerComandes;
	}

	@Override
	public void run() {
		for (int i = 0; i < ServeiSopars.CLIENTS_TOTALS; i++) {
			taulerComandes.treureComanda();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
