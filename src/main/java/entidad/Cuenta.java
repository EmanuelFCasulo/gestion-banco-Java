package entidad;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


public class Cuenta {
	
	private int nroCuenta;  
	private Long cbu;  
	private Cliente dni;
	private Date fecha_creacion; 
	private TipoCuenta tipoCuenta;
	private BigDecimal saldo;
	private boolean estado;

	public Cuenta() { }

	public Cuenta(int nroCuenta, Long cbu, Cliente dni, Date fecha_creacion, TipoCuenta tipoCuenta, BigDecimal saldo,
			boolean estado) {
		this.nroCuenta = nroCuenta;
		this.cbu = cbu;
		this.dni = dni;
		this.fecha_creacion = fecha_creacion;
		this.tipoCuenta = tipoCuenta;
		this.saldo = saldo;
		this.estado = estado;
	}

	public int getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(int nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public Long getCbu() {
		return cbu;
	}

	public void setCbu(Long cbu) {
		this.cbu = cbu;
	}

	public Cliente getDni() {
		return dni;
	}

	public void setDni(Cliente dni) {
		this.dni = dni;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public TipoCuenta getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(TipoCuenta tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cuenta [nroCuenta=" + nroCuenta + ", cbu=" + cbu + ", dni=" + dni + ", fecha_creacion=" + fecha_creacion
				+ ", tipoCuenta=" + tipoCuenta + ", saldo=" + saldo + ", estado=" + estado + "]";
	}

}
