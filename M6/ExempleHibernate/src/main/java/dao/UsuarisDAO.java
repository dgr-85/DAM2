package dao;

import java.util.ArrayList;

import pojos.Usuaris;

public interface UsuarisDAO {
	public Integer addUsuari(Usuaris usuaris);

	public Usuaris getUsuarisById(int id);

	public void updateUsuaris(Usuaris usuaris);

	public void deleteUsuaris(int id);

	public ArrayList<Usuaris> listAllUsuaris();
}
