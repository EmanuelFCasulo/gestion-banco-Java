package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.LocalidadDao;
import dao.PaisDao;
import dao.ProvinciaDao;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;


public class LocalidadDaoImpl implements LocalidadDao {

	private static final String insert = "INSERT INTO Localidades (localidad) VALUES (?)";
	private static final String logicalDeletion = "UPDATE Localidades set estado = 0 Where codLocalidad = ?";
	private static final String readall = "SELECT * FROM Localidades l inner join provincias p on l.codProvincia = p.codProvincia where l.codProvincia = ?";
	private static final String update = "UPDATE Localidades set localidad = ? Where codLocalidad = ?";
	private static final String readlast = "SELECT * FROM Localidades ORDER by codLocalidad DESC LIMIT 1";
	private static final String readOne = "SELECT * FROM Localidades Where codLocalidad = ?";
	
	public boolean Insert(Localidad localidad_a_agregar) {
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
			statement.setString(1,  localidad_a_agregar.getLocalidad());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {

			System.out.println("Error al intentar ingresar el registro");
		}

		return isInsertExitoso;
	}
	
	public boolean Update(Localidad localidad_a_actualizar) {

		System.out.println(localidad_a_actualizar.toString());
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
			statement.setString(1, localidad_a_actualizar.getLocalidad());


			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return isUpdateExitoso;
	}

	public boolean EliminacionLogica(Localidad localidad_a_eliminar) {
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
			statement.setBoolean(1, localidad_a_eliminar.isEstado());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isLogicalDeletionExitoso = true;
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer Borrar el registro(SQL ERROR)");
		}
		return isLogicalDeletionExitoso;
	}
	
	public List<Localidad> BuscarTodas(int codProv) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Localidad> localidades = new ArrayList<Localidad>();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			statement.setInt(1, codProv);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				localidades.add(getLocalidad(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return localidades;
	}
	
	public int BuscarUltima() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Localidad localidad = new Localidad();
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
				localidad = getLocalidad(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer   el registro(SQL ERROR)");
		}
		return localidad.getCodLocalidad();
	}
	
	public Localidad BuscarUna(int codLocalidad) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Localidad localidad = new Localidad();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readOne);
			statement.setInt(1, codLocalidad);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				localidad = getLocalidad(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error al querer leer la localidad del registro(SQL ERROR)");
		}
		
		return localidad;
	}
	
	private Localidad getLocalidad(ResultSet resultSet) throws SQLException {

		int codLocalidad = resultSet.getInt("codLocalidad");
		String localidad = resultSet.getString("localidad");
		int codProvincia = resultSet.getInt("codProvincia");
		int codPais = resultSet.getInt("codPais");
		boolean estado = resultSet.getBoolean("estado");
		
		ProvinciaDao provinciaDao = new ProvinciaDaoImpl();
		Provincia provincia = provinciaDao.BuscarUna(codProvincia);		
		PaisDao paisDao = new PaisDaoImpl();
		Pais pais = paisDao.BuscarUno(codPais);

		return new Localidad(codLocalidad, provincia, pais, localidad, estado);
	}

}
