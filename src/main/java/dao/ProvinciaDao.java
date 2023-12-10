package dao;

import java.util.List;

import entidad.Provincia;

public interface ProvinciaDao {
	
	public boolean Insert(Provincia provincia_a_agregar);
	public boolean EliminacionLogica(Provincia provincia_a_eliminar);  
	public boolean Update(Provincia provincia_a_modificar);
	public List<Provincia> BuscarTodas();
	public Provincia BuscarUna(int codProvincia);
	public int Buscarultima();
	
}
