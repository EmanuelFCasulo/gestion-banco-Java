package negocioImpl;

import java.util.List;

import dao.LocalidadDao;
import daoImpl.LocalidadDaoImpl;
import entidad.Localidad;
import negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio {

	LocalidadDao lDao = new LocalidadDaoImpl();

	public boolean Insert(Localidad localidad) {

		boolean estado = false;
		estado = lDao.Insert(localidad);
		
		return estado;
	}
	
	public boolean Update(Localidad localidad) {

		boolean estado = false;
		estado = lDao.Update(localidad);
		
		return estado;
	}

	public boolean EliminacionLogica(Localidad localidad_a_eliminar) {
		boolean estado = false;
		try {
			if (localidad_a_eliminar.getCodLocalidad() > 0) {
				estado = lDao.EliminacionLogica(localidad_a_eliminar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}


	public List<Localidad> BuscarTodas(int codProv) {

		List<Localidad> lLocalidades;
		lLocalidades = lDao.BuscarTodas( codProv);
		return lLocalidades;
	}


	public int BuscarUltima() {
		System.out.print(lDao.BuscarUltima());
		return lDao.BuscarUltima();
	}

	public Localidad BuscarUna(int codLocalidad) {
		
		Localidad localidad = new Localidad();
		localidad = lDao.BuscarUna(codLocalidad);
		
		return localidad;
	}


}
