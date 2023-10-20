package principal;

import java.util.ArrayList;
import java.util.Iterator;

import model.Departament;

import dao.DAOManager;
import dao.DepartamentDAO;
import dao.GestorErrors;

/*En aquest cas el main representa la vista de l'aplicació. No pot mostrar res que no hi
 * tingui a veure. Per això, la connexió a la base de dades té la seva pròpia classe
 * (GestorConnexions.java).*/

public class TutorialMain {

	public static void main(String[] args) {
		
		DepartamentDAO depDAO = DAOManager.getDepDAO();

		
		// Afegir un Departament
		System.out.println("Afegir un Departament");
		Departament newDep = new Departament(77,"NouDep1","BARCELONA");
		int resultat = depDAO.addDepartament(newDep);
		if (resultat>0){
			System.out.println("Departament Afegit");	
		}else {
			System.out.println(GestorErrors.getMissatge(resultat, "Departament"));
		};
		
		
	}

}
