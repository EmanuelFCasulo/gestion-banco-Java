package negocioImpl;

import java.util.List;

import dao.ProvinciaDao;
import daoImpl.ProvinciaDaoImpl;
import entidad.Localidad;
import entidad.Provincia;
import negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio{
	
	ProvinciaDao prDao = new ProvinciaDaoImpl();

	@Override
	public List<Provincia> BuscarTodas() {

		List<Provincia> lProvincias;
		lProvincias = prDao.BuscarTodas();
		return lProvincias;
	}

	@Override
	public Provincia BuscarUna(int codLocalidad) {
		Provincia provincia = new Provincia();
		provincia = prDao.BuscarUna(codLocalidad);
		
		return provincia;
	}

}
