package dao;

import java.util.List;

import entidad.TipoCuenta;

public interface TipoCuentaDao {
	public boolean Insert(TipoCuenta tipo_cuenta_a_agregar);
	public boolean EliminacionLogica(TipoCuenta tipo_cuenta_a_eliminar);
	public List<TipoCuenta> BuscarTodas();
	public TipoCuenta BuscarUna(int codTipo);

}
