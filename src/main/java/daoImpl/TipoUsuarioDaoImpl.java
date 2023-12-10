package daoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.TipoUsuarioDao;
import entidad.TipoUsuario;

public class TipoUsuarioDaoImpl implements TipoUsuarioDao{

	private static final String readall = "SELECT * FROM TiposUsuarios";
	private static final String readOne = "SELECT * FROM TiposUsuarios Where codTipo = ?";
	
	public List<TipoUsuario> BuscarTodos() {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<TipoUsuario> lTipo = new ArrayList<TipoUsuario>();
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
				lTipo.add(getTipoUsuario(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return lTipo;
	}
	
	public TipoUsuario BuscarUno(int codTipo) {
		PreparedStatement statement;
		ResultSet resultSet; 
		TipoUsuario tipo = new TipoUsuario();
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
				tipo = getTipoUsuario(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer   el registro(SQL ERROR)");
		}
		
		return tipo;
	}
	
	private TipoUsuario getTipoUsuario(ResultSet resultSet) throws SQLException {
	
		int codTipo = resultSet.getInt("codTipo");
		String tipoUsuario = resultSet.getString("tipoUsuario");
		boolean estado = resultSet.getBoolean("estado");
	
		return new TipoUsuario(codTipo, tipoUsuario, estado);
	}


}
