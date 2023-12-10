package entidad;

public class TipoCuenta {
	
	private int codTipo;
	private String tipoCuenta;
	private boolean estado;

	public TipoCuenta() { }

	public TipoCuenta(int codTipo, String tipoCuenta, boolean estado) {
		this.codTipo = codTipo;
		this.tipoCuenta = tipoCuenta;
		this.estado = estado;
	}

	public int getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(int codTipo) {
		this.codTipo = codTipo;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "TipoCuenta [codTipo=" + codTipo + ", tipoCuenta=" + tipoCuenta + ", estado=" + estado + "]";
	}

}
