package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import negocio.ClienteNegocio;
import negocioImpl.ClienteNegocioImpl;


/*
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;*/

/**
 * Servlet implementation class ServletListado
 */
@WebServlet("/ServletListado")
public class ServletListado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		
		if (request.getParameter("getId") != null || request.getParameter("btnFiltrar") != null || request.getParameter("btnLimpiar") != null ) {
			cargarClientes(request, response);

		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	private void cargarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 			        
		    		
		/*	ClienteNegocio cNeg = new ClienteNegocioImpl(); 
			int cant = (int) cNeg.countActive();
			
			int pag = Integer.valueOf(request.getParameter("pag"));
			
			//maxPag es la variable para Máximo de Páginas
		    //Elementos por página.			
			int maxPag = cant / 10;
		    //Operación para obtener el número de registro del que inicia.    
		    int regMin = (pag - 1) * 10;
		    //Operación para obtener el número de registros máximos para mostrar en esa página.
		    //Esto con el fin, de recorrer el arreglo desde el registro mínimo hasta el registro máximo.
		    int regMax = pag * 10;
		    
		    
			request.setAttribute("pag", pag);
			request.setAttribute("maxPag", maxPag);		
			request.setAttribute("regMin", regMin);		
			request.setAttribute("regMax", regMax);		
			
			RequestDispatcher rd = request.getRequestDispatcher("/se.jsp");
			
			cargarDesplegablesModif(request, response);
			
			rd.forward(request, response);*/
			
			
			
	}

}
