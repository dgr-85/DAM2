package main;

import dao.UsuarisDAO;
import managers.DAOManager;
import managers.ErrorManager;
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
		} else {
			System.out.println(ErrorManager.getMessage(resIdAddUsuari, "Aquest usuari"));
		}

		// Afegir usuari ja existent (causa error)
		System.out.println("===============================================");
		System.out.println("Afegint usuari...");
		nouUsuari = new Usuaris(2533, "pwd1999", "Josefina", "Puigplujós Puigvermell", "jopupu@gmail.com");
		resIdAddUsuari = uDAO.addUsuari(nouUsuari);
		if (resIdAddUsuari > 0) {
			System.out.println("Usuari amb Id " + resIdAddUsuari + " afegit.");
		} else {
			System.out.println(ErrorManager.getMessage(resIdAddUsuari, "Aquest usuari"));
		}

		// Buscar usuari per Id
		System.out.println("===============================================");
		Integer idUsuariBuscat = 4567;
		System.out.println("Buscant usuari amb Id " + idUsuariBuscat + "...");
		Usuaris usuariBuscat = uDAO.getUsuarisById(idUsuariBuscat);
		if (usuariBuscat != null) {
			System.out.println("Usuari trobat: " + usuariBuscat.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idUsuariBuscat)));
		}

		// Buscar usuari per Id inexistent
		System.out.println("===============================================");
		idUsuariBuscat = 7999;
		System.out.println("Buscant usuari amb Id " + idUsuariBuscat + "...");
		usuariBuscat = uDAO.getUsuarisById(idUsuariBuscat);
		if (usuariBuscat != null) {
			System.out.println("Usuari trobat: " + usuariBuscat.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idUsuariBuscat)));
		}
	}
}
