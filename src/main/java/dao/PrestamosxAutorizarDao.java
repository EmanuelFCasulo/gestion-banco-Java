package dao;

import java.util.List;

import entidad.PrestamoxAutorizar;

public interface PrestamosxAutorizarDao {
	
	public boolean Insert(PrestamoxAutorizar prestamo);
	public boolean Update(PrestamoxAutorizar prestamo);
	public boolean EliminacionLogica(PrestamoxAutorizar prestamo); // Baja lógica
	public List<PrestamoxAutorizar> BuscarTodos();
	public PrestamoxAutorizar BuscarUno(int nroCuenta);
	public List<PrestamoxAutorizar> BuscarActivos();
	public int ContarPrestamos();


}