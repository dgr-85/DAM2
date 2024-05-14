package exerTemps;

import java.util.Date;

public class Dia {
	private Date fecha;
	private Integer probabilidadPrecipitacion;
	private String estadoCielo;
	private String direccionViento;
	private Integer velocidadViento;
	private Integer temperaturaMaxima;
	private Integer temperaturaMinima;
	private Integer sensTermicaMaxima;
	private Integer sensTermicaMinima;
	private Integer humedadMaxima;
	private Integer humedadMinima;

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getProbabilidadPrecipitacion() {
		return probabilidadPrecipitacion;
	}

	public void setProbabilidadPrecipitacion(Integer probabilidadPrecipitacion) {
		this.probabilidadPrecipitacion = probabilidadPrecipitacion;
	}

	public String getEstadoCielo() {
		return estadoCielo;
	}

	public void setEstadoCielo(String estadoCielo) {
		this.estadoCielo = estadoCielo;
	}

	public String getDireccionViento() {
		return direccionViento;
	}

	public void setDireccionViento(String direccionViento) {
		this.direccionViento = direccionViento;
	}

	public Integer getVelocidadViento() {
		return velocidadViento;
	}

	public void setVelocidadViento(Integer velocidadViento) {
		this.velocidadViento = velocidadViento;
	}

	public Integer getTemperaturaMaxima() {
		return temperaturaMaxima;
	}

	public void setTemperaturaMaxima(Integer temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}

	public Integer getTemperaturaMinima() {
		return temperaturaMinima;
	}

	public void setTemperaturaMinima(Integer temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}

	public Integer getSensTermicaMaxima() {
		return sensTermicaMaxima;
	}

	public void setSensTermicaMaxima(Integer sensTermicaMaxima) {
		this.sensTermicaMaxima = sensTermicaMaxima;
	}

	public Integer getSensTermicaMinima() {
		return sensTermicaMinima;
	}

	public void setSensTermicaMinima(Integer sensTermicaMinima) {
		this.sensTermicaMinima = sensTermicaMinima;
	}

	public Integer getHumedadMaxima() {
		return humedadMaxima;
	}

	public void setHumedadMaxima(Integer humedadMaxima) {
		this.humedadMaxima = humedadMaxima;
	}

	public Integer getHumedadMinima() {
		return humedadMinima;
	}

	public void setHumedadMinima(Integer humedadMinima) {
		this.humedadMinima = humedadMinima;
	}

	@Override
	public String toString() {
		return "Dia [fecha=" + fecha + ", probabilidadPrecipitacion=" + probabilidadPrecipitacion + ", estadoCielo="
				+ estadoCielo + ", direccionViento=" + direccionViento + ", velocidadViento=" + velocidadViento
				+ ", temperaturaMaxima=" + temperaturaMaxima + ", temperaturaMinima=" + temperaturaMinima
				+ ", sensTermicaMaxima=" + sensTermicaMaxima + ", sensTermicaMinima=" + sensTermicaMinima
				+ ", humedadMaxima=" + humedadMaxima + ", humedadMinima=" + humedadMinima + "]";
	}
}
