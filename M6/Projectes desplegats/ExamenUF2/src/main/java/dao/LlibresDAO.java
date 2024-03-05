package dao;

import java.util.List;

import pojos.Llibres;

public interface LlibresDAO {
	public Integer addLlibre(Llibres llibre);

	public Llibres getLlibreById(Integer id);

	public Integer updateLlibre(Llibres llibre);

	public Integer deleteLlibre(Integer id);

	public List<Llibres> listAllLlibres();
}
