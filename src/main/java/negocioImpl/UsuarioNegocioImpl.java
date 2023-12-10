package negocioImpl;

import entidad.Usuario;
import negocio.UsuarioNegocio;
import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;

import java.util.List;


public class UsuarioNegocioImpl implements UsuarioNegocio {
	
	UsuarioDao uDao = new UsuarioDaoImpl();

	public Boolean IniciarSesion(Usuario usuario) {
		
		/*Instancio la clase responsable de contectarse con la BD*/		
		/*Si encontro el registro en la BD entonces declaro una session*/
		if (uDao.IniciarSesion(usuario)) {
			return true;
		}
		
		return false;
	}
	
	public boolean Insert(Usuario usuario) {
		boolean estado = false;
		estado = uDao.Insert(usuario);
		return estado;
	}
	
	public boolean Update(Usuario usuario) {

		boolean estado = false;
		estado = uDao.Update(usuario);
		
		return estado;
	}

	public boolean EliminacionLogica(String dni) {
		boolean estado = false;
		try {
			if (dni != null) {
				estado = uDao.EliminacionLogica(dni);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}


	public List<Usuario> BuscarTodos() {

		List<Usuario> lUsuarios;
		lUsuarios = uDao.BuscarTodos();
		return lUsuarios;
	}
	
	public Usuario BuscarUno(String dni) {
		
		Usuario usuario = new Usuario();
		usuario = uDao.BuscarUno(dni);
		
		return usuario;
	}


}
