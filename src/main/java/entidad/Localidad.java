package entidad;

public class Localidad {

	private int codLocalidad;
	private String localidad;
	private Provincia provincia;
	private Pais pais;
	private boolean estado;
	
	public Localidad() { }

	public Localidad(int codLocalidad, Provincia provincia, Pais pais, String localidad, boolean estado) {
		this.codLocalidad = codLocalidad;
		this.provincia = provincia;
		this.pais = pais;
		this.localidad = localidad;
		this.estado = estado;
	}

	public int getCodLocalidad() {
		return codLocalidad;
	}

	public void setCodLocalidad(int codLocalidad) {
		this.codLocalidad = codLocalidad;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Localidad [codLocalidad=" + codLocalidad + ", provincia=" + provincia + ", pais=" + pais
				+ ", localidad=" + localidad + ", estado=" + estado + "]";
	}

}
