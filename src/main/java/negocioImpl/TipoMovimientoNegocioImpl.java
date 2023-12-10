package negocioImpl;

import java.util.List;

import dao.TipoMovimientoDao;
import daoImpl.TipoMovimientoDaoImpl;
import entidad.TipoMovimiento;
import negocio.TipoMovimientoNegocio;

public class TipoMovimientoNegocioImpl implements TipoMovimientoNegocio {
	TipoMovimientoDao tipoMovimientoDao = new TipoMovimientoDaoImpl();
	
	@Override
	public boolean Insert(TipoMovimiento tipo_movimiento_a_agregar) {
		boolean estado = false;
		estado = tipoMovimientoDao.Insert(tipo_movimiento_a_agregar);
		return estado;
	}

	@Override
	public boolean EliminacionLogica(TipoMovimiento tipo_movimiento_a_eliminar) {
		boolean estado = false;
		estado = tipoMovimientoDao.EliminacionLogica(tipo_movimiento_a_eliminar);
		return estado;
	}

	@Override
	public boolean Update(TipoMovimiento tipo_movimiento_a_modificar) {
		boolean estado = false;
		estado = tipoMovimientoDao.Update(tipo_movimiento_a_modificar);
		return estado;
	}

	@Override
	public List<TipoMovimiento> BuscarTodos() {
		List<TipoMovimiento> listaMovimientos;
		listaMovimientos = tipoMovimientoDao.BuscarTodos();
		return listaMovimientos;
	}

	@Override
	public TipoMovimiento BuscarUno(int codTipoMovimiento) {
		System.out.print(tipoMovimientoDao.BuscarUno(codTipoMovimiento));
		return tipoMovimientoDao.BuscarUno(codTipoMovimiento);
	}

	@Override
	public int BuscarUltimo() {
		System.out.print(tipoMovimientoDao.BuscarUltimo());
		return tipoMovimientoDao.BuscarUltimo();
	}

}

