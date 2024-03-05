package dao;

import java.util.ArrayList;

import model.Empleat;

public interface EmpleatDAO {
	// CRUD

	// Create
	public Integer addEmpleat(Empleat e);

	// Read
	public Empleat getEmpleatById(Integer id, Boolean invocacioRecursiva);

	// Update
	public int updateEmpleat(Empleat e);

	// Delete
	public int deleteEmpleat(Integer id);

	// Consultes
	public ArrayList<Empleat> listEmpleats();
}
