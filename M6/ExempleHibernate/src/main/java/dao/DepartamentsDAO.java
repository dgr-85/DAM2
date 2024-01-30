package dao;

import java.util.ArrayList;

import pojos.Departaments;

public interface DepartamentsDAO {

	public Integer addDepartament(Departaments departaments, Boolean incloureEmpleats);

	public Departaments getDepartamentById(int id);

	public Integer updateDepartament(Departaments departaments);

	public Integer deleteDepartament(int id);

	public ArrayList<Departaments> listAllDepartaments();
}
