package negocio;

import java.util.List;

import entidad.Pais;

public interface PaisNegocio {
	
	public boolean Insert(Pais pais_a_agregar);
	public boolean EliminacionLogica(Pais pais_a_eliminar); 
	public boolean Update(Pais pais_a_modificar);
	public List<Pais> BuscarTodos();
	public int BuscarUltimo();
}
