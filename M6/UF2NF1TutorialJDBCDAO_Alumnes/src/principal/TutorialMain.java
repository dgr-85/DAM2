package principal;

import java.sql.Date;
import java.util.ArrayList;

import dao.DAOManager;
import dao.DepartamentDAO;
import dao.EmpleatDAO;
import dao.GestorErrors;
import model.Departament;
import model.Empleat;

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
		int resAddDep = depDAO.addDepartament(newDep);
		if (resAddDep > 0) {
			System.out.println("Departament Afegit");
		} else {
			System.out.println(GestorErrors.getMissatge(resAddDep, "Departament"));
		}

		// Buscar un Departament a partir de la seva Id
		System.out.println("Buscar un Departament a partir de la seva Id");
		int idReadDep = 77;
		Departament searchDep = depDAO.getDepartamentById(idReadDep, false);
		if (searchDep != null) {
			System.out.println("Departament Trobat");
			System.out.println(searchDep.toString());
		} else {
			System.out.println(GestorErrors.getMissatge(0, "Departament"));
		}

		// Actualitzar un Departament
		System.out.println("Actualitzar les dades d'un Departament");
		Departament updateDep = new Departament(77, "NomActualitzat", "La Cochinchina");
		int resUpdateDep = depDAO.updateDepartament(updateDep);
		if (resUpdateDep > 0) {
			System.out.println(resUpdateDep + " Departament actualitzat");
			updateDep = depDAO.getDepartamentById(77, false);
			System.out.println(updateDep.toString());
		} else {
			System.out.println(GestorErrors.getMissatge(resUpdateDep, "Departament"));
		}

		// Esborrar un Departament

		System.out.println("Esborrar un Departament");
		int idDeleteDep = 77;
		int resDeleteDep = depDAO.deleteDepartament(idDeleteDep, false);
		if (resDeleteDep > 0) {
			System.out.println(resDeleteDep + " Departament esborrat.");
		} else {
			System.out.println(GestorErrors.getMissatge(0, "Departament"));
		}

		// Llista de tots els Departaments
		System.out.println("Llista de tots els Departaments");
		ArrayList<Departament> llistaDepartaments = depDAO.listDepartaments();
		for (Departament dep : llistaDepartaments) {
			System.out.println(dep.toString());
		}

		// Afegir un Empleat
		System.out.println("Afegir un Empleat");
		Empleat exemple = empDAO.getEmpleatById(7698);
		Empleat newEmp = new Empleat(9999, "NouCognom", "DIRECTOR", exemple, Date.valueOf("2000-12-31"), (float) 5.2,
				(float) 2.2, depDAO.getDepartamentById(10, false));
		int resAddEmp = empDAO.addEmpleat(newEmp);
		if (resAddEmp > 0) {
			System.out.println(resAddEmp + " Empleat afegit");
		} else {
			System.out.println(GestorErrors.getMissatge(resAddDep, "Empleat"));
		}

		// Buscar un Empleat a partir de la seva Id

		System.out.println("Buscar un Empleat a partir de la seva Id");
		int idReadEmp = 9999;
		Empleat searchEmp = empDAO.getEmpleatById(idReadEmp);
		if (searchEmp != null) {
			System.out.println("Empleat Trobat");
			System.out.println(searchEmp.toString());
		} else {
			System.out.println(GestorErrors.getMissatge(0, "Empleat"));
		}

		// Actualitzar un Empleat
		System.out.println("Actualitzar les dades d'un Departament");
		Empleat updateEmp = new Empleat(9999, "NewSurname", "ANALISTA", empDAO.getEmpleatById(7900),
				Date.valueOf("1999-12-31"), (float) 3.7, (float) 79.8, depDAO.getDepartamentById(20, false));
		int resUpdateEmp = empDAO.updateEmpleat(updateEmp);
		if (resUpdateEmp > 0) {
			System.out.println(resUpdateEmp + " Empleat actualitzat");
			updateEmp = empDAO.getEmpleatById(9999);
			System.out.println(updateEmp.toString());
		} else {
			System.out.println(GestorErrors.getMissatge(resUpdateDep, "Empleat"));
		}

		// Esborrar un Empleat
		System.out.println("Esborrar un Empleat");
		int idDeleteEmp = 9999;
		int resDeleteEmp = empDAO.deleteEmpleat(idDeleteEmp);
		if (resDeleteEmp > 0) {
			System.out.println(resDeleteEmp + " Empleat esborrat.");
		} else {
			System.out.println(GestorErrors.getMissatge(0, "Empleat"));
		}

		// Llista de tots els Empleats
		System.out.println("Llista de tots els Empleats");
		ArrayList<Empleat> llistaEmpleats = empDAO.listOfEmpleats();
		for (Empleat emp : llistaEmpleats) {
			System.out.println(emp.toString());
		}

	}

}
