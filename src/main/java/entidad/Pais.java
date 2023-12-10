package entidad;

public class Pais {
	
	private int codPais;
	private String pais;
	private boolean estado;
	
	public Pais() {	}

	public Pais(int codPais, String pais, boolean estado) {
		this.codPais = codPais;
		this.pais = pais;
		this.estado = estado;
	}

	public int getCodPais() {
		return codPais;
	}

	public void setCodPais(int codPais) {
		this.codPais = codPais;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Pais [codPais=" + codPais + ", pais=" + pais + ", estado=" + estado + "]";
	}
	
	
}
