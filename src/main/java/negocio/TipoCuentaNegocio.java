package negocio;

import java.util.List;

import entidad.TipoCuenta;

public interface TipoCuentaNegocio {
	
	
	public boolean Insert(TipoCuenta tipo_movimiento_a_agregar);
	public boolean EliminacionLogica(TipoCuenta tipo_movimiento_a_eliminar);
	public List<TipoCuenta> BuscarTodos();
	public TipoCuenta BuscarUno(int codTipoCuenta);



	
}
