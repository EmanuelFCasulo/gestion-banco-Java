package dao;

import java.util.List;

import entidad.TipoUsuario;

public interface TipoUsuarioDao {
	
	public List<TipoUsuario> BuscarTodos();
	
	public TipoUsuario BuscarUno(int codTipoUsuario);

}
