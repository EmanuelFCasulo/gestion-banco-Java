package entidad;

public class TipoMovimiento {
	
	private int codTipo;
	private String tipoMovimiento;
	private boolean estado;

	public TipoMovimiento() {
		this.estado=true;
	}

	public TipoMovimiento(int codTipo, String tipoMovimiento, boolean estado) {
		this.codTipo = codTipo;
		this.tipoMovimiento = tipoMovimiento;
		this.estado = estado;
	}

	public int getCodTipo() {
		return codTipo;
	}

	public void setCodTipo(int codTipo) {
		this.codTipo = codTipo;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "TiposMovimientos [codTipo=" + codTipo + ", tipoMovimiento=" + tipoMovimiento + ", estado=" + estado
				+ "]";
	}
	
}
