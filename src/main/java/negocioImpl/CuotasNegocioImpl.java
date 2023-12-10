package negocioImpl;

import java.math.BigDecimal;

import daoImpl.CuentaDaoImpl;
import daoImpl.CuotasDaoImpl;
import entidad.Cuenta;
import negocio.CuotasNegocio;

public class CuotasNegocioImpl implements CuotasNegocio{
	CuotasDaoImpl cuotasDao = new CuotasDaoImpl();
	
	public boolean PagarCuota(int NroCuenta,int idCuota, BigDecimal importe) {
		CuentaDaoImpl cuenta = new CuentaDaoImpl();
		Cuenta cuentaE;
		cuentaE = cuenta.BuscarUno(NroCuenta);
		if(cuotasDao.pagarCuota(NroCuenta,idCuota, cuentaE.getSaldo().subtract(importe), "Pago cuota - ID" + idCuota))
			return true;
		else
			return false;
	}
}
