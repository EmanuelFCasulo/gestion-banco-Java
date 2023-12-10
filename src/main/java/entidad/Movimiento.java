package entidad;

import java.math.BigDecimal;
import java.util.Date;

public class Movimiento {
	
	private int codMovimiento;  
	private Cuenta nroCuenta;  
	private Date fecha; 
	private BigDecimal importe;
	private TipoMovimiento tipoMovimiento;
	private BigDecimal saldo;
	private String detalle;


	public Movimiento() { }



	public Movimiento(int codMovimiento, Cuenta nroCuenta, Date fecha, BigDecimal importe,
			TipoMovimiento tipoMovimiento, BigDecimal saldo, String detalle) {
		super();
		this.codMovimiento = codMovimiento;
		this.nroCuenta = nroCuenta;
		this.fecha = fecha;
		this.importe = importe;
		this.tipoMovimiento = tipoMovimiento;
		this.saldo = saldo;
		this.detalle = detalle;
	}
	
	public Movimiento( Cuenta nroCuenta, Date fecha, BigDecimal importe,
			TipoMovimiento tipoMovimiento, BigDecimal saldo, String detalle) {
		super();
		this.nroCuenta = nroCuenta;
		this.fecha = fecha;
		this.importe = importe;
		this.tipoMovimiento = tipoMovimiento;
		this.saldo = saldo;
		this.detalle = detalle;
	}
	
	
	public int getCodMovimiento() {
		return codMovimiento;
	}



	public void setCodMovimiento(int codMovimiento) {
		this.codMovimiento = codMovimiento;
	}



	public Cuenta getNroCuenta() {
		return nroCuenta;
	}



	public void setNroCuenta(Cuenta nroCuenta) {
		this.nroCuenta = nroCuenta;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public BigDecimal getImporte() {
		return importe;
	}



	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}



	public TipoMovimiento getTipoMovimiento() {
		return tipoMovimiento;
	}



	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}



	public BigDecimal getSaldo() {
		return saldo;
	}



	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}



	public String getDetalle() {
		return detalle;
	}



	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}



	@Override
	public String toString() {
		return "Movimiento [codMovimiento=" + codMovimiento + ", nroCuenta=" + nroCuenta + ", fecha=" + fecha
				+ ", importe=" + importe + ", tipoMovimiento=" + tipoMovimiento + ", saldo=" + saldo + ", detalle="
				+ detalle + "]";
	}


	
	
}
