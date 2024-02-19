package dao;

import java.util.List;

import pojos.Idiomes;

public interface IdiomesDAO {
	public Integer addIdioma(Idiomes idioma);

	public Idiomes getIdiomaById(Integer id);

	public Integer updateIdioma(Idiomes idioma);

	public Integer deleteIdioma(Integer id);

	public List<Idiomes> listAllIdiomes();
}
