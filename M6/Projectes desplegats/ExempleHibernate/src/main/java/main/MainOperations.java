package main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import dao.DepartamentsDAO;
import dao.EmpleatsDAO;
import dao.UsuarisDAO;
import managers.DAOManager;
import managers.ErrorManager;
import pojos.Departaments;
import pojos.Empleats;
import pojos.Usuaris;

public class MainOperations {
	public static void main(String[] args) {

		UsuarisDAO uDAO = DAOManager.getUsuarisDAO();
		EmpleatsDAO eDAO = DAOManager.getEmpleatsDAO();
		DepartamentsDAO dDAO = DAOManager.getDepartamentsDAO();

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

		// ===========================================================================
		// ===========================================================================
		// ===========================================================================

		// Afegir empleat
		System.out.println("===============================================");
		System.out.println("Afegint empleat...");
		Departaments exempleDep = dDAO.getDepartamentById(10);
		Empleats nouEmpleat = new Empleats(8877, exempleDep, "MORENETA", "MONTSE", Date.valueOf("1993-04-30"),
				(long) 4300, (long) 0);
		Integer resIdAddEmpleat = eDAO.addEmpleat(nouEmpleat);
		if (resIdAddEmpleat > 0) {
			System.out.println("Empleat amb Id " + resIdAddEmpleat + " afegit.");
			System.out.println(nouEmpleat.toString());
			System.out.println(nouEmpleat.getDepartaments().toString());
		} else {
			System.out.println(ErrorManager.getMessage(resIdAddEmpleat, "Aquest empleat"));
		}

		// Afegir empleat ja existent (causa error)
		System.out.println("===============================================");
		System.out.println("Afegint empleat...");
		nouEmpleat = new Empleats(8877, exempleDep, "ROSSETA", "ROSA", Date.valueOf("1996-07-10"), (long) 7300,
				(long) 40);
		;
		resIdAddEmpleat = eDAO.addEmpleat(nouEmpleat);
		if (resIdAddEmpleat > 0) {
			System.out.println("Empleat amb Id " + resIdAddEmpleat + " afegit.");
		} else {
			System.out.println(ErrorManager.getMessage(resIdAddEmpleat, "Aquest empleat"));
		}

		// Buscar empleat per Id
		System.out.println("===============================================");
		Integer idEmpleatBuscat = 8877;
		System.out.println("Buscant empleat amb Id " + idEmpleatBuscat + "...");
		Empleats resIdEmpleatBuscat = eDAO.getEmpleatById(idEmpleatBuscat);
		if (resIdEmpleatBuscat != null) {
			System.out.println("Empleat trobat: " + resIdEmpleatBuscat.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idEmpleatBuscat)));
		}

		// Buscar empleat per Id inexistent (retorna null)
		System.out.println("===============================================");
		idEmpleatBuscat = 9292;
		System.out.println("Buscant empleat amb Id " + idEmpleatBuscat + "...");
		resIdEmpleatBuscat = eDAO.getEmpleatById(idEmpleatBuscat);
		if (resIdEmpleatBuscat != null) {
			System.out.println("Empleat trobat: " + resIdEmpleatBuscat.toString());
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idEmpleatBuscat)));
		}

		// Actualizar empleat
		System.out.println("===============================================");
		Integer idEmpleatActualizat = 8877;
		System.out.println("Actualitzant empleat amb Id " + idEmpleatActualizat + "...");
		Empleats empleatActualitzat = eDAO.getEmpleatById(idEmpleatActualizat);
		if (empleatActualitzat != null) {
			empleatActualitzat.setCognoms("CASTANYETA");
			empleatActualitzat.setNom("ANNA MARIA");
			empleatActualitzat.setDataAlta(Date.valueOf("1992-11-18"));
			empleatActualitzat.setSou((long) 9001);
			empleatActualitzat.setComissio(null);
			empleatActualitzat.setDepartaments(dDAO.getDepartamentById(20));
			idEmpleatActualizat = eDAO.updateEmpleat(empleatActualitzat);
			if (idEmpleatActualizat == empleatActualitzat.getEmpNo()) {
				System.out.println("Empleat amb Id " + empleatActualitzat.getEmpNo() + " actualitzat amb èxit.");
				System.out.println(empleatActualitzat.toString());
				System.out.println(empleatActualitzat.getDepartaments().toString());
			} else {
				System.out.println(
						ErrorManager.getMessage(idEmpleatActualizat, String.valueOf(empleatActualitzat.getEmpNo())));
			}
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idEmpleatActualizat)));
		}

		// Actualizar empleat inexistent (causa error)
		System.out.println("===============================================");
		idEmpleatActualizat = 9227;
		System.out.println("Actualitzant empleat amb Id " + idEmpleatActualizat + "...");
		empleatActualitzat = eDAO.getEmpleatById(idEmpleatActualizat);
		if (empleatActualitzat != null) {
			empleatActualitzat.setCognoms("CASTANYETA");
			empleatActualitzat.setNom("ANNA MARIA");
			empleatActualitzat.setDataAlta(Date.valueOf("1992-11-18"));
			empleatActualitzat.setSou((long) 9001);
			empleatActualitzat.setComissio(null);
			empleatActualitzat.setDepartaments(dDAO.getDepartamentById(20));
			idEmpleatActualizat = eDAO.updateEmpleat(empleatActualitzat);
			if (idEmpleatActualizat == empleatActualitzat.getEmpNo()) {
				System.out.println("Empleat amb Id " + empleatActualitzat.getEmpNo() + " actualitzat amb èxit.");
			} else {
				System.out.println(
						ErrorManager.getMessage(idEmpleatActualizat, String.valueOf(empleatActualitzat.getEmpNo())));
			}
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idEmpleatActualizat)));
		}

		// Esborrar empleat per Id
		System.out.println("===============================================");
		Integer idEmpleatEsborrat = 8877;
		System.out.println("Esborrant empleat amb Id " + idEmpleatEsborrat + "...");
		Integer resIdEmpleatEsborrat = eDAO.deleteEmpleat(idEmpleatEsborrat);
		if (resIdEmpleatEsborrat.equals(idEmpleatEsborrat)) {
			System.out.println("Empleat amb Id " + resIdEmpleatEsborrat + " esborrat amb èxit.");
		} else {
			System.out.println(ErrorManager.getMessage(resIdEmpleatEsborrat, String.valueOf(idEmpleatEsborrat)));
		}

		// Esborrar empleat inexistent per Id (causa error)
		System.out.println("===============================================");
		idEmpleatEsborrat = 9020;
		System.out.println("Esborrant empleat amb Id " + idEmpleatEsborrat + "...");
		resIdEmpleatEsborrat = eDAO.deleteEmpleat(idEmpleatEsborrat);
		if (resIdEmpleatEsborrat.equals(idEmpleatEsborrat)) {
			System.out.println("Empleat amb Id " + resIdEmpleatEsborrat + " esborrat amb èxit.");
		} else {
			System.out.println(ErrorManager.getMessage(resIdEmpleatEsborrat, String.valueOf(idEmpleatEsborrat)));
		}

		// Llistar tots els empleats
		System.out.println("===============================================");
		System.out.println("Llistant tots els empleats...");
		ArrayList<Empleats> listEmpleats = eDAO.listAllEmpleats();
		if (listEmpleats != null) {
			System.out.println("Se n'han trobat " + listEmpleats.size() + ":");
			for (Empleats e : listEmpleats) {
				System.out.println(e.toString());
			}
		}

		// ===========================================================================
		// ===========================================================================
		// ===========================================================================

		// Afegir departament
		System.out.println("===============================================");
		System.out.println("Afegint departament...");
		Departaments nouDepartament = new Departaments(50, "RECURSOS HUMANS", "MORDOR", null);
		Set<Empleats> emps = new HashSet<>();
		emps.add(new Empleats(6578, nouDepartament, "FERNANDEZ", "VISITACION", Date.valueOf("1990-12-05"), (long) 2000,
				(long) 10));
		nouDepartament.setEmpleatses(emps);
		Integer resIdAddDepartament = dDAO.addDepartament(nouDepartament);
		if (resIdAddDepartament > 0) {
			System.out.println("Departament amb Id " + resIdAddDepartament + " afegit.");
			if (emps.size() > 0) {
				System.out.println("Incloent " + emps.size() + " empleats.");
			}
		} else {
			System.out.println(ErrorManager.getMessage(resIdAddDepartament, "Aquest departament"));
		}

		// Afegir departament ja existent (causa error)
		System.out.println("===============================================");
		System.out.println("Afegint departament...");
		nouDepartament = new Departaments(50, "DE RELLENO", "SANT MORDOR", null);
		resIdAddDepartament = dDAO.addDepartament(nouDepartament);
		if (resIdAddDepartament > 0) {
			System.out.println("Departament amb Id " + resIdAddDepartament + " afegit.");
		} else {
			System.out.println(ErrorManager.getMessage(resIdAddDepartament, "Aquest departament"));
		}

		// Buscar departament per Id
		System.out.println("===============================================");
		Integer idDepartamentBuscat = 50;
		System.out.println("Buscant departament amb Id " + idDepartamentBuscat + "...");
		Departaments resIdDepartamentBuscat = dDAO.getDepartamentById(idDepartamentBuscat);
		if (resIdDepartamentBuscat != null) {
			System.out.println("Departament trobat: " + resIdDepartamentBuscat.toString());
			if (resIdDepartamentBuscat.getEmpleatses().size() > 0) {
				System.out.println(
						"Aquest departament conté " + resIdDepartamentBuscat.getEmpleatses().size() + " empleats:");
				System.out.println(resIdDepartamentBuscat.getEmpleatses().toString());
			}
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idDepartamentBuscat)));
		}

		// Buscar departament per Id inexistent (retorna null)
		System.out.println("===============================================");
		idDepartamentBuscat = 99;
		System.out.println("Buscant departament amb Id " + idDepartamentBuscat + "...");
		resIdDepartamentBuscat = dDAO.getDepartamentById(idDepartamentBuscat);
		if (resIdDepartamentBuscat != null) {
			System.out.println("Departament trobat: " + resIdDepartamentBuscat.toString());
			if (resIdDepartamentBuscat.getEmpleatses().size() > 0) {
				System.out.println(
						"Aquest departament conté " + resIdDepartamentBuscat.getEmpleatses().size() + " empleats:");
				System.out.println(resIdDepartamentBuscat.getEmpleatses().toString());
			}
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idDepartamentBuscat)));
		}

		// Actualizar departament
		System.out.println("===============================================");
		Integer idDepartamentActualizat = 50;
		System.out.println("Actualitzant departament amb Id " + idDepartamentActualizat + "...");
		Departaments departamentActualitzat = dDAO.getDepartamentById(idDepartamentActualizat);
		if (departamentActualitzat != null) {
			departamentActualitzat.setDeptNom("CÀTERING");
			departamentActualitzat.setDeptCiutat("CÁCERES");
			idDepartamentActualizat = dDAO.updateDepartament(departamentActualitzat);
			if (idDepartamentActualizat == departamentActualitzat.getDeptNo()) {
				System.out
						.println("Departament amb Id " + departamentActualitzat.getDeptNo() + " actualitzat amb èxit.");
			} else {
				System.out.println(ErrorManager.getMessage(idDepartamentActualizat,
						String.valueOf(departamentActualitzat.getDeptNo())));
			}
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idDepartamentActualizat)));
		}

		// Actualizar departament inexistent (causa error)
		System.out.println("===============================================");
		idDepartamentActualizat = 99;
		System.out.println("Actualitzant departament amb Id " + idDepartamentActualizat + "...");
		departamentActualitzat = dDAO.getDepartamentById(idDepartamentActualizat);
		if (departamentActualitzat != null) {
			departamentActualitzat.setDeptNom("CÀTERING");
			departamentActualitzat.setDeptCiutat("CÁCERES");
			idDepartamentActualizat = dDAO.updateDepartament(departamentActualitzat);
			if (idDepartamentActualizat == departamentActualitzat.getDeptNo()) {
				System.out
						.println("Departament amb Id " + departamentActualitzat.getDeptNo() + " actualitzat amb èxit.");
			} else {
				System.out.println(ErrorManager.getMessage(idDepartamentActualizat,
						String.valueOf(departamentActualitzat.getDeptNo())));
			}
		} else {
			System.out.println(ErrorManager.getMessage(-1, String.valueOf(idDepartamentActualizat)));
		}

		// Esborrar departament per Id
		System.out.println("===============================================");
		Integer idDepartamentEsborrat = 50;
		System.out.println("Esborrant departament amb Id " + idDepartamentEsborrat + "...");
		Integer resIdDepartamentEsborrat = dDAO.deleteDepartament(idDepartamentEsborrat);
		if (resIdDepartamentEsborrat.equals(idDepartamentEsborrat)) {
			System.out.println("Departament amb Id " + resIdDepartamentEsborrat + " esborrat amb èxit.");
		} else {
			System.out
					.println(ErrorManager.getMessage(resIdDepartamentEsborrat, String.valueOf(idDepartamentEsborrat)));
		}

		// Esborrar departament inexistent per Id (causa error)
		System.out.println("===============================================");
		idDepartamentEsborrat = 99;
		System.out.println("Esborrant departament amb Id " + idDepartamentEsborrat + "...");
		resIdDepartamentEsborrat = dDAO.deleteDepartament(idDepartamentEsborrat);
		if (resIdDepartamentEsborrat.equals(idDepartamentEsborrat)) {
			System.out.println("Departament amb Id " + resIdDepartamentEsborrat + " esborrat amb èxit.");
		} else {
			System.out
					.println(ErrorManager.getMessage(resIdDepartamentEsborrat, String.valueOf(idDepartamentEsborrat)));
		}

		// Llistar tots els departaments
		System.out.println("===============================================");
		System.out.println("Llistant tots els departaments...");
		ArrayList<Departaments> listDepartaments = dDAO.listAllDepartaments();
		if (listDepartaments != null) {
			System.out.println("Se n'han trobat " + listDepartaments.size() + ":");
			for (Departaments d : listDepartaments) {
				System.out.println(d.toString());
				if (d.getEmpleatses().size() > 0) {
					System.out.println("Aquest departament conté " + d.getEmpleatses().size() + " empleats:");
					System.out.println(d.getEmpleatses().toString());
				}
			}
		}
	}
}
