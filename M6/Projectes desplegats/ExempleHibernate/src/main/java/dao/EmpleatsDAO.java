package dao;

import java.util.ArrayList;

import pojos.Empleats;

public interface EmpleatsDAO {

	public Integer addEmpleat(Empleats empleats);

	public Empleats getEmpleatById(int id);

	public Integer updateEmpleat(Empleats empleats);

	public Integer deleteEmpleat(int id);

	public ArrayList<Empleats> listAllEmpleats();

}
