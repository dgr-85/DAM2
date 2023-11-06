package dao;

import java.util.ArrayList;

import model.Departament;
import model.Empleat;

public interface DepartamentDAO {
	/* CRUD operations */
	/* Create */
	public int addDepartament(Departament d);

	/* Read */
	public Departament getDepartamentById(Integer Id, boolean ambEmpleats);

	/* Update */
	public int updateDepartament(Departament d);

	/* Delete */
	public int deleteDepartament(Integer id, boolean cascade);

	/* Consultes a demanda */
	public ArrayList<Departament> listDepartaments();

	public ArrayList<Empleat> listEmpleatsByDepartament(Integer id);
}
