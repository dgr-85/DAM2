package main;

import java.util.ArrayList;

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
		Integer idUsuariBuscat = 2533;
		System.out.println("Buscant usuari amb Id " + idUsuariBuscat + "...");
		Usuaris resIdUsuariBuscat = uDAO.getUsuarisById(idUsuariBuscat);
		if (resIdUsuariBuscat != null) {
			System.out.println("Usuari trobat: " + resIdUsuariBuscat.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idUsuariBuscat)));
		}

		// Buscar usuari per Id inexistent (retorna null)
		System.out.println("===============================================");
		idUsuariBuscat = 7999;
		System.out.println("Buscant usuari amb Id " + idUsuariBuscat + "...");
		resIdUsuariBuscat = uDAO.getUsuarisById(idUsuariBuscat);
		if (resIdUsuariBuscat != null) {
			System.out.println("Usuari trobat: " + resIdUsuariBuscat.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idUsuariBuscat)));
		}

		// Actualizar usuari
		System.out.println("===============================================");
		Integer idUsuariActualizat = 2533;
		System.out.println("Actualitzant usuari amb Id " + idUsuariActualizat + "...");
		Usuaris usuariActualitzat = uDAO.getUsuarisById(idUsuariActualizat);
		if (usuariActualitzat != null) {
			usuariActualitzat.setNom("Pepeta");
			usuariActualitzat.setCognoms("Puigsolet Puiggrogós");
			usuariActualitzat.setPwd("pwd6444");
			usuariActualitzat.setEmail("pepupu2@gmail.com");
			idUsuariActualizat = uDAO.updateUsuaris(usuariActualitzat);
			if (idUsuariActualizat == usuariActualitzat.getUserid()) {
				System.out.println("Usuari amb Id " + usuariActualitzat.getUserid() + " actualitzat amb èxit.");
			} else {
				System.out.println(
						ErrorManager.getMessage(idUsuariActualizat, String.valueOf(usuariActualitzat.getUserid())));
			}
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idUsuariActualizat)));
		}

		// Actualizar usuari inexistent (causa error)
		System.out.println("===============================================");
		idUsuariActualizat = 9227;
		System.out.println("Actualitzant usuari amb Id " + idUsuariActualizat + "...");
		usuariActualitzat = uDAO.getUsuarisById(idUsuariActualizat);
		if (usuariActualitzat != null) {
			usuariActualitzat.setNom("Pepeta");
			usuariActualitzat.setCognoms("Puigsolet Puiggrogós");
			usuariActualitzat.setPwd("pwd6444");
			usuariActualitzat.setEmail("pepupu2@gmail.com");
			idUsuariActualizat = uDAO.updateUsuaris(usuariActualitzat);
			if (idUsuariActualizat == usuariActualitzat.getUserid()) {
				System.out.println("Usuari amb Id " + usuariActualitzat.getUserid() + " actualitzat amb èxit.");
			} else {
				System.out.println(
						ErrorManager.getMessage(idUsuariActualizat, String.valueOf(usuariActualitzat.getUserid())));
			}
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idUsuariActualizat)));
		}

		// Esborrar usuari per Id
		System.out.println("===============================================");
		Integer idUsuariEsborrat = 2533;
		System.out.println("Esborrant usuari amb Id " + idUsuariEsborrat + "...");
		Integer resIdUsuariEsborrat = uDAO.deleteUsuaris(idUsuariEsborrat);
		if (resIdUsuariEsborrat.equals(idUsuariEsborrat)) {
			System.out.println("Usuari amb Id " + resIdUsuariEsborrat + " esborrat amb èxit.");
		} else {
			System.out.println(ErrorManager.getMessage(resIdUsuariEsborrat, String.valueOf(idUsuariEsborrat)));
		}

		// Esborrar usuari inexistent per Id (causa error)
		System.out.println("===============================================");
		idUsuariEsborrat = 4533;
		System.out.println("Esborrant usuari amb Id " + idUsuariEsborrat + "...");
		resIdUsuariEsborrat = uDAO.deleteUsuaris(idUsuariEsborrat);
		if (resIdUsuariEsborrat.equals(idUsuariEsborrat)) {
			System.out.println("Usuari amb Id " + resIdUsuariEsborrat + " esborrat amb èxit.");
		} else {
			System.out.println(ErrorManager.getMessage(resIdUsuariEsborrat, String.valueOf(idUsuariEsborrat)));
		}

		// Llistar tots els usuaris
		System.out.println("===============================================");
		System.out.println("Llistant tots els usuaris...");
		ArrayList<Usuaris> listUsuaris = uDAO.listAllUsuaris();
		if (listUsuaris != null) {
			System.out.println("Se n'han trobat " + listUsuaris.size() + ":");
			for (Usuaris u : listUsuaris) {
				System.out.println(u.toString());
			}
		}
	}
}
