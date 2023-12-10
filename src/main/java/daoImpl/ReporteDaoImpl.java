package daoImpl;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dao.ReporteDao;

public class ReporteDaoImpl implements ReporteDao{

	private static final String CLIENTES_X_LOCALIDAD = "SELECT L.localidad, COUNT(C.dni) AS cantidad_clientes " +
	        "FROM Localidades L " +
	        "LEFT JOIN Clientes C ON L.codLocalidad = C.codLocalidad AND L.codProvincia = C.codProvincia AND L.codPais = C.codPais " +
	        "GROUP BY L.localidad " +
	        "HAVING COUNT(C.dni) > 0";
	
	private static final String MOVIMIENTOS_X_TIPO = "SELECT TM.tipoMovimiento, COUNT(M.codMovimiento) AS cantidad_movimientos FROM TiposMovimientos TM LEFT JOIN Movimientos M ON TM.codTipo = M.tipoMovimiento GROUP BY TM.tipoMovimiento";
	private static final String CUENTAS_X_TIPO = "SELECT TC.tipoCuenta, COUNT(C.nroCuenta) AS cantidad_cuentas FROM TiposCuentas TC LEFT JOIN Cuentas C ON TC.codTipo = C.tipoCuenta GROUP BY TC.tipoCuenta";
	private static final String SALDO_PROMEDIO_X_TIPO_CUENTA = "SELECT TC.tipoCuenta, AVG(C.saldo) AS saldo_promedio FROM TiposCuentas TC LEFT JOIN Cuentas C ON TC.codTipo = C.tipoCuenta GROUP BY TC.tipoCuenta";
	private static final String PRESTAMOS_X_CLIENTE = "SELECT CONCAT (C.nombre, \" \", C.apellido) AS nombre_apellido, COUNT(P.codPrestamo) AS cantidad_prestamos FROM Clientes C LEFT JOIN Prestamos P ON C.dni = P.dni WHERE P.estado = 1 and c.nombre <> 'Admin' GROUP BY C.nombre, C.apellido;";
	
	
	@Override
	public Map<String, Integer> ClientesxLocalidad() {
		Map<String, Integer> clientesPorLocalidad = new HashMap<>();
        PreparedStatement statement;
        ResultSet resultSet;
        Conexion conexion = Conexion.getConexion();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
        try {
            statement = conexion.getSQLConexion().prepareStatement(CLIENTES_X_LOCALIDAD);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String localidad = resultSet.getString("localidad");
                int cantidadClientes = resultSet.getInt("cantidad_clientes");
                clientesPorLocalidad.put(localidad, cantidadClientes);
            }
        } catch (SQLException e) {
            System.out.print("Error al intentar leer la cantidad de clientes activos (SQL ERROR)");
        } 
        return clientesPorLocalidad;
	}
	@Override
	public Map<String, Integer> MovimientosPorTipo() {
		Map<String, Integer> movimientosPorTipo = new HashMap<>();
        PreparedStatement statement;
        ResultSet resultSet;
        Conexion conexion = Conexion.getConexion();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
        try {
            statement = conexion.getSQLConexion().prepareStatement(MOVIMIENTOS_X_TIPO);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String tipoMovimiento = resultSet.getString("tipoMovimiento");
                int cantidad = resultSet.getInt("cantidad_movimientos");
                movimientosPorTipo.put(tipoMovimiento, cantidad);
            }
        } catch (SQLException e) {
            System.out.print("Error al intentar leer las cuentas por tipo(SQL ERROR)");
        } 
        return movimientosPorTipo;
	}
	@Override
	public Map<String, Integer> CuentasPorTipo() {
		Map<String, Integer> cuentasPorTipo = new HashMap<>();
        PreparedStatement statement;
        ResultSet resultSet;
        Conexion conexion = Conexion.getConexion();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
        try {
            statement = conexion.getSQLConexion().prepareStatement(CUENTAS_X_TIPO);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String tipoMovimiento = resultSet.getString("tipoCuenta");
                int cantidad = resultSet.getInt("cantidad_cuentas");
                cuentasPorTipo.put(tipoMovimiento, cantidad);
            }
        } catch (SQLException e) {
            System.out.print("Error al intentar leer las cuentas por tipo(SQL ERROR)");
        } 
        return cuentasPorTipo;
	}
	@Override
	public Map<String, BigDecimal> SaldoPromXTipoCuenta() {
		Map<String, BigDecimal> saldoPromxTC = new HashMap<>();
        PreparedStatement statement;
        ResultSet resultSet;
        Conexion conexion = Conexion.getConexion();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
        try {
            statement = conexion.getSQLConexion().prepareStatement(SALDO_PROMEDIO_X_TIPO_CUENTA);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String tipoMovimiento = resultSet.getString("tipoCuenta");
                BigDecimal saldo = resultSet.getBigDecimal("saldo_promedio");
                saldoPromxTC.put(tipoMovimiento, saldo);
            }
        } catch (SQLException e) {
            System.out.print("Error al intentar leer las cuentas por tipo(SQL ERROR)");
        } 
        return saldoPromxTC;
	}
	@Override
	public Map<String, Integer> PrestamosXCliente() {
		Map<String, Integer> prestamosPorCliente = new HashMap<>();
        PreparedStatement statement;
        ResultSet resultSet;
        Conexion conexion = Conexion.getConexion();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
        try {
            statement = conexion.getSQLConexion().prepareStatement(PRESTAMOS_X_CLIENTE);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String tipoMovimiento = resultSet.getString("nombre_apellido");
                int cantidad = resultSet.getInt("cantidad_prestamos");
                prestamosPorCliente.put(tipoMovimiento, cantidad);
            }
        } catch (SQLException e) {
            System.out.print("Error al intentar leer las cuentas por tipo(SQL ERROR)");
        } 
        return prestamosPorCliente;
	}
}
