package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidad.Movimiento;

public interface  MovimientoDao {
	
	public boolean Insert(Movimiento movimiento);
	
	public ArrayList<Movimiento> BuscarTodos();
	
	public ArrayList<Movimiento> BuscarNro(int nroCuenta);
	
	public Movimiento BuscarUltimo();

	public ArrayList<Movimiento> BuscarPorTipo(int tipoMovimiento, Date fechaInial, Date fechaFianl);

	public ArrayList<Movimiento> BuscarDesde(Date fechaInicio);

	public ArrayList<Movimiento> BuscarHasta(Date fechaFinal);
}
