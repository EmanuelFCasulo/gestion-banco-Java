package negocioImpl;

import java.util.List;

import dao.PrestamosxAutorizarDao;
import daoImpl.PrestamosxAutorizarDaoImpl;
import entidad.PrestamoxAutorizar;
import negocio.PrestamosxAutorizarNegocio;

public class PrestamosxAutorizarNegocioImpl implements PrestamosxAutorizarNegocio {
	
	PrestamosxAutorizarDao pxaDao = new PrestamosxAutorizarDaoImpl();
	
	@Override
	public boolean Insert(PrestamoxAutorizar prestamo) {

		boolean estado = false;
		estado = pxaDao.Insert(prestamo);
		return estado;
	}
	

	@Override
	public boolean EliminacionLogica(PrestamoxAutorizar prestamo) {
		boolean estado=false;
		if( prestamo.getNroCuenta()  > 0 )
		{
			estado=pxaDao.EliminacionLogica(prestamo);
		}
		return estado;
	}
	
	
	
	@Override
	public List<PrestamoxAutorizar> BuscarTodos() {
		List<PrestamoxAutorizar> prestamo;
		prestamo = pxaDao.BuscarTodos();
		return prestamo;
	
	}

	@Override
	public PrestamoxAutorizar BuscarUno(int nroPrestamo) {
		PrestamoxAutorizar prestamo = new PrestamoxAutorizar();
		prestamo = pxaDao.BuscarUno(nroPrestamo);
		return prestamo; 
	}

	@Override
	public boolean Update(PrestamoxAutorizar prestamo) {
		boolean estado=false;
		if( prestamo.getCodPrestamoPendiente()  > 0 )
		{
			estado=pxaDao.Update(prestamo);
			
		}
		return estado;
	
	}
	public int ContarPrestamos() {

		int cant;
		cant = pxaDao.ContarPrestamos();
		return cant;
	}

	@Override
	public List<PrestamoxAutorizar> BuscarAcivos() {
		List<PrestamoxAutorizar> prestamo;
		prestamo = pxaDao.BuscarActivos();
		return prestamo;
	
	}


	
	
}