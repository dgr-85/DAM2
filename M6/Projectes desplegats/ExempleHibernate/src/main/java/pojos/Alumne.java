package pojos;
// Generated 15 de des. 2023, 16:03:53 by Hibernate Tools 6.3.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Alumne generated by hbm2java
 */
public class Alumne implements java.io.Serializable {

	private int nexp;
	private Integer edat;
	private String nom;
	private String tlf;
	private Set assignaturas = new HashSet(0);

	public Alumne() {
	}

	public Alumne(int nexp, Integer edat, String nom, String tlf, Set assignaturas) {
		this.nexp = nexp;
		this.edat = edat;
		this.nom = nom;
		this.tlf = tlf;
		this.assignaturas = assignaturas;
	}

	public int getNexp() {
		return this.nexp;
	}

	public void setNexp(int nexp) {
		this.nexp = nexp;
	}

	public Integer getEdat() {
		return this.edat;
	}

	public void setEdat(Integer edat) {
		this.edat = edat;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTlf() {
		return this.tlf;
	}

	public void setTlf(String tlf) {
		this.tlf = tlf;
	}

	public Set getAssignaturas() {
		return this.assignaturas;
	}

	public void setAssignaturas(Set assignaturas) {
		this.assignaturas = assignaturas;
	}

}
