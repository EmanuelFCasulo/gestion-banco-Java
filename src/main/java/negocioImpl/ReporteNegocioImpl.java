package negocioImpl;

import java.math.BigDecimal;
import java.util.Map;

import dao.ReporteDao;
import daoImpl.ReporteDaoImpl;
import negocio.ReporteNegocio;

public class ReporteNegocioImpl implements ReporteNegocio{

	ReporteDao rneg = new ReporteDaoImpl();
	
	@Override
	public Map<String, Integer> ClientesxLocalidad() {
		 Map<String, Integer> cliXLoc = rneg.ClientesxLocalidad();
		return cliXLoc;
	}

	@Override
	public Map<String, Integer> MovimientosPorTipo() {
		Map<String, Integer> movXTipo = rneg.MovimientosPorTipo();
		return movXTipo;
	}

	@Override
	public Map<String, Integer> CuentasPorTipo() {
		Map<String, Integer> ctaXTipo = rneg.CuentasPorTipo();
		return ctaXTipo;
	}

	@Override
	public Map<String, BigDecimal> SaldoPromXTipoCuenta() {
		Map<String, BigDecimal> promSaldo = rneg.SaldoPromXTipoCuenta();
		return promSaldo;
	}

	@Override
	public Map<String, Integer> PrestamosXCliente() {
		Map<String, Integer> prestXCliente = rneg.PrestamosXCliente();
		return prestXCliente;
	}

}
