package daoImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import dao.ClienteDao;
import dao.PrestamosDao;
import dao.TipoCuentaDao;
import entidad.Cliente;
import entidad.Cuenta;
import entidad.Cuota;
import entidad.Prestamo;
import entidad.TipoCuenta;

public class PrestamosDaoImpl implements PrestamosDao{
	
	private static final String readall = "SELECT * FROM prestamos where dni = ?";
	private static final String getcuotas = "select * from cuotas_x_prestamo where codPrestamo  =  ? ";
	
	public List<Prestamo> BuscarDni(String dni) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			statement.setString(1, dni);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				prestamos.add(getPrestamo(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return prestamos;
	}
	
	private Prestamo getPrestamo(ResultSet resultSet) throws SQLException {

		int codPrestamo = resultSet.getInt("codPrestamo");
		Date fecha = resultSet.getDate("fecha");
		BigDecimal importePagar = resultSet.getBigDecimal("importe_a_pagar");
		BigDecimal importePedido = resultSet.getBigDecimal("importe_pedido");
		int plazoPago = resultSet.getInt("plazo_pago");
		BigDecimal montoMensual = resultSet.getBigDecimal("monto_mensual");
		int cantidadCuotas = resultSet.getInt("cantidad_cuotas");
		boolean estado = resultSet.getBoolean("estado");
		return new Prestamo(codPrestamo,fecha,importePagar,importePedido,plazoPago,montoMensual,cantidadCuotas, estado);
	}
	
	public List<Cuota> ObtenerCuota(int codPrestamo){
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Cuota> cuotas = new ArrayList<Cuota>();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(getcuotas);
			statement.setInt(1, codPrestamo);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cuotas.add(getCuota(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return cuotas;
	}
	
	private Cuota getCuota(ResultSet resultSet) throws SQLException {

		int idCuota = resultSet.getInt("idCuota");
		int codPrestamo= resultSet.getInt("codPrestamo");
		int nroCuota= resultSet.getInt("nroCuota");
		Date fecha_venc = resultSet.getDate("fecha_venc");
		Date fecha_pago = resultSet.getDate("fecha_pago");
		BigDecimal importe = resultSet.getBigDecimal("importe");
		boolean estado = resultSet.getBoolean("estado");
		return new Cuota(idCuota,codPrestamo,nroCuota,fecha_venc,fecha_pago,importe,estado);
	}

	@Override
	public boolean Insert(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean EliminacionLogica(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Update(Prestamo prestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Prestamo BuscarUno(int nroCuenta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Prestamo> BuscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Buscarultimo() {
		// TODO Auto-generated method stub
		return null;
	}


	
}




