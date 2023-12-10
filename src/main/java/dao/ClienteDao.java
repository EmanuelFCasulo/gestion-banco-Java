package dao;

import java.util.List;

import entidad.Cliente;


public interface ClienteDao {
	
	public boolean Insert(Cliente cliente);

	public boolean EliminacionLogica(Cliente cliente_a_eliminar); // Baja lógica

	public boolean Update(Cliente cliente_a_modificar);

	public Cliente BuscarUno(String dni);
	
	public List<Cliente> BuscarTodos();
	
	public String BuscarUltimo();

	public int ClientesActivos();

}
