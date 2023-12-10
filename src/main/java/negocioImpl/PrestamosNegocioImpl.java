package negocioImpl;

import java.util.List;

import daoImpl.PrestamosDaoImpl;
import entidad.Cuota;
import entidad.Prestamo;
import negocio.PrestamosNegocio;

public class PrestamosNegocioImpl implements PrestamosNegocio{
	PrestamosDaoImpl prestamos = new PrestamosDaoImpl();
	public List<Prestamo> LeerDni(String dni){
		return prestamos.BuscarDni(dni);
	}
	
	public List<Cuota> ObtenerCuota(int codPrestamo){
		return prestamos.ObtenerCuota(codPrestamo);
	}
}
