package dao;

import java.util.List;

import pojos.Autors;

public interface AutorsDAO {
	public Integer addAutor(Autors autor);

	public Autors getAutorById(Integer id);

	public Integer updateAutor(Autors autor);

	public Integer deleteAutor(Integer id);

	public List<Autors> listAllAutors(Boolean includeLlibres);
}
