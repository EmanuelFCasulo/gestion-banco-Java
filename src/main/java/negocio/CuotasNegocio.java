package negocio;

import java.math.BigDecimal;

public interface CuotasNegocio {
	public boolean PagarCuota(int NroCuenta,int idCuota, BigDecimal importe);
}
