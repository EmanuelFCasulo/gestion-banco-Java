package negocio;

import java.util.List;

import entidad.Cliente;

public interface ClienteNegocio {
	
	public boolean Insert(Cliente cliente);

	public boolean EliminacionLogica(Cliente cliente_a_eliminar);

	public boolean Update(Cliente cliente_a_modificar);
	
	public List<Cliente> BuscarTodos();
	
	public Cliente BuscarUno(String dni);

	public String BuscarUltimo();
	
	public int ContarActivo();

}
