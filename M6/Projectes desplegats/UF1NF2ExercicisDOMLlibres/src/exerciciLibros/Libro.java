package exerciciLibros;

public class Libro {
	String titol;
	String autor;
	Integer anyPublicacio;
	public String getTitol() {
		return titol;
	}
	public void setTitol(String titol) {
		this.titol = titol;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Integer getAnyPublicacio() {
		return anyPublicacio;
	}
	public void setAnyPublicacio(Integer anyPublicacio) {
		this.anyPublicacio = anyPublicacio;
	}
	@Override
	public String toString() {
		return "Libro [titol=" + titol + ", autor=" + autor
				+ ", anyPublicacio=" + anyPublicacio + "]";
	}
	
}
