package model;

import java.sql.Date;

public class Empleat {
	private int codiEmpleat;
	private String cognom;
	private String ofici;
	private Empleat director;
	private Date dataAlta;
	private float salari;
	private float comissio;
	private Departament departamentEmpleat;

	public Empleat() {
	}

	public Empleat(int codiEmpleat, String cognom, String ofici, Empleat director, Date dataAlta, float salari,
			float comissio, Departament departamentEmpleat) {
		this.codiEmpleat = codiEmpleat;
		this.cognom = cognom;
		this.ofici = ofici;
		this.director = director;
		this.dataAlta = dataAlta;
		this.salari = salari;
		this.comissio = comissio;
		this.departamentEmpleat = departamentEmpleat;
	}

	public int getCodiEmpleat() {
		return codiEmpleat;
	}

	public void setCodiEmpleat(int codiEmpleat) {
		this.codiEmpleat = codiEmpleat;
	}

	public String getCognom() {
		return cognom;
	}

	public void setCognom(String cognom) {
		this.cognom = cognom;
	}

	public String getOfici() {
		return ofici;
	}

	public void setOfici(String ofici) {
		this.ofici = ofici;
	}

	public Empleat getDirector() {
		return director;
	}

	public void setDirector(Empleat director) {
		this.director = director;
	}

	public Date getDataAlta() {
		return dataAlta;
	}

	public void setDataAlta(Date dataAlta) {
		this.dataAlta = dataAlta;
	}

	public float getSalari() {
		return salari;
	}

	public void setSalari(float salari) {
		this.salari = salari;
	}

	public float getComissio() {
		return comissio;
	}

	public void setComissio(float comissio) {
		this.comissio = comissio;
	}

	public Departament getDepartamentEmpleat() {
		return departamentEmpleat;
	}

	public void setDepartamentEmpleat(Departament departamentEmpleat) {
		this.departamentEmpleat = departamentEmpleat;
	}

	@Override
	public String toString() {
		String resultat = "Dades de l'Empleat: [Codi:" + codiEmpleat + ", Nom:" + cognom + ", Posició:" + ofici
				+ ", Director:" + director.getCognom() + ", Data d'incorporació:" + dataAlta + ", Salari:" + salari
				+ ", Comissió:" + comissio + ", Departament:" + departamentEmpleat.getCodiDepartament() + "]";

		return resultat;
	}

}
