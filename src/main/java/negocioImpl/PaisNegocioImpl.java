package negocioImpl;

import java.util.List;

import dao.PaisDao;
import daoImpl.PaisDaoImpl;
import entidad.Pais;
import negocio.PaisNegocio;

public class PaisNegocioImpl implements PaisNegocio {

	PaisDao pDao = new PaisDaoImpl();

	public boolean Insert(Pais pais) {

		boolean estado = false;
		estado = pDao.Insert(pais);
		
		return estado;
	}
	
	public boolean Update(Pais pais) {

		boolean estado = false;
		estado = pDao.Update(pais);
		
		return estado;
	}

	public boolean EliminacionLogica(Pais pais_a_eliminar) {
		boolean estado = false;
		try {
			if (pais_a_eliminar.getCodPais() > 0) {
				estado = pDao.EliminacionLogica(pais_a_eliminar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}


	public List<Pais> BuscarTodos () {

		List<Pais> lPaises;
		lPaises = pDao.BuscarTodos();
		return lPaises;
	}


	public int BuscarUltimo() {
		System.out.print(pDao.Buscarultimo());
		return pDao.Buscarultimo();
	}


}