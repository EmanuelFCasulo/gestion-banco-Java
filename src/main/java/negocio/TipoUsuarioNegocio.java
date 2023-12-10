package negocio;

import java.util.List;

import entidad.TipoUsuario;

public interface TipoUsuarioNegocio {
	
public List<TipoUsuario> BuscarTodos();
	
	public TipoUsuario BuscarUno(int codTipoUsuario);

}
