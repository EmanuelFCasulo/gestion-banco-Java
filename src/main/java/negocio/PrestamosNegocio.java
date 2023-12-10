package negocio;

import java.util.List;

import entidad.Cuota;
import entidad.Prestamo;

public interface PrestamosNegocio {
	public List<Prestamo> LeerDni(String dni);
	public List<Cuota> ObtenerCuota(int codPrestamo);
}
