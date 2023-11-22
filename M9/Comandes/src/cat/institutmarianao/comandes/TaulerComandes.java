package cat.institutmarianao.comandes;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class TaulerComandes {

	private Queue<String> comandes;

	public TaulerComandes() {

		comandes = new LinkedList<>();

	}

	public boolean quedenComandes() {

		return !comandes.isEmpty();

	}

	public boolean cabenComandes() {

		return comandes.size() < ServeiSopars.MAX_CLIENTS;
	}

	public synchronized String treureComanda() {

		while (!quedenComandes()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		String comanda = comandes.poll();

		System.out.println("S'ha tret " + comanda + ". La cua conté " + Arrays.toString(comandes.toArray()));

		notify();

		return comanda;

	}

	public synchronized void afegirComanda(String comanda) {

		while (!cabenComandes()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		comandes.offer(comanda);

		System.out.println("S'ha afegit " + comanda + ". La cua conté " + Arrays.toString(comandes.toArray()));

		notify();
	}

}