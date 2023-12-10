package dao;

import java.util.List;

import entidad.Cuota;
import entidad.Prestamo;


public interface PrestamosDao {
	
	public boolean Insert(Prestamo prestamo);

	public boolean EliminacionLogica(Prestamo prestamo); // Baja lógica

	public boolean Update(Prestamo prestamo);

	public Prestamo BuscarUno(int nroCuenta);
	
	public List<Prestamo> BuscarTodos();

	public String Buscarultimo();

	public List<Prestamo> BuscarDni(String dni);
	
	public List<Cuota> ObtenerCuota(int codPrestamo);
}
