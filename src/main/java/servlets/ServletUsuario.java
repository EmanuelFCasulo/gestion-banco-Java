package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Usuario;
import entidad.Cuenta;
import entidad.Localidad;
import entidad.Pais;
import entidad.TipoUsuario;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;
import negocioImpl.UsuarioNegocioImpl;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.LocalidadNegocioImpl;
import negocioImpl.PaisNegocioImpl;
import negocioImpl.TipoUsuarioNegocioImpl;
import negocio.CuentaNegocio;
import negocio.LocalidadNegocio;
import negocio.PaisNegocio;
import negocio.TipoUsuarioNegocio;
import negocio.UsuarioNegocio;

/*
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
*/
/*
 * Servlet implementation class ServletUsuario
 */
@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public ServletUsuario() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("dni") != null && request.getParameter("btnAltaUsuario") != null) {
			String dni = request.getParameter("dni");
			cargarDesplegablesAlta(dni, request, response);
		}

		if (request.getParameter("btnAltaUs") != null) {
			registrarUsuario(request, response);
		}

		if (request.getParameter("btnModifUsuario") != null) {
			String dni = request.getParameter("dni");
			cargarUsuarioParaModif(dni, request, response);
		}

		if (request.getParameter("btnModifUs") != null) {
			modificarUsuario(request, response);
		}

		if (request.getParameter("btnIndex") != null) {
			boolean pedirDni = true;
			request.setAttribute("pedirDni", pedirDni);
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("btnIniciarSesion") != null) {
			Usuario usuario = new Usuario();
			RequestDispatcher rd;
			if (iniciarSesion(request, response, usuario)) {
				/* Segun el tipo de usuario redirecciona al panel de cliente o de admin. */
				/*
				 * if(usuario.getDni().equals("0")) { rd =
				 * request.getRequestDispatcher("/index.jsp"); }
				 */
				if (usuario.getTipoUsuario().getCodTipo() == 2)
					rd = request.getRequestDispatcher("/inicioClientes.jsp");
				else
					rd = request.getRequestDispatcher("/inicioAdmin.jsp");
				rd.forward(request, response);
			} else {
				rd = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("SesionFallida", true);
				rd.forward(request, response);
			}
		}

		if (request.getParameter("btnSiguiente") != null) {
			validarDNI(request, response);
		}

	}

	private void validarDNI(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		boolean isCliente = false;
		String dni = request.getParameter("txtDNI");

		ClienteNegocio clienteNeg = new ClienteNegocioImpl();
		Cliente cl = clienteNeg.BuscarUno(dni);
		System.out.println(cl);

		if (cl != null && cl.isEstado() == true) {
			request.setAttribute("isCliente", true);
			cargarUsuarioParaModif(dni, request, response);

		} else {
			request.setAttribute("isCliente", isCliente);
			rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}

	}

	/*
	 * InicarSesion(request, response, usuario) Entrada: request, response,
	 * entidad.usuario Salida: true o false en el nombre de la funcion.
	 * Funcionalidad: Levanta los datos ingresados en el form Index.jsp y intenta
	 * iniciar sesion, en caso de exito genera una session con los datos del
	 * usuario, en caso de error o usuario inexistente devuelve false
	 */
	public Boolean iniciarSesion(HttpServletRequest request, HttpServletResponse response, Usuario usuario)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		String clave = request.getParameter("txtClave");
		String usu = request.getParameter("txtUsuario");
		String DNI = request.getParameter("txtDNI");
		usuario.setDNI(DNI);
		usuario.setUsuario(usu);
		usuario.setClave(clave);
		UsuarioNegocioImpl usuNeg = new UsuarioNegocioImpl();

		CuentaNegocio ctaNeg = new CuentaNegocioImpl();
		List<Cuenta> lCta = ctaNeg.BuscarClienteDni(DNI);

		if (usuNeg.IniciarSesion(usuario)) {
			request.getSession().setAttribute("Usuario", usuario);
			request.getSession().setAttribute("cuentasDDL", lCta);
			cuentasUsuario(request, usuario);
			return true;
		} else
			return false;

	}

	private void cuentasUsuario(HttpServletRequest request, Usuario usuario) {
		CuentaNegocio cuenta = new CuentaNegocioImpl();
		ArrayList<Cuenta> cta = (ArrayList<Cuenta>) cuenta.BuscarClienteDni(usuario.getDni());
		int nroCuenta;
		if (cta.size() != 0 || cta.isEmpty() == false) {
			nroCuenta = cta.get(0).getNroCuenta();
		} else {
			nroCuenta = 0;
		}

		request.getSession().setAttribute("cuentaSeleccionada", nroCuenta);
		request.getSession().setAttribute("cuentas", cta);

	}

	private void cargarDesplegablesAlta(String dni, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TipoUsuarioNegocio usNeg = new TipoUsuarioNegocioImpl();
		ArrayList<TipoUsuario> lTiposUs = (ArrayList<TipoUsuario>) usNeg.BuscarTodos();
		request.setAttribute("tiposUsuarios", lTiposUs);
		request.setAttribute("dni", dni);

		RequestDispatcher rd = request.getRequestDispatcher("/altaUsuario.jsp");
		rd.forward(request, response);

	}

	private void cargarDesplegablesModif(String dni, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TipoUsuarioNegocio usNeg = new TipoUsuarioNegocioImpl();
		ArrayList<TipoUsuario> lTiposUs = (ArrayList<TipoUsuario>) usNeg.BuscarTodos();
		request.setAttribute("tiposUsuarios", lTiposUs);
		request.setAttribute("dni", dni);

		RequestDispatcher rd = request.getRequestDispatcher("/modifUsuarioForm.jsp");
		rd.forward(request, response);

	}

	private void cargarUsuarioParaModif(String dni, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UsuarioNegocio usNeg = new UsuarioNegocioImpl();
		Usuario us = usNeg.BuscarUno(dni);
		String usuario = us.getUsuario();
		request.setAttribute("usuario", us);
		System.out.println(dni);
		System.out.println(us);

		if (usuario != null) {
			cargarDesplegablesModif(dni, request, response);
		} else {
			cargarDesplegablesAlta(dni, request, response);
		}

	}

	private void registrarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		String mensaje = null;
		boolean agregado = false;

		String contrasenia = request.getParameter("txtContrasenia");
		String contrasenia2 = request.getParameter("txtContrasenia2");
		int tipoUsuario = Integer.parseInt(request.getParameter("txtTipo"));
		String usuario = request.getParameter("txtUsuario");
		String dni = request.getParameter("txtDNI");

		Cliente cl = new Cliente();
		TipoUsuario tipo = new TipoUsuario();
		cl.setDni(dni);
		tipo.setCodTipo(tipoUsuario);

		Usuario us = new Usuario(usuario, cl, tipo, contrasenia, true);

		if (contrasenia.equals(contrasenia2)) {
			try {
				UsuarioNegocio usNeg = new UsuarioNegocioImpl();
				agregado = usNeg.Insert(us);
				if (agregado) {
					System.out.println(us);
					request.setAttribute("agregado", agregado);
					mensaje = "Usuario registrado con éxito";
					request.setAttribute("usuario", us);
					request.setAttribute("mensaje", mensaje);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			mensaje = "Las Contraseñas no coinciden";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("agregado", agregado);
			request.setAttribute("usuario", us);
			cargarDesplegablesAlta(dni, request, response);
		}

		rd = request.getRequestDispatcher("altaUsuario.jsp");
		rd.forward(request, response);

	}

	private void modificarUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd;
		String mensaje = null;
		boolean modificado = false;

		String contrasenia = request.getParameter("txtContrasenia");
		String contrasenia2 = request.getParameter("txtContrasenia2");
		int tipoUsuario = Integer.parseInt(request.getParameter("txtTipo"));
		String usuario = request.getParameter("txtUsuario");
		String dni = request.getParameter("txtDNI");

		Cliente cl = new Cliente();
		TipoUsuario tipo = new TipoUsuario();
		cl.setDni(dni);
		tipo.setCodTipo(tipoUsuario);

		Usuario us = new Usuario(usuario, cl, tipo, contrasenia, true);

		if (contrasenia.equals(contrasenia2)) {
			try {
				UsuarioNegocio usNeg = new UsuarioNegocioImpl();
				modificado = usNeg.Update(us);
				if (modificado) {
					System.out.println(us);
					request.setAttribute("modificado", modificado);
					mensaje = "Usuario modificado con Éxito";
					request.setAttribute("usuario", us);
					request.setAttribute("mensaje", mensaje);

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			mensaje = "Las Contraseñas no coinciden";
			request.setAttribute("mensaje", mensaje);
			request.setAttribute("modificado", modificado);
			request.setAttribute("usuario", us);
			cargarDesplegablesModif(dni, request, response);
		}

		rd = request.getRequestDispatcher("modifUsuarioForm.jsp");
		rd.forward(request, response);

	}

}
