package main;

import dao.UsuarisDAO;
import managers.DAOManager;
import pojos.Usuaris;

public class MainOperations {
	public static void main(String[] args) {

		UsuarisDAO uDAO = DAOManager.getUsuarisDAO();

		// Afegir usuari
		System.out.println("===============================================");
		System.out.println("Afegint usuari...");
		Usuaris nouUsuari = new Usuaris(2533, "pwd2533", "Pepita", "Puigventós Puigvert", "pepupu@gmail.com");
		Integer resIdAddUsuari = uDAO.addUsuari(nouUsuari);
		if (resIdAddUsuari > 0) {
			System.out.println("Usuari amb Id " + resIdAddUsuari + " afegit.");
		}
	}
}
