package negocio;

import java.util.List;

import entidad.PrestamoxAutorizar;

public interface PrestamosxAutorizarNegocio {
	
	public boolean Insert(PrestamoxAutorizar prestamo);
	public boolean EliminacionLogica(PrestamoxAutorizar prestamo);  // Baja lógica
	public boolean Update(PrestamoxAutorizar prestamo);
	public List<PrestamoxAutorizar> BuscarTodos();
	public List<PrestamoxAutorizar> BuscarAcivos();
	public PrestamoxAutorizar BuscarUno(int nroCuenta);
	public int ContarPrestamos();
	
}