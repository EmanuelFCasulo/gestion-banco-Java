package negocio;

import java.math.BigDecimal;
import java.util.Map;

public interface ReporteNegocio {
	public Map<String, Integer> ClientesxLocalidad();
	public Map<String, Integer> MovimientosPorTipo();
	public Map<String, Integer> CuentasPorTipo();
	public Map<String, BigDecimal> SaldoPromXTipoCuenta();
	public Map<String, Integer> PrestamosXCliente();


}
