package negocio;

import java.util.List;

import entidad.Provincia;

public interface ProvinciaNegocio {
	public List<Provincia> BuscarTodas();
	public Provincia BuscarUna(int codLocalidad);
}
