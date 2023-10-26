package principal;

import java.util.ArrayList;

import dao.DAOManager;
import dao.DepartamentDAO;
import dao.EmpleatDAO;
import dao.GestorErrors;
import model.Departament;

/*En aquest cas el main representa la vista de l'aplicació. No pot mostrar res que no hi
 * tingui a veure. Per això, la connexió a la base de dades té la seva pròpia classe
 * (GestorConnexions.java).*/

public class TutorialMain {

	public static void main(String[] args) {

		DepartamentDAO depDAO = DAOManager.getDepDAO();
		EmpleatDAO empDAO = DAOManager.getEmpDAO();

		// Afegir un Departament
		System.out.println("Afegir un Departament");
		Departament newDep = new Departament(77, "NouDep1", "BARCELONA");
		int resAdd = depDAO.addDepartament(newDep);
		if (resAdd > 0) {
			System.out.println("Departament Afegit");
		} else {
			System.out.println(GestorErrors.getMissatge(resAdd, "Departament"));
		}

		// Buscar un Departament a partir de la seva Id
		System.out.println("Buscar un Departament a partir de la seva Id");
		int idRead = 77;
		Departament searchDep = depDAO.getDepartamentById(idRead, false);
		if (searchDep != null) {
			System.out.println("Departament Trobat");
			System.out.println(searchDep.toString());
		} else {
			System.out.println(GestorErrors.getMissatge(0, "Departament"));
		}

		// Actualitzar un departament
		System.out.println("Actualitzar les dades d'un Departament");
		Departament updateDep = new Departament(77, "NomActualitzat", "La Cochinchina");
		int resUpdate = depDAO.updateDepartament(updateDep);
		if (resUpdate > 0) {
			System.out.println("Departament actualitzat");
			updateDep = depDAO.getDepartamentById(77, false);
			System.out.println(updateDep.toString());
		} else {
			System.out.println(GestorErrors.getMissatge(resUpdate, "Departament"));
		}

		// Esborrar un Departament
		System.out.println("Esborrar un Departament");
		int idDelete = 77;
		int resDelete = depDAO.deleteDepartament(idDelete, false);
		if (resDelete > 0) {
			System.out.println("Departament esborrat.");
		} else {
			System.out.println(GestorErrors.getMissatge(0, "Departament"));
		}

		// Llista de tots els Departaments
		System.out.println("Llista de tots els Departaments");
		ArrayList<Departament> llistaDepartaments = depDAO.listDepartaments();
		for (Departament dep : llistaDepartaments) {
			System.out.println(dep.toString());
		}
	}

}
