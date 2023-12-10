package dao;

import java.util.List;

import entidad.Pais;

public interface PaisDao {
	
	public boolean Insert(Pais pais_a_agregar);

	public boolean EliminacionLogica(Pais pais_a_eliminar);  // Baja lógica

	public boolean Update(Pais pais_a_modificar);

	public List<Pais> BuscarTodos();

	public Pais BuscarUno(int codPais);
	
	public int Buscarultimo();

}
