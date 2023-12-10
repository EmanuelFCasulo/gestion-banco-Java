package daoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;

import dao.PrestamosxAutorizarDao;
import entidad.Cuenta;
import entidad.PrestamoxAutorizar;


public class PrestamosxAutorizarDaoImpl implements PrestamosxAutorizarDao {
	
	private static final String insert = "{CALL agregarPrestamoxAutorizar(?,?,?)}";
	private static final String readallActive = "SELECT * FROM prestamos_x_autorizar where estado=1";
	private static final String countallActive = "SELECT COUNT(codPrestamoPendinte) as total  FROM prestamos_x_autorizar where estado = 1";
	private static final String update = "UPDATE prestamos_x_autorizar SET estado = ? WHERE codPrestamoPendinte = ?";
	private static final String countAll = "SELECT COUNT(codPrestamoPendinte) as total FROM agregarPrestamoxAutorizar where estado = 1 ORDER by apellido, dni codPrestamoPendinte";
	
	@Override
	public boolean Insert(PrestamoxAutorizar prestamo) {
		
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
	
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();   //setInt setDouble
		}

		try {
			 CallableStatement cs = (CallableStatement) conexion.prepareCall(insert);
			cs.setInt(1, prestamo.getNroCuenta());
			cs.setBigDecimal(2, prestamo.getImporte());
			cs.setInt(3, prestamo.getCantidad_cuotas());

			if (cs.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {

			System.out.println("Error al intentar ingresar el registro");
		}

		return isInsertExitoso;
	
	}
	
	public boolean EliminacionLogica(PrestamoxAutorizar prestamo) {
		// TODO Auto-generated method stub
		return false;
	}

	public PrestamoxAutorizar BuscarUno(int nroCuenta) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<PrestamoxAutorizar> BuscarTodos() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<PrestamoxAutorizar> prestamoxAutorizar = new ArrayList<PrestamoxAutorizar>();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readallActive);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				prestamoxAutorizar.add(getPrestamoxAutorizar(resultSet));
			
			
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return prestamoxAutorizar;
	}
	
	
	public List<PrestamoxAutorizar> BuscarActivos() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<PrestamoxAutorizar> prestamoxAutorizar = new ArrayList<PrestamoxAutorizar>();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readallActive);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				prestamoxAutorizar.add(getPrestamoxAutorizar(resultSet));
			
			
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return prestamoxAutorizar;
	}

	public boolean Update(PrestamoxAutorizar prestamo) {
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");


		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
				statement = conexion.prepareStatement(update);	
				
				statement.setInt(1,prestamo.getEstado());
				statement.setInt(2,prestamo.getCodPrestamoPendiente());
				
				
				

			if(statement.executeUpdate() > 0){
				conexion.commit();
				isUpdateExitoso  = true;
				}
			} 
		catch (SQLException e1) {
				e1.printStackTrace();
			}


		return isUpdateExitoso;
		}
	
	private PrestamoxAutorizar getPrestamoxAutorizar(ResultSet resultSet) throws SQLException {

		int codPrestamoPendinte = resultSet.getInt("codPrestamoPendinte");
		int nroCuenta = resultSet.getInt("nroCuenta");
		Date fecha_creacion = resultSet.getDate("fecha_creacion");
		BigDecimal importe = resultSet.getBigDecimal("importe_pedido");
		int cantidad_cuotas = resultSet.getInt("cantidad_cuotas");
		int estado = resultSet.getInt("estado");
		return new PrestamoxAutorizar(nroCuenta,codPrestamoPendinte,fecha_creacion,importe,cantidad_cuotas,estado);
	}

	
	public int ContarPrestamos() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		int cant = 0;
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(countallActive);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cant = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer leer la cantidad de Prestamos por Autorizar activos (SQL ERROR)");
		}
		System.out.print(cant);
		return cant;
	}

	
	
	

}
