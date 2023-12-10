package entidad;

public class Provincia {
	
	private int codProvincia;
	private Pais pais;
	private String provincia;
	private boolean estado;
	
	public Provincia() { }

	public Provincia(int codProvincia, Pais pais, String provincia, boolean estado) {
		this.codProvincia = codProvincia;
		this.pais = pais;
		this.provincia = provincia;
		this.estado = estado;
	}

	public int getCodProvincia() {
		return codProvincia;
	}

	public void setCodProvincia(int codProvincia) {
		this.codProvincia = codProvincia;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Provincia [codProvincia=" + codProvincia + ", pais=" + pais + ", provincia=" + provincia + ", estado="
				+ estado + "]";
	}
	
	
}
