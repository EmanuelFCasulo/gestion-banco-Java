package negocio;

import java.util.List;

import entidad.Usuario;

public interface UsuarioNegocio {

	public Boolean IniciarSesion(Usuario usuario);
	
	public boolean Insert(Usuario usuario);
	
	public boolean Update(Usuario usuario);
	
	public boolean EliminacionLogica(String dni); // Baja lógica

	public Usuario BuscarUno(String dni);
	
	public List<Usuario> BuscarTodos();

//	public String readLast();

}
