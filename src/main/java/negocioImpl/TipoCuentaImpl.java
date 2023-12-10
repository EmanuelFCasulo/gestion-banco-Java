package negocioImpl;

import java.util.List;

import dao.TipoCuentaDao;
import daoImpl.TipoCuentaDaoImpl;
import entidad.TipoCuenta;
import negocio.TipoCuentaNegocio;

public class TipoCuentaImpl implements TipoCuentaNegocio {
	TipoCuentaDao TipoCuentaDao = new TipoCuentaDaoImpl();

	@Override
	public boolean Insert(TipoCuenta tipo_movimiento_a_agregar) {
		boolean estado = false;
		estado = TipoCuentaDao.Insert(tipo_movimiento_a_agregar);
		return estado;
	}

	@Override
	public boolean EliminacionLogica(TipoCuenta localidad_a_eliminar) {
		boolean estado = false;
		try {
			if (localidad_a_eliminar.getCodTipo() > 0) {
				estado = TipoCuentaDao.EliminacionLogica(localidad_a_eliminar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}



	@Override
	public List<TipoCuenta> BuscarTodos() {
		List<TipoCuenta> tc;
		tc = TipoCuentaDao.BuscarTodas();
		return tc;
	}

	@Override
	public TipoCuenta BuscarUno(int codTipoCuenta) {
		TipoCuenta tc = new TipoCuenta();
		tc = TipoCuentaDao.BuscarUna(codTipoCuenta);
		
		return tc;
	}
}

