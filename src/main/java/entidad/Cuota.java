package entidad;

import java.math.BigDecimal;
import java.sql.Date;

public class Cuota {
	private int idCuota;
	private int codPrestamo;
	private int nroCuota;
	private Date fecha_venc;
	private Date fecha_pago;
	private BigDecimal importe;
	private boolean estado;
	
	
	public Cuota(int idCuota, int codPrestamo, int nroCuota, Date fecha_venc, Date fecha_pago, BigDecimal importe,
			boolean estado) {
		this.idCuota = idCuota;
		this.codPrestamo = codPrestamo;
		this.nroCuota = nroCuota;
		this.fecha_venc = fecha_venc;
		this.fecha_pago = fecha_pago;
		this.importe = importe;
		this.estado = estado;
	}
	
	public Cuota(int codPrestamo, int nroCuota, Date fecha_venc, Date fecha_pago, BigDecimal importe,
			boolean estado) {
		this.codPrestamo = codPrestamo;
		this.nroCuota = nroCuota;
		this.fecha_venc = fecha_venc;
		this.fecha_pago = fecha_pago;
		this.importe = importe;
		this.estado = estado;
	}
	
	public Cuota(int idCuota, int codPrestamo, int nroCuota, BigDecimal importe,
			boolean estado) {
		this.idCuota = idCuota;
		this.codPrestamo = codPrestamo;
		this.nroCuota = nroCuota;
		this.importe = importe; 
		this.estado = estado;
	}
	
	public int getIdCuota() {
		return idCuota;
	}
	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
	}
	public int getCodPrestamo() {
		return codPrestamo;
	}
	public void setCodPrestamo(int codPrestamo) {
		this.codPrestamo = codPrestamo;
	}
	public int getNroCuota() {
		return nroCuota;
	}
	public void setNroCuota(int nroCuota) {
		this.nroCuota = nroCuota;
	}
	public Date getFecha_venc() {
		return fecha_venc;
	}
	public void setFecha_venc(Date fecha_venc) {
		this.fecha_venc = fecha_venc;
	}
	public Date getFecha_pago() {
		return fecha_pago;
	}
	public void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
