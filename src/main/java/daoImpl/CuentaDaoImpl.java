package daoImpl;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.cj.jdbc.CallableStatement;

import dao.ClienteDao;
import dao.CuentaDao;
import dao.TipoCuentaDao;
import entidad.Cuenta;
import entidad.TipoCuenta;
import entidad.Cliente;

public class CuentaDaoImpl implements CuentaDao{
	private static final String insert = "insert into cuentas(`CBU`,`dni`, `fecha_creacion`, `tipoCuenta`, `saldo`) values(?,?, current_date(),?,'10000')";
	private static final String logicalDeletion = "UPDATE Cuentas set estado = false Where nroCuenta = ?";
	private static final String readall = "SELECT * FROM Cuentas";
	private static final String readlast = "SELECT * FROM Cuentas ORDER by nroCuenta DESC LIMIT 1";
	private static final String readOne = "SELECT * FROM Cuentas Where nroCuenta = ?";
	private static final String readOneCbu = "SELECT * FROM Cuentas Where CBU = ?";
	private static final String readForClient = "SELECT * FROM Cuentas Where dni LIKE ? and estado = 1";
	private static final String update = "UPDATE Cuentas set saldo = ?, CBU = ?, dni = ?, tipoCuenta = ? Where nroCuenta = ?";
	private static final String cbuMax = "select max(cbu) +1  from cuentas";
	private static final String readForClientnroCta = "SELECT * FROM Cuentas Where nroCuenta = ? and estado = 1";


	public long BuscarMaxCbu() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		long maxCbu = 0;
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(cbuMax);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				maxCbu = resultSet.getLong(1);
			}
		} catch (SQLException e) {
			System.out.print("Error de base de datos (SQL ERROR )");
		}
		return maxCbu;
	}

	public boolean Insert(Cuenta cuenta_a_agregar) {
		
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
	
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();   //setInt setDouble
		}

		try {
			 CallableStatement cs = (CallableStatement) conexion.prepareCall(insert);
			cs.setLong(1, BuscarMaxCbu());
			cs.setString(2, cuenta_a_agregar.getDni().getDni());
			cs.setInt(3, cuenta_a_agregar.getTipoCuenta().getCodTipo());

			if (cs.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {

			System.out.println("Error al intentar ingresar el registro");
		}

		return isInsertExitoso;
	}

	public boolean EliminacionLogica(Cuenta cuenta_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isLogicalDeletionExitoso = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.prepareStatement(logicalDeletion);
			statement.setInt(1, cuenta_a_eliminar.getNroCuenta());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isLogicalDeletionExitoso = true;
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer Borrar el registro(SQL ERROR)");
		}
		return isLogicalDeletionExitoso;
	}

	public List<Cuenta> BuscarTodos() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Cuenta> cuenta = new ArrayList<Cuenta>();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cuenta.add(getCuenta(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return cuenta;
	}

	public int BuscarUltimo() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Cuenta cuenta = new Cuenta();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readlast);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cuenta = getCuenta(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error de base de datos (SQL ERROR )");
		}
		return cuenta.getNroCuenta();
	}

	public Cuenta BuscarUno(int nroCuenta) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Cuenta cuenta = new Cuenta();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readOne);
			statement.setInt(1, nroCuenta);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cuenta = getCuenta(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error de base de datos (SQL ERROR )");
		}

		return cuenta;
	}

	public Cuenta BuscarCbu(String cbu) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Cuenta cuenta = new Cuenta();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readOneCbu);
			statement.setString(1, cbu);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cuenta = getCuenta(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error de base de datos (SQL ERROR)");
		}

		return cuenta;
	}
	
	public List<Cuenta> BuscarClienteDni(String dni) {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Cuenta> cuenta = new ArrayList<Cuenta>();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readForClient);
			statement.setString(1,  "%" + dni + "%");
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cuenta.add(getCuenta(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al querer leer las cuentas del registro(SQL ERROR)");
		}

		return cuenta;
	}
	
	public List<Cuenta> BuscarClienteNroCta(int nroCta) {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Cuenta> cuenta = new ArrayList<Cuenta>();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readForClientnroCta);
			statement.setInt(1,  nroCta);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cuenta.add(getCuenta(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al querer leer las cuentas del registro(SQL ERROR)");
		}

		return cuenta;
	}

	private Cuenta getCuenta(ResultSet resultSet) throws SQLException {

		int nroCuenta = resultSet.getInt("nroCuenta");
		//int cbu = resultSet.getInt("cbu");
		Long cbu = resultSet.getLong("cbu");
		String dni = resultSet.getString("dni");
		ClienteDao clienteDao = new ClienteDaoImpl();
		Cliente cliente = clienteDao.BuscarUno(dni);
		Date fecha_creacion = resultSet.getDate("fecha_creacion");
		int numCuenta = resultSet.getInt("tipoCuenta");
		TipoCuentaDao tipoCuentaDao = new TipoCuentaDaoImpl();
		TipoCuenta tipoCuenta = tipoCuentaDao.BuscarUna(numCuenta);
		BigDecimal saldo = resultSet.getBigDecimal("saldo");
		Boolean estado = resultSet.getBoolean("estado");
		return new Cuenta(nroCuenta,cbu,cliente,fecha_creacion,tipoCuenta,saldo,estado);
	}


public boolean Update(Cuenta cuenta_a_actualizar) {

		System.out.println(cuenta_a_actualizar.toString());
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
				statement.setBigDecimal(1, cuenta_a_actualizar.getSaldo());
				statement.setLong(2, cuenta_a_actualizar.getCbu());
				statement.setString(3, cuenta_a_actualizar.getDni().getDni());
				statement.setString(4, cuenta_a_actualizar.getTipoCuenta().getTipoCuenta());
				statement.setInt(5,cuenta_a_actualizar.getNroCuenta());

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

}
