package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ClienteDao;
import dao.LocalidadDao;
import dao.PaisDao;
import dao.ProvinciaDao;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;

public class ClienteDaoImpl implements ClienteDao {
	private static final String insert = "INSERT INTO Clientes(dni,nombre,apellido,CUIL,sexo,nacionalidad,fecha_nac,direccion,codLocalidad, codProvincia, codPais, correo_electronico, telefonos) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String logicalDeletion = "UPDATE Clientes set estado = 0 Where dni = ?";
	private static final String readall = "SELECT * FROM Clientes ORDER by apellido, dni ASC";
	private static final String readOne = "SELECT * FROM Clientes Where dni LIKE ?";
	private static final String update = "UPDATE Clientes set nombre = ?, apellido = ?, CUIL = ?, sexo = ?, nacionalidad = ?, fecha_nac = ?, direccion = ?, codLocalidad = ?, codProvincia = ?, codPais = ?, correo_electronico = ?, telefonos = ?  Where dni = ?";
	private static final String readlast = "SELECT * FROM Clientes ORDER by dni DESC LIMIT 1";
	private static final String countAll = "SELECT COUNT(dni) as total FROM Clientes where estado = 1 ORDER by apellido, dni ASC";
	
	
	public int ClientesActivos() { 
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
			statement = conexion.getSQLConexion().prepareStatement(countAll);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cant = resultSet.getInt("total");
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer leer la cantidad de clientes activos (SQL ERROR)");
		}
		System.out.print(cant);
		return cant;
	}
	
	public boolean Insert(Cliente cliente_a_agregar) {
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
			statement.setString(1, cliente_a_agregar.getDni());
			statement.setString(2, cliente_a_agregar.getNombre());
			statement.setString(3, cliente_a_agregar.getApellido());
			statement.setString(4, cliente_a_agregar.getCuil());
			statement.setString(5, cliente_a_agregar.getSexo());
			statement.setInt(6, cliente_a_agregar.getNacionalidad().getCodPais());
			statement.setDate(7, cliente_a_agregar.getFecha_nac());
			statement.setString(8, cliente_a_agregar.getDireccion());
			statement.setInt(9, cliente_a_agregar.getLocalidad().getCodLocalidad());
			statement.setInt(10, cliente_a_agregar.getProvincia().getCodProvincia()); //prueba mati
			statement.setInt(11, cliente_a_agregar.getLocalidad().getPais().getCodPais());
			statement.setString(12, cliente_a_agregar.getCorreo_electronico());
			statement.setString(13, cliente_a_agregar.getTelefonos());


			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
			else {
				conexion.rollback();
			}
		} catch (SQLException e) {

			System.out.println("Error al intentar ingresar el registro");
		}

		return isInsertExitoso;
	}
	
	public boolean Update(Cliente cliente_a_actualizar) {

		System.out.println(cliente_a_actualizar.toString());
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
			statement.setString(1, cliente_a_actualizar.getNombre());
			statement.setString(2, cliente_a_actualizar.getApellido());
			statement.setString(3, cliente_a_actualizar.getCuil());
			statement.setString(4, cliente_a_actualizar.getSexo());
			statement.setInt(5, cliente_a_actualizar.getNacionalidad().getCodPais());
			statement.setDate(6, cliente_a_actualizar.getFecha_nac());
			statement.setString(7, cliente_a_actualizar.getDireccion());
			statement.setInt(8, cliente_a_actualizar.getLocalidad().getCodLocalidad());
			statement.setInt(9, cliente_a_actualizar.getProvincia().getCodProvincia());//VER
			statement.setInt(10, cliente_a_actualizar.getLocalidad().getPais().getCodPais());
			statement.setString(11, cliente_a_actualizar.getCorreo_electronico());
			statement.setString(12, cliente_a_actualizar.getTelefonos());
			statement.setString(13, cliente_a_actualizar.getDni());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return isUpdateExitoso;
	}
	
	public boolean EliminacionLogica(Cliente cliente_a_eliminar) {
		System.out.println(cliente_a_eliminar.toString());
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
			statement.setString(1, cliente_a_eliminar.getDni());
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isLogicalDeletionExitoso = true;
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer dar la baja lógica del registro(SQL ERROR)");
		}
		return isLogicalDeletionExitoso;
	}
	
	
	public List<Cliente> BuscarTodos() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
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
				clientes.add(getCliente(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros(SQL ERROR)");
		}
		return clientes;
	}
	
	public Cliente BuscarUno(String dni) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Cliente cliente = new Cliente();
		Conexion conexion = Conexion.getConexion();
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readOne);
			statement.setString(1, "%" + dni + "%");
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				cliente = getCliente(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer leer  el registro(SQL ERROR)");
		}
		
		return cliente;
	}
	
	public String BuscarUltimo() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		Cliente cliente = new Cliente();
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
				cliente = getCliente(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer leer el registro(SQL ERROR)");
		}
		return cliente.getDni();
	}
	
	private Cliente getCliente(ResultSet resultSet) throws SQLException {

		String dni = resultSet.getString("dni");
		String nombre = resultSet.getString("nombre");
		String apellido = resultSet.getString("apellido");
		String cuil = resultSet.getString("CUIL");
		String sexo = resultSet.getString("sexo");
		int codNacionalidad = resultSet.getInt("nacionalidad");
		Date fecha_nac = resultSet.getDate("fecha_nac");
		String direccion = resultSet.getString("direccion");
		int codLocalidad = resultSet.getInt("codLocalidad");
		int codProvincia = resultSet.getInt("codProvincia");
		int codPais = resultSet.getInt("codPais");
		String correo_electronico = resultSet.getString("correo_electronico");
		String telefonos = resultSet.getString("telefonos");
		boolean estado = resultSet.getBoolean("estado");
		
		LocalidadDao localidadDao = new LocalidadDaoImpl();
		Localidad localidad = localidadDao.BuscarUna(codLocalidad);		
		ProvinciaDao provinciaDao = new ProvinciaDaoImpl();
		Provincia provincia = provinciaDao.BuscarUna(codProvincia);		
		PaisDao paisDao = new PaisDaoImpl();
		Pais pais = paisDao.BuscarUno(codPais);
		Pais nacionalidad = paisDao.BuscarUno(codNacionalidad);
		
		
		return new Cliente(dni, nombre, apellido, cuil, sexo, nacionalidad, fecha_nac, direccion, localidad, provincia, pais, correo_electronico, telefonos, estado);
	}


}
