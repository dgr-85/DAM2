package pojos;
// Generated 19 de febr. 2024, 15:45:50 by Hibernate Tools 6.3.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Idiomes generated by hbm2java
 */
public class Idiomes implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int codi;
	private String descripcio;
	private Set<Llibres> llibreses = new HashSet<>(0);

	public Idiomes() {
	}

	public Idiomes(int codi, String descripcio, Set<Llibres> llibreses) {
		this.codi = codi;
		this.descripcio = descripcio;
		this.llibreses = llibreses;
	}

	public int getCodi() {
		return this.codi;
	}

	public void setCodi(int codi) {
		this.codi = codi;
	}

	public String getDescripcio() {
		return this.descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public Set<Llibres> getLlibreses() {
		return this.llibreses;
	}

	public void setLlibreses(Set<Llibres> llibreses) {
		this.llibreses = llibreses;
	}

	@Override
	public String toString() {
		return "Idiomes [codi=" + codi + ", descripcio=" + descripcio + ", llibreses=" + llibreses + "]";
	}

}
