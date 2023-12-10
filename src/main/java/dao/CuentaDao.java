package dao;

import java.util.List;

import entidad.Cuenta;

public interface  CuentaDao {
	
	public boolean Insert(Cuenta cuenta_a_agregar);
	
	public boolean Update(Cuenta cuenta_a_agregar);
	
	public boolean EliminacionLogica(Cuenta cuenta_a_eliminar);

	public List<Cuenta> BuscarTodos();

	public List<Cuenta> BuscarClienteDni(String dni);

	public Cuenta BuscarCbu(String cbu);
	
	public Cuenta BuscarUno(int nroCuenta);

	public int BuscarUltimo();

	public List<Cuenta> BuscarClienteNroCta(int nroCta);
}
