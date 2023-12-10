package dao;

import java.util.List;

import entidad.Usuario;

public interface UsuarioDao {
	
	public Boolean IniciarSesion(Usuario usuario);
	public boolean Insert(Usuario usuario);
	public boolean EliminacionLogica(String dni); 
	public boolean Update(Usuario usuario_a_modificar);
	public Usuario BuscarUno(String dni);
	public List<Usuario> BuscarTodos();
}
