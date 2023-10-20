package dao;

import model.Empleat;

public interface EmpleatDAO {
	// CRUD

	// Create
	public int addEmpleat(Empleat e);

	// Read
	public Empleat getEmpleatById(int id);

	// Update
	public int updateEmpleat(int id);

	// Delete
	public int deleteEmpleat(int id);

	// Consultes
}
