package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TipoCuentaDao;
import entidad.TipoCuenta;

public class TipoCuentaDaoImpl implements TipoCuentaDao {
	private static final String insert = "INSERT INTO TiposCuentas(tipoCuenta) VALUES (?,?)";
	private static final String logicalDeletion = "UPDATE TiposCuentas set estado = 0 Where codTipo = ?";
	private static final String readall = "SELECT * FROM TiposCuentas";
	private static final String readOne = "SELECT * FROM TiposCuentas Where codTipo = ?";
	
	@Override
	public boolean Insert(TipoCuenta tipo_cuenta_a_agregar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();   //setInt setDouble
		}

		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, tipo_cuenta_a_agregar.getTipoCuenta());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {

			System.out.println("Error al intentar ingresar el registro");
		}

		return isInsertExitoso;
	}

	@Override
	public boolean EliminacionLogica(TipoCuenta tipo_cuenta_a_eliminar) {
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
			statement.setBoolean(1, tipo_cuenta_a_eliminar.isEstado());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isLogicalDeletionExitoso = true;
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer Borrar el registro(SQL ERROR)");
		}
		return isLogicalDeletionExitoso;
	}

	@Override
	public List<TipoCuenta> BuscarTodas() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<TipoCuenta> tipoCuenta = new ArrayList<TipoCuenta>();
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
				tipoCuenta.add(getTipoCuenta(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return tipoCuenta;
	}

	@Override
	public TipoCuenta BuscarUna(int codTipo) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		TipoCuenta tipoCuenta = new TipoCuenta();
		Conexion conexion = Conexion.getConexion();
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readOne);
			statement.setInt(1, codTipo);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				tipoCuenta = getTipoCuenta(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer   el registro(SQL ERROR)");
		}
		
		return tipoCuenta;
	}

	private TipoCuenta getTipoCuenta(ResultSet resultSet) throws SQLException {
		
		int codTipo = resultSet.getInt("codTipo");
		String tipoCuenta = resultSet.getString("tipoCuenta");
		Boolean estado = resultSet.getBoolean("estado");
		
		return new TipoCuenta(codTipo, tipoCuenta, estado);
	}

	

}
