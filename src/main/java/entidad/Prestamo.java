package entidad;

import java.math.BigDecimal;
import java.util.Date;

public class Prestamo {
	
	private int codPrestamo;  
	private Cliente dni;
	private Date fecha; 
	private BigDecimal importe_a_pagar;
	private BigDecimal importe_pedido;
	private int plazo_pago;
	private BigDecimal monto_mensual;
	private int cantidad_cuotas;
	private boolean estado;

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Prestamo() {	}

	public Prestamo(int codPrestamo, Cliente dni, Date fecha, BigDecimal importe_a_pagar, BigDecimal importe_pedido,
			int plazo_pago, BigDecimal monto_mensual, int cantidad_cuotas) {
		this.codPrestamo = codPrestamo;
		this.dni = dni;
		this.fecha = fecha;
		this.importe_a_pagar = importe_a_pagar;
		this.importe_pedido = importe_pedido;
		this.plazo_pago = plazo_pago;
		this.monto_mensual = monto_mensual;
		this.cantidad_cuotas = cantidad_cuotas;
	}
	
	public Prestamo(int codPrestamo, Date fecha, BigDecimal importe_a_pagar, BigDecimal importe_pedido,
			int plazo_pago, BigDecimal monto_mensual, int cantidad_cuotas, boolean estado) {
		this.codPrestamo = codPrestamo;
		this.fecha = fecha;
		this.importe_a_pagar = importe_a_pagar;
		this.importe_pedido = importe_pedido;
		this.plazo_pago = plazo_pago;
		this.monto_mensual = monto_mensual;
		this.cantidad_cuotas = cantidad_cuotas;
		this.estado = estado;
	}

	public int getCodPrestamo() {
		return codPrestamo;
	}

	public void setCodPrestamo(int codPrestamo) {
		this.codPrestamo = codPrestamo;
	}

	public Cliente getDni() {
		return dni;
	}

	public void setDni(Cliente dni) {
		this.dni = dni;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getImporte_a_pagar() {
		return importe_a_pagar;
	}

	public void setImporte_a_pagar(BigDecimal importe_a_pagar) {
		this.importe_a_pagar = importe_a_pagar;
	}

	public BigDecimal getImporte_pedido() {
		return importe_pedido;
	}

	public void setImporte_pedido(BigDecimal importe_pedido) {
		this.importe_pedido = importe_pedido;
	}

	public int getPlazo_pago() {
		return plazo_pago;
	}

	public void setPlazo_pago(int plazo_pago) {
		this.plazo_pago = plazo_pago;
	}

	public BigDecimal getMonto_mensual() {
		return monto_mensual;
	}

	public void setMonto_mensual(BigDecimal monto_mensual) {
		this.monto_mensual = monto_mensual;
	}

	public int getCantidad_cuotas() {
		return cantidad_cuotas;
	}

	public void setCantidad_cuotas(int cantidad_cuotas) {
		this.cantidad_cuotas = cantidad_cuotas;
	}

	@Override
	public String toString() {
		return "Prestamo [codPrestamo=" + codPrestamo + ", dni=" + dni + ", fecha=" + fecha + ", importe_a_pagar="
				+ importe_a_pagar + ", importe_pedido=" + importe_pedido + ", plazo_pago=" + plazo_pago
				+ ", monto_mensual=" + monto_mensual + ", cantidad_cuotas=" + cantidad_cuotas + "]";
	}	

}
