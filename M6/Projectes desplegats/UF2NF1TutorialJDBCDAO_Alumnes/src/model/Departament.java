package model;

import java.util.ArrayList;

public class Departament {
	private Integer codiDepartament;
	private String nomDepartament;
	private String llocDepartament;
	private ArrayList<Empleat> empleats;

	/*
	 * Aqu� podem escollir entre afegir la llista d'empleats (i per tant, quant
	 * portem un departament portarem la llista dels seus empleats o no afegir-la i
	 * si necessitem els empleats d'un departament els anirem a buscar.
	 */
	// private ArrayList<Empleat> llistaEmpleats=new ArrayList<Empleat>();

	public Departament() {
	}

	public Departament(Integer codiDepartament, String nomDepartament, String llocDepartament) {
		super();
		this.codiDepartament = codiDepartament;
		this.nomDepartament = nomDepartament;
		this.llocDepartament = llocDepartament;
		this.empleats = null;
	}

	public Integer getCodiDepartament() {
		return codiDepartament;
	}

	public void setCodiDepartament(Integer codiDepartament) {
		this.codiDepartament = codiDepartament;
	}

	public String getNomDepartament() {
		return nomDepartament;
	}

	public void setNomDepartament(String nomDepartament) {
		this.nomDepartament = nomDepartament;
	}

	public String getLlocDepartament() {
		return llocDepartament;
	}

	public void setLlocDepartament(String llocDepartament) {
		this.llocDepartament = llocDepartament;
	}

	public ArrayList<Empleat> getEmpleats() {
		return empleats;
	}

	public void setEmpleats(ArrayList<Empleat> empleats) {
		this.empleats = empleats;
	}

	@Override
	public String toString() {
		String resultat = "Dades del Departament: [Codi:" + codiDepartament + ", Nom:" + nomDepartament + ", Lloc:"
				+ llocDepartament + "]";

		return resultat;
	}

}
