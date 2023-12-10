package daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;

import dao.ClienteDao;
import dao.LocalidadDao;
import dao.PaisDao;
import dao.ProvinciaDao;
import dao.TipoUsuarioDao;
import dao.UsuarioDao;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;
import entidad.TipoUsuario;
import entidad.Usuario;

public class UsuarioDaoImpl implements UsuarioDao {
	
	private static final String QueryIniciarSesion = "select  c.nombre, c.apellido, c.cuil, c.sexo, c.nacionalidad, c.fecha_nac, c.direccion, c.codLocalidad, c.codProvincia, c.codPais, c.correo_electronico, c.telefonos, c.estado, tu.tipoUsuario, tu.codTipo from BD_Banco_grupo_2.usuarios u  inner join BD_Banco_grupo_2.clientes c on c.dni = u.dni  inner join BD_Banco_grupo_2.tiposUsuarios tu on tu.CodTipo = u.tipoUsuario where u.usuario = ? and u.DNI = ? and	u.clave = ? and	u.estado = 1 and c.estado = 1 and tu.estado = 1";
	private static final String insert = "INSERT INTO Usuarios (usuario, dni, tipoUsuario, clave) VALUES (?,?,?,?)";
	private static final String logicalDeletion = "UPDATE Usuarios set estado = 0 Where dni = ?";
	private static final String readall = "SELECT * FROM Usuarios";
	private static final String update = "UPDATE Usuarios set tipoUsuario = ?, clave = ? Where usuario = ? and dni = ?";
	private static final String readOne = "SELECT * FROM Usuarios Where dni = ?"; 


	public Boolean IniciarSesion(Usuario usuario) {
		
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {    
			statement = conexion.getSQLConexion().prepareStatement(QueryIniciarSesion);
			statement.setString(1,  usuario.getUsuario());
			statement.setString(2, usuario.getDni());
			statement.setString(3, usuario.getClave());
			resultSet = statement.executeQuery();
			
			/*Vuelvo un registro atras*/
			//resultSet.beforeFirst();
			
			/*Si tengo un resultado el usuario existe*/
			if (resultSet.next()) {
				cargarUsuario(resultSet, usuario);
				return true;
			}
			else 
				return false;
				
		} catch (SQLException e) {
			System.out.print("Error al iniciar sesion(SQL ERROR)");
			return false;
		}

	}
	
	public boolean Insert(Usuario usuario_a_agregar) {
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
			statement.setString(1, usuario_a_agregar.getUsuario());
			statement.setString(2, usuario_a_agregar.getDni());
			statement.setInt(3, usuario_a_agregar.getTipoUsuario().getCodTipo());
			statement.setString(4, usuario_a_agregar.getClave());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
			else {
				conexion.rollback();
			}
		} catch (SQLException e) {

			System.out.println("Error al intentar ingresar el registro de usuario");
		}

		return isInsertExitoso;
	}

	public boolean Update(Usuario usuario_a_modificar) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isUpdateExitoso = false;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();   //setInt setDouble
		}

		try {
			statement = conexion.prepareStatement(update);
			statement.setInt(1, usuario_a_modificar.getTipoUsuario().getCodTipo());
			statement.setString(2, usuario_a_modificar.getClave());
			statement.setString(3, usuario_a_modificar.getUsuario());
			statement.setString(4, usuario_a_modificar.getDni());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isUpdateExitoso = true;
			}
		} catch (SQLException e) {

			System.out.println("Error al intentar modificar el registro de usuario");
		}

		return isUpdateExitoso;
	}
	
	public boolean EliminacionLogica(String dni) {
		System.out.println(dni.toString());
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
			statement.setString(1, dni);
			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isLogicalDeletionExitoso = true;
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer dar la baja lógica del usuario(SQL ERROR)");
		}
		return isLogicalDeletionExitoso;
	}
	
	public List<Usuario> BuscarTodos() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<Usuario> usuariosList = new ArrayList<Usuario>();
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
				usuariosList.add(getUsuario(resultSet));
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer obtener todos los registros de Usuarios(SQL ERROR)");
		}
		return usuariosList;
	}
	
	public Usuario BuscarUno(String dni) {
		PreparedStatement statement;
		ResultSet resultSet; 
		Usuario usuario = new Usuario();
		Conexion conexion = Conexion.getConexion();
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			statement = conexion.getSQLConexion().prepareStatement(readOne);
			statement.setString(1, dni);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				usuario = getUsuario(resultSet);
			}
		} catch (SQLException e) {
			System.out.print("Error al Querer leer el registro de Usuario(SQL ERROR)");
		}
		
		return usuario;
	}
	
	public void cargarUsuario(ResultSet rs, Usuario usuario) throws SQLException {
		
		String 	Nombre = rs.getString("nombre");
		String 	Apellido = rs.getString("apellido");
		String 	Cuil = rs.getString("cuil");
		String 	Sexo = rs.getString("sexo");
		int codNacionalidad = rs.getInt("nacionalidad");
		Date 	fecha_nac = rs.getDate("fecha_nac");
		String direccion = rs.getString("direccion");
		int codLocalidad = rs.getInt("codLocalidad");
		int codProvincia = rs.getInt("codProvincia");
		int codPais = rs.getInt("codPais");
		String 	correo_electronico = rs.getString("correo_electronico");
		String 	tipoUsuario = rs.getString("tipoUsuario");
		int 	CodTipo = rs.getInt("codTipo");
		
		LocalidadDao localidadDao = new LocalidadDaoImpl();
		Localidad localidad = localidadDao.BuscarUna(codLocalidad);		
		ProvinciaDao provinciaDao = new ProvinciaDaoImpl();
		Provincia provincia = provinciaDao.BuscarUna(codProvincia);		
		PaisDao paisDao = new PaisDaoImpl();
		Pais pais = paisDao.BuscarUno(codPais);
		Pais nacionalidad = paisDao.BuscarUno(codNacionalidad);
		

		usuario.getcliente().setNombre(Nombre);
		usuario.getcliente().setApellido(Apellido);
		usuario.getcliente().setCuil(Cuil);
		usuario.getcliente().setSexo(Sexo);
		usuario.getcliente().setFecha_nac(fecha_nac);
		usuario.getcliente().setNacionalidad(nacionalidad);
		usuario.getcliente().setDireccion(direccion);
		usuario.getcliente().setLocalidad(localidad);
		usuario.getcliente().setProvincia(provincia);
		usuario.getcliente().setPais(pais);
		usuario.getcliente().setCorreo_electronico(correo_electronico);
		/*Si llegue aca es porque el estado es true*/
		usuario.setEstado(true);
		
		TipoUsuario Tusuario = new TipoUsuario(CodTipo, tipoUsuario, true);
		usuario.setTipoUsuario(Tusuario);
		
	}
	
	private Usuario getUsuario(ResultSet resultSet) throws SQLException {

		String usuario = resultSet.getString("usuario");
		String dni = resultSet.getString("dni");
		int tipo = resultSet.getInt("tipoUsuario");
		String clave = resultSet.getString("clave");
		boolean estado = resultSet.getBoolean("estado");
		
		ClienteDao clDao = new ClienteDaoImpl();
		Cliente cliente = clDao.BuscarUno(dni);
		TipoUsuarioDao tuDao = new TipoUsuarioDaoImpl();
		TipoUsuario tipoUs = tuDao.BuscarUno(tipo);
				
		return new Usuario(usuario, cliente, tipoUs,clave, estado);
	}
	
	
}
