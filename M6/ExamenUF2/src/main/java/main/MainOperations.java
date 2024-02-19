package main;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.AutorsDAO;
import dao.IdiomesDAO;
import dao.LlibresDAO;
import managers.DAOManager;
import managers.ErrorManager;
import pojos.Autors;
import pojos.Llibres;

public class MainOperations {

	public static void main(String[] args) {
		IdiomesDAO iDAO = DAOManager.getIdiomesDAO();
		AutorsDAO aDAO = DAOManager.getAutorsDAO();
		LlibresDAO lDAO = DAOManager.getLlibresDAO();

		// Afegir autor sense llibres
		System.out.println("===============================================");
		System.out.println("Adding new Autor...");
		Autors addAutor = new Autors(4, "Autor 4", "Tazmania", "Panfleto", null);
		Integer resIdAddAutor = aDAO.addAutor(addAutor);
		if (resIdAddAutor > 0) {
			System.out.println("Autor with Id " + resIdAddAutor + " added.");
		} else {
			System.out.println(ErrorManager.getMessage(resIdAddAutor, "This Autor"));
		}
		// Afegir autor amb 1 llibre
		System.out.println("===============================================");
		System.out.println("Adding new Autor...");
		addAutor = new Autors(5, "Autor 5", "Tailandia", "Triptico", null);
		Set<Llibres> auLl = new HashSet<>();
		// int codiLlibre, Autors autors, Idiomes idiomes, String titol, Integer pagines
		auLl.add(new Llibres(5, addAutor, iDAO.getIdiomaById(1), "Titol 5", 50));
		addAutor.setLlibreses(auLl);
		resIdAddAutor = aDAO.addAutor(addAutor);
		if (resIdAddAutor > 0) {
			System.out.println("Autor with Id " + resIdAddAutor + " added.");
		} else {
			System.out.println(ErrorManager.getMessage(resIdAddAutor, "This Autor"));
		}

		// Llistar tots els autors amb llibres i idiomes
		System.out.println("==============================================");
		System.out.println("Listing all Autors...");
		List<Autors> listAutors = aDAO.listAllAutors(true);
		if (listAutors.size() > 0) {
			for (Autors a : listAutors) {
				System.out.println(a.toString());
			}
		} else {
			System.out.println("No Autors found.");
		}

	}
}