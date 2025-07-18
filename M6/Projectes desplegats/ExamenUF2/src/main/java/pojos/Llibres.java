package pojos;
// Generated 19 de febr. 2024, 15:45:50 by Hibernate Tools 6.3.1.Final

/**
 * Llibres generated by hbm2java
 */
public class Llibres implements java.io.Serializable {

	private int codiLlibre;
	private Autors autors;
	private Idiomes idiomes;
	private String titol;
	private Integer pagines;

	public Llibres() {
	}

	public Llibres(int codiLlibre, Autors autors, Idiomes idiomes, String titol, Integer pagines) {
		this.codiLlibre = codiLlibre;
		this.autors = autors;
		this.idiomes = idiomes;
		this.titol = titol;
		this.pagines = pagines;
	}

	public int getCodiLlibre() {
		return this.codiLlibre;
	}

	public void setCodiLlibre(int codiLlibre) {
		this.codiLlibre = codiLlibre;
	}

	public Autors getAutors() {
		return this.autors;
	}

	public void setAutors(Autors autors) {
		this.autors = autors;
	}

	public Idiomes getIdiomes() {
		return this.idiomes;
	}

	public void setIdiomes(Idiomes idiomes) {
		this.idiomes = idiomes;
	}

	public String getTitol() {
		return this.titol;
	}

	public void setTitol(String titol) {
		this.titol = titol;
	}

	public Integer getPagines() {
		return this.pagines;
	}

	public void setPagines(Integer pagines) {
		this.pagines = pagines;
	}

	@Override
	public String toString() {
		return "Llibres [codiLlibre=" + codiLlibre + ", autors=" + autors + ", idiomes=" + idiomes + ", titol=" + titol
				+ ", pagines=" + pagines + "]";
	}

}
