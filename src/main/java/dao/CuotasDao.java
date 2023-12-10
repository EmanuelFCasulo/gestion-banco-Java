package dao;

import java.math.BigDecimal;

public interface CuotasDao {
	public boolean pagarCuota(int NroCuenta,int idCuota, BigDecimal saldo, String detalle);
}
