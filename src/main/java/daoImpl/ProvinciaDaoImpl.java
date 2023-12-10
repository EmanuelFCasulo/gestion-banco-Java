package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PaisDao;
import dao.ProvinciaDao;
import entidad.Pais;
import entidad.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao{

	private static final String insert = "INSERT INTO Provincias (provincia) VALUES (?)";
	private static final String logicalDeletion = "UPDATE Provincias set estado = 0 Where codProvincia = ?";
	private static final String readall = "SELECT * FROM Provincias";
	private static final String update = "UPDATE Provincias set provincia = ? Where codProvincia = ?";
	private static final String readlast = "SELECT * FROM Provincias ORDER by codProvincia DESC LIMIT 1";
	private static final String readOne = "SELECT * FROM Provincias Where codProvincia = ?";
	
	public boolean Insert(Provincia provincia_a_agregar) {
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
			statement.setString(1,  provincia_a_agregar.getProvincia());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {

			System.out.println("Error al intentar ingresar el registro");
		}

		return isInsertExitoso;
	}
	
	public boolean Update(Provincia provincia_a_actualizar) {

		System.out.println(provincia_a_actualizar.toString());
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
			statement.setString(1, provincia_a_actualizar.getProvincia());


			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return isUpdateExitoso;
	}

	public boolean EliminacionLogica(Provincia provincia_a_eliminar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isLogicalDeletionExitoso = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.prepareStatement(update);
			statement.setBoolean(1, provincia_a_eliminar.isEstado());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isLogicalDeletionExitoso = true;
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer Borrar el registro(SQL ERROR)");
		}
		return isLogicalDeletionExitoso;
	}
	
	public List<Provincia> BuscarTodas() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Provincia> provincias = new ArrayList<Provincia>();
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
				provincias.add(getProvincia(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return provincias;
	}
	
	public int Buscarultima() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Provincia provincia = new Provincia();
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
				provincia = getProvincia(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer   el registro(SQL ERROR)");
		}
		return provincia.getCodProvincia();
	}

	public Provincia BuscarUna(int codProvincia) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Provincia provincia = new Provincia();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readOne);
			statement.setInt(1, codProvincia);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				provincia = getProvincia(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer   el registro(SQL ERROR)");
		}
		
		return provincia;
	}
	
	private Provincia getProvincia(ResultSet resultSet) throws SQLException {

		int codProvincia = resultSet.getInt("codProvincia");
		int codPais = resultSet.getInt("codPais");
		String provincia = resultSet.getString("provincia");
		boolean estado = resultSet.getBoolean("estado");
		
		PaisDao paisDao = new PaisDaoImpl();
		Pais pais = paisDao.BuscarUno(codPais);

		return new Provincia(codProvincia, pais, provincia, estado);
	}

}