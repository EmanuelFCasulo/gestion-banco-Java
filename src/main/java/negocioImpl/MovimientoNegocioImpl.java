package negocioImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.MovimientoDao;
import daoImpl.MovimientoDaoImpl;
import entidad.Movimiento;
import negocio.MovimientoNegocio;


public class MovimientoNegocioImpl implements MovimientoNegocio{

	MovimientoDao mDao = new MovimientoDaoImpl();

	public boolean Insert(Movimiento mov) {

		boolean estado = false;
		estado = mDao.Insert(mov);
		
		return estado;
	}


	public ArrayList<Movimiento> BuscarTodos() {

		ArrayList<Movimiento> lMovimiento;
		lMovimiento = mDao.BuscarTodos();
		return lMovimiento;
	}


	public Movimiento BuscarUltimo() {
		System.out.print(mDao.BuscarUltimo());
		return mDao.BuscarUltimo();
	}

	public ArrayList<Movimiento> BuscarNro(int nroCuenta){
		ArrayList<Movimiento> lMovimientos_x_cuenta;
		lMovimientos_x_cuenta = mDao.BuscarNro(nroCuenta);
		return lMovimientos_x_cuenta; 
	}


	public ArrayList<Movimiento> BuscarPorTipo(int tipoMovimiento, Date fechaInicial, Date fechaFinal) {
		ArrayList<Movimiento> tipo_movimiento;
		tipo_movimiento = mDao.BuscarPorTipo(tipoMovimiento, fechaInicial, fechaFinal);
		return tipo_movimiento; 
	}
	
	public ArrayList<Movimiento> BuscarDesde(Date fechaInicio) {
		ArrayList<Movimiento> tipo_movimiento;
		tipo_movimiento = mDao.BuscarDesde(fechaInicio);
		return tipo_movimiento; 
	}

	public ArrayList<Movimiento> BuscarHasta(Date fechaFinal) {
		ArrayList<Movimiento> tipo_movimiento;
		tipo_movimiento = mDao.BuscarHasta(fechaFinal);
		return tipo_movimiento; 
	}

}