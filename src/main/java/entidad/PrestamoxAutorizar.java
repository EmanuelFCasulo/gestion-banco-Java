package entidad;

import java.math.BigDecimal;
import java.util.Date;

public class PrestamoxAutorizar {
	


	private int codPrestamoPendiente;
	private Date fecha_creacion;
	private BigDecimal importe;
	private int cantidad_cuotas;
	private int nroCuenta;
	private int estado;

	public PrestamoxAutorizar(int _nroCuenta,int codPrestamoPendinte, Date fecha_creacion, BigDecimal importe,
		int cantidad_cuotas, int _estado) {
		try
		{
			
			this.fecha_creacion = fecha_creacion;
			this.nroCuenta = _nroCuenta;
			this.codPrestamoPendiente= codPrestamoPendinte;
			this.importe=importe;
			this.cantidad_cuotas=cantidad_cuotas;
			this.estado=_estado;
		
		}
		catch(Exception e)
		{
			System.out.println("No se pudo cargar la entidad prestamo");
		}
	}
	
	public int getCodPrestamoPendiente() {
		return this.codPrestamoPendiente;
	}


	public void setCodPrestamoPendiente(int codPrestamoPendiente) {
		this.codPrestamoPendiente = codPrestamoPendiente;
	}


	public Date getFecha_creacion() {
		return fecha_creacion;
	}


	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}


	public BigDecimal getImporte() {
		return importe;
	}


	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}


	public int getCantidad_cuotas() {
		return cantidad_cuotas;
	}


	public void setCantidad_cuotas(int cantidad_cuotas) {
		this.cantidad_cuotas = cantidad_cuotas;
	}


	public int getNroCuenta() {
		return nroCuenta;
	}


	public void setNroCuenta(int nroCuenta) {
		this.nroCuenta = nroCuenta;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}

		

	public PrestamoxAutorizar() {
		// TODO Auto-generated constructor stub
	}






	
}