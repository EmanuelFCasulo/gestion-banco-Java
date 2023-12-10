package negocioImpl;

import java.util.List;

import dao.TipoUsuarioDao;
import daoImpl.TipoUsuarioDaoImpl;
import entidad.TipoUsuario;
import negocio.TipoUsuarioNegocio;


public class TipoUsuarioNegocioImpl implements TipoUsuarioNegocio {
	
	TipoUsuarioDao tDao = new TipoUsuarioDaoImpl();

	public List<TipoUsuario> BuscarTodos() {
		List<TipoUsuario> lTipo;
		lTipo = tDao.BuscarTodos();
		return lTipo;
	}

	public TipoUsuario BuscarUno(int codUsuario) {

		return tDao.BuscarUno(codUsuario);
	}

}
