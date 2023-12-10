package servlets;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuota;
import entidad.Prestamo;
import entidad.Usuario;
import negocioImpl.PrestamosNegocioImpl;

/*
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import negocioImpl.PrestamosNegocioImpl;*/

/**
 * Servlet implementation class ServletPrestamos
 */
@WebServlet("/ServletPrestamos")
public class ServletPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletPrestamos() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("pagoPrestamos")!=null) {
			obtenerPrestamos(request, response);
	
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	public void obtenerPrestamos(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException  {
		Usuario usuario = new Usuario();
		int nroCuenta = Integer.parseInt(request.getParameter("pagoPrestamos"));
		ArrayList<Prestamo> prestamosList = new ArrayList<Prestamo>();
		ArrayList<Cuota> cuotaList = new ArrayList<Cuota>();
		usuario = (Usuario)request.getSession().getAttribute("Usuario");
		PrestamosNegocioImpl prestamos = new PrestamosNegocioImpl();
		prestamosList=(ArrayList<Prestamo>) prestamos.LeerDni(usuario.getDni());	
		ArrayList<Prestamo> prestamosActivos  = new ArrayList<Prestamo>();
		ListIterator<Prestamo> it = prestamosList.listIterator();
		
		BigDecimal csaldo = new BigDecimal(request.getParameter("getSaldo"));
		
		/*Levanto solo los prestamos activos*/
		while (it.hasNext()) {
			Prestamo p = it.next();
			if(p.getEstado()) {
				prestamosActivos.add(p);
				cuotaList.addAll((ArrayList<Cuota>)prestamos.ObtenerCuota(p.getCodPrestamo()));
			}
		}
		
		if (prestamosActivos != null && cuotaList != null) {
			request.setAttribute("Prestamos", prestamosActivos);
			request.setAttribute("NroCuenta", nroCuenta);	
			request.setAttribute("Cuotas", cuotaList);	
			request.setAttribute("Saldo", csaldo);
		}
		else {
			request.setAttribute("SinPrestamos", true);
		}
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/pagarPrestamo.jsp");
		rd.forward(request, response);
		
	}

}
