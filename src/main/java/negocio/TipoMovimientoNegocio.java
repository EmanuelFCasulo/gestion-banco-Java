package negocio;

import java.util.List;

import entidad.TipoMovimiento;

public interface TipoMovimientoNegocio {
	
	public boolean Insert(TipoMovimiento tipo_movimiento_a_agregar);
	public boolean EliminacionLogica(TipoMovimiento tipo_movimiento_a_eliminar);
	public boolean Update(TipoMovimiento tipo_movimiento_a_modificar);
	public List<TipoMovimiento> BuscarTodos();	
	public TipoMovimiento BuscarUno(int codTipoMovimiento);
	public int BuscarUltimo();
	
}
