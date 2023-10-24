package dao;

import java.util.ArrayList;

import model.Empleat;

public interface EmpleatDAO {
	// CRUD

	// Create
	public int addEmpleat(Empleat e);

	// Read
	public Empleat getEmpleatById(int id);

	// Update
	public int updateEmpleat(Empleat e);

	// Delete
	public int deleteEmpleat(int id);

	// Consultes
	public ArrayList<Empleat> listOfEmpleats();
}
