package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TipoMovimientoDao;
import entidad.TipoMovimiento;

public class TipoMovimientoDaoImpl implements TipoMovimientoDao {
	private static final String insert = "INSERT INTO TiposMovimientos(tipoMovimiento) VALUES (?)";
	private static final String logicalDeletion = "UPDATE TiposMovimientos set estado = 0 Where codTipo = ?";
	private static final String readall = "SELECT * FROM TiposMovimientos";
	private static final String update = "UPDATE TiposMovimientos set tipoMovimiento = ? Where codTipo = ?";
	private static final String readlast = "SELECT * FROM TiposMovimientos ORDER by codTipo DESC LIMIT 1";
	private static final String readOne = "SELECT * FROM TiposMovimientos Where codTipo = ?";

	
	public boolean Insert(TipoMovimiento tipo_movimiento_a_agregar) {
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
			statement.setString(1,  tipo_movimiento_a_agregar.getTipoMovimiento());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {

			System.out.println("Error al intentar ingresar el registro");
		}

		return isInsertExitoso;
	}

	
	public boolean EliminacionLogica(TipoMovimiento tipo_movimiento_a_eliminar) {
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
			statement.setBoolean(1, tipo_movimiento_a_eliminar.isEstado());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isLogicalDeletionExitoso = true;
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer Borrar el registro(SQL ERROR)");
		}
		return isLogicalDeletionExitoso;
	}
	
	public List<TipoMovimiento> BuscarTodos() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<TipoMovimiento> tipoMovimiento = new ArrayList<TipoMovimiento>();
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
				tipoMovimiento.add(getTipoMovimiento(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return tipoMovimiento;
	}
	
	public int BuscarUltimo() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
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
				tipoMovimiento = getTipoMovimiento(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer   el registro(SQL ERROR)");
		}
		return tipoMovimiento.getCodTipo();
	}
	
	public TipoMovimiento BuscarUno(int codTipo) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		TipoMovimiento tipoMovimiento = new TipoMovimiento();
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
				tipoMovimiento = getTipoMovimiento(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer   el registro(SQL ERROR)");
		}
		
		return tipoMovimiento;
	}
	
	private TipoMovimiento getTipoMovimiento(ResultSet resultSet) throws SQLException {
	
		int codTipo = resultSet.getInt("codTipo");
		String tipoMovimiento = resultSet.getString("tipoMovimiento");
		boolean estado = resultSet.getBoolean("estado");
	
		return new TipoMovimiento(codTipo, tipoMovimiento, estado);
	}


	@Override
	public boolean Update(TipoMovimiento tipo_movimiento_a_modificar) {
		// TODO Auto-generated method stub
		return false;
	}

}

