package entidad;

public class TipoUsuario {
	
	private int codTipo;
	private String tipoUsuario;
	private boolean estado;

	public TipoUsuario() { }

	public TipoUsuario(int codTipo, String tipoUsuario, boolean estado) {
		this.codTipo = codTipo;
		this.tipoUsuario = tipoUsuario;
		this.estado = estado; 
	}

	public int getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(int codTipo) {
		this.codTipo = codTipo;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "TiposUsuarios [codTipo=" + codTipo + ", tipoUsuario=" + tipoUsuario + ", estado=" + estado + "]";
	}
	

}
