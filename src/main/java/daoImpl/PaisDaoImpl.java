package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.PaisDao;
import entidad.Pais;

public class PaisDaoImpl implements PaisDao {

	private static final String insert = "INSERT INTO Paises(pais) VALUES (?)";
	private static final String logicalDeletion = "UPDATE Paises set estado = 0 Where codPais = ?";
	private static final String readall = "SELECT * FROM Paises";
	private static final String update = "UPDATE Paises set pais = ? Where codPais = ?";
	private static final String readlast = "SELECT * FROM Paises ORDER by codPais DESC LIMIT 1";
	private static final String readOne = "SELECT * FROM Paises Where codPais = ?";
	
	public boolean Insert(Pais pais_a_agregar) {
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
			statement.setString(1,  pais_a_agregar.getPais());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {

			System.out.println("Error al intentar ingresar el registro");
		}

		return isInsertExitoso;
	}
	
	public boolean Update(Pais pais_a_actualizar) {

		System.out.println(pais_a_actualizar.toString());
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
			statement.setString(1, pais_a_actualizar.getPais());


			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return isUpdateExitoso;
	}

	public boolean EliminacionLogica(Pais pais_a_eliminar) {
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
			statement.setBoolean(1, pais_a_eliminar.isEstado());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isLogicalDeletionExitoso = true;
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer Borrar el registro(SQL ERROR)");
		}
		return isLogicalDeletionExitoso;
	}
	
	public List<Pais> BuscarTodos() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Pais> paises = new ArrayList<Pais>();
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
				paises.add(getPais(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return paises;
	}
	
	public int Buscarultimo() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Pais pais = new Pais();
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
				pais = getPais(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer   el registro(SQL ERROR)");
		}
		return pais.getCodPais();
	}
	
	public Pais BuscarUno(int codPais) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Pais pais = new Pais();
		Conexion conexion = Conexion.getConexion();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readOne);
			statement.setInt(1, codPais);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				pais = getPais(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer   el registro(SQL ERROR)");
		}
		
		return pais;
	}
	
	private Pais getPais(ResultSet resultSet) throws SQLException {

		int codPais = resultSet.getInt("codPais");
		String pais = resultSet.getString("pais");
		boolean estado = resultSet.getBoolean("estado");

		return new Pais(codPais, pais, estado);
	}

}
