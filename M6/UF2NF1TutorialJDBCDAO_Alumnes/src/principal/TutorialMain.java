package principal;

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
		int resultat = depDAO.addDepartament(newDep);
		if (resultat > 0) {
			System.out.println("Departament Afegit");
		} else {
			System.out.println(GestorErrors.getMissatge(resultat, "Departament"));
		}
		;

		// Buscar un Departament a partir de la seva Id
		System.out.println("Buscar un Departament a partir de la seva Id");
		int id = 10;
		Departament searchDep = depDAO.getDepartamentById(id, false);
		if (searchDep != null) {
			System.out.println("Departament Trobat");
			System.out.println(searchDep.toString());
		} else {
			System.out.println(GestorErrors.getMissatge(resultat, "Departament"));
		}
		;

	}

}
