package servlets;

import java.io.IOException;



import entidad.Cliente;
import entidad.Prestamo;
import entidad.PrestamoxAutorizar;

import negocio.PrestamosxAutorizarNegocio;
import negocioImpl.PrestamosxAutorizarNegocioImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;*/
/**
 * Servlet implementation class ServletPrestamosxAutorizar
 */
@WebServlet("/ServletPrestamosxAutorizar")
public class ServletPrestamosxAutorizar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPrestamosxAutorizar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("btnRealizarSolicitudPrestamo")!=null) {
			btnRealizarSolicitudPrestamo(request, response);
		}
		if (request.getParameter("getPrestamos") != null || request.getParameter("pag") != null) {
			cargarPrestamos(request, response);
		}
		if (request.getParameter("btnAutorizar") != null) {
			updatePrestamo(request, response,2);
		}
		if (request.getParameter("btnRechazar") != null) {
			updatePrestamo(request, response,0);
		}
		
		
		
	}

	private void updatePrestamo(HttpServletRequest request, HttpServletResponse response, int i) throws ServletException, IOException {
		RequestDispatcher rd;
		PrestamosxAutorizarNegocio pdxaNeg = new PrestamosxAutorizarNegocioImpl();
		boolean solicitado = false;
		String resString="";
		PrestamoxAutorizar pxa = new PrestamoxAutorizar();
		
		try
		{
		pxa.setCodPrestamoPendiente(Integer.parseInt(request.getParameter("codPrestamo")));
		pxa.setEstado(i);
		solicitado = pdxaNeg.Update(pxa);

		if(solicitado)
			resString="Solicitud Procesada Satisfactoriamente";
		else
			resString="Solicitud no pudo ser agregada Satisfactoriamente";
		}
		catch(Exception e)
		{
			resString="Solicitud no pudo ser agregada Satisfactoriamente";
			request.setAttribute("codPrestamo",null);
		}
		ArrayList<PrestamoxAutorizar> lPrestamos = (ArrayList<PrestamoxAutorizar>)pdxaNeg.BuscarAcivos() ;
		
		
		
		
		//PAGINADO
		int cantTotal = (int) pdxaNeg.ContarPrestamos();  //Cantidad de registros activos en la BD

		int pag = 1;
		if(request.getParameter("pag") != null) {
			pag = Integer.parseInt(request.getParameter("pag"));	
		}
		
		int limit = 10;                      		
		int offset = 0;
		if(pag > 1) offset = limit * (pag - 1);	 	
		int cantPag = (cantTotal / limit) + 1 ; 	
		int resto = offset + limit;
		int index = 0;
		
		ListIterator<PrestamoxAutorizar> itLista = lPrestamos.listIterator();
		while (itLista.hasNext()) {
			PrestamoxAutorizar pres = itLista.next();
			index += 1;
	       
			if(index < offset + 1 || index > offset + limit ) {
				itLista.remove();
			}
		}

		request.setAttribute("pag", pag);
		request.setAttribute("cantPag", cantPag);
		
		
		
		request.setAttribute("codPrestamo", pxa.getCodPrestamoPendiente());
		request.setAttribute("resBoolean", solicitado);
		request.setAttribute("resString", resString);
		request.setAttribute("Prestamos", lPrestamos);
		rd = request.getRequestDispatcher("/AltaPrestamo.jsp");
		///rd = request.getRequestDispatcher("/AltaPrestamo.jsp?getPrestamos");
		rd.forward(request, response);
	}

	private void cargarPrestamos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd;
		boolean solicitado = false;
		String resString="";
		PrestamosxAutorizarNegocio pdxaNeg = new PrestamosxAutorizarNegocioImpl();
		ArrayList<PrestamoxAutorizar> lPrestamos = (ArrayList<PrestamoxAutorizar>)pdxaNeg.BuscarAcivos() ;
		if(lPrestamos != null)
		{
	
			if(!lPrestamos.isEmpty())
				solicitado =true;
			else
			resString="No hay Prestamos pendientes de aprobacion";
		}
		
		
		
		//PAGINADO
		int cantTotal = (int) pdxaNeg.ContarPrestamos();  //Cantidad de registros activos en la BD

		int pag = 1;
		if(request.getParameter("pag") != null) {
			pag = Integer.parseInt(request.getParameter("pag"));	
		}
		
		int limit = 10;                      //Elementos por página.		
		int offset = 0;
		if(pag > 1) offset = limit * (pag - 1);	 //inicio paginado   	
		int cantPag = (cantTotal / limit) + 1 ; // Cantidad de páginas.	
		int resto = offset + limit;
		int index = 0;
		
		ListIterator<PrestamoxAutorizar> itLista = lPrestamos.listIterator();
		while (itLista.hasNext()) {
			PrestamoxAutorizar pres = itLista.next();
			index += 1;
	       
			if(index < offset + 1 || index > offset + limit ) {
				itLista.remove();
			}
		}

		request.setAttribute("pag", pag);
		request.setAttribute("cantPag", cantPag);
		request.setAttribute("resString", resString);
		request.setAttribute("resBoolean", solicitado);
		request.setAttribute("Prestamos", lPrestamos);

	    rd = request.getRequestDispatcher("/AltaPrestamo.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
	public void btnRealizarSolicitudPrestamo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		RequestDispatcher rd;
		
		
		PrestamosxAutorizarNegocio pdxaNeg = new PrestamosxAutorizarNegocioImpl();
		boolean solicitado = false;
		String resString="";
		PrestamoxAutorizar pxa = new PrestamoxAutorizar();
		try
		{
		pxa.setNroCuenta(Integer.parseInt(request.getParameter("getCuenta")));
		pxa.setCantidad_cuotas(Integer.parseInt(request.getParameter("txtCuotas")));
		pxa.setImporte(new BigDecimal(request.getParameter("txtMonto")));
		
		
		solicitado = pdxaNeg.Insert(pxa);

		if(solicitado)
			resString="Solicitud procesada Satisfactoriamente";
		else
			resString="Solicitud no pudo ser agregada Satisfactoriamente";
		}
		catch(Exception e)
		{
			resString="Solicitud no pudo ser agregada Satisfactoriamente";
			request.setAttribute("cuentaSeleccionada",null);
		}
		request.setAttribute("cuentaSeleccionada", pxa.getNroCuenta());
		request.setAttribute("resBoolean", solicitado);
		request.setAttribute("resString", resString);
		rd = request.getRequestDispatcher("/solicitarPrestamo.jsp");
		rd.forward(request, response);
		
	}

}