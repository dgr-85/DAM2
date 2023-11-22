package cat.institutmarianao.comandes;

public class ServeiSopars {

	public static final int MAX_CLIENTS = 3;
	public static final int CLIENTS_TOTALS = 15;
	public static String[] plats = { "Amanida", "Pizza", "Pasta", "Bistec", "Peix", "Verdures" };

	public static void main(String[] args) throws InterruptedException {

		TaulerComandes taulerComandes = new TaulerComandes();
		Cuiner cuiner = new Cuiner(taulerComandes);
		Cambrer cambrer = new Cambrer(taulerComandes);

		Thread productor = new Thread(cambrer);
		Thread consumidor = new Thread(cuiner);

		productor.start();
		consumidor.start();
	}

}
