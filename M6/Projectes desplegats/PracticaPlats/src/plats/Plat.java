package plats;

public class Plat {
	private String descripcio;
	private String codi;
	private Integer preu;
	private String tipus;
	private String idGrup;
	private String descripcioGrup;
	private String tipusGrup;

	public Plat() {
		super();
	}

	public Plat(String descripcio, String codi, Integer preu, String tipus, String idGrup, String descripcioGrup,
			String tipusGrup) {
		super();
		this.descripcio = descripcio;
		this.codi = codi;
		this.preu = preu;
		this.tipus = tipus;
		this.idGrup = idGrup;
		this.descripcioGrup = descripcioGrup;
		this.tipusGrup = tipusGrup;
	}

	public String getDescripcio() {
		return descripcio;
	}

	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}

	public String getCodi() {
		return codi;
	}

	public void setCodi(String codi) {
		this.codi = codi;
	}

	public Integer getPreu() {
		return preu;
	}

	public void setPreu(Integer preu) {
		this.preu = preu;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public String getIdGrup() {
		return idGrup;
	}

	public void setIdGrup(String idGrup) {
		this.idGrup = idGrup;
	}

	public String getDescripcioGrup() {
		return descripcioGrup;
	}

	public void setDescripcioGrup(String descripcioGrup) {
		this.descripcioGrup = descripcioGrup;
	}

	public String getTipusGrup() {
		return tipusGrup;
	}

	public void setTipusGrup(String tipusGrup) {
		this.tipusGrup = tipusGrup;
	}

}
