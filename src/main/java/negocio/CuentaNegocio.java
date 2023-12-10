package negocio;

import java.util.List;

import entidad.Cuenta;

public interface CuentaNegocio {

	public boolean Insert(Cuenta cuenta_a_agregar);

	public boolean Delete(Cuenta cuenta_a_eliminar);

	public boolean Update(Cuenta cuenta_a_modificar);

	public List<Cuenta> BuscarTodos();
	
	public List<Cuenta> BuscarClienteDni(String dni);

	public int BuscarUltimo();
	
	public Cuenta BuscarUno(int nroCuenta);

	public boolean Insert(String dni, int tc);

	boolean VerificarMaxCuentas(String dni);
	
	boolean VerificarCliente(String dni);
	
	public Cuenta BuscarCbu(String cbu);
	
	public List<Cuenta> BuscarClienteNroCta(int nroCta);
}