package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import jakarta.servlet.http.HttpServletResponse;
*/


import entidad.Cuenta;
import excepciones.SaldoCuenta;
import negocio.CuentaNegocio;
import negocio.TipoCuentaNegocio;
import negocioImpl.CuentaNegocioImpl;
import negocioImpl.TipoCuentaImpl;

/**
 * Servlet implementation class ServletCliente
 */
@WebServlet("/ServletCuenta")
public class ServletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CuentaNegocio neg = new CuentaNegocioImpl();


    /**
     * Default constructor. 
     */
    public ServletCuenta() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("btnBuscar") != null) {
			cargarBusquedaDeCuentas(request, response,0);
		}
		if (request.getParameter("btnBuscarBaja") != null) {
			cargarBusquedaDeCuentas(request, response,1);
		}

		if (request.getParameter("btnAgregar") != null) {
			registrarCuenta(request, response);
		}
		if (request.getParameter("btnBaja") != null) {
			BajaCurrentCuenta(request, response);
		}	
		
		if (request.getParameter("btnSeleccionar")!=null) {
			setearCurrentCuenta(request,response);
		}
		if (request.getParameter("btnConsultaCbu")!=null) {
			consultarCbu(request,response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}


	private void cargarBusquedaDeCuentas(HttpServletRequest request, HttpServletResponse response, int i) throws ServletException, IOException {
	
		
		CuentaNegocio neg = new CuentaNegocioImpl();
		TipoCuentaNegocio negTC = new TipoCuentaImpl();
		////String dni = request.getParameter("txtdni");
		String resString = null;
		Boolean resBoolean = false;
		String dni = request.getParameter("txtdni").toString().trim();
		ArrayList<Cuenta> lCuenta = (ArrayList<Cuenta>) neg.BuscarClienteDni(dni);
		RequestDispatcher rd;
		if(neg.VerificarCliente(dni))
		{
			if(lCuenta == null && Integer.parseInt(dni) != 40192839)
			{
				resString="El cliente no tiene cuentas asociadas";
				resBoolean =false;
			}
			else
				if(Integer.parseInt(dni) == 40192839)
				{
					resString="El Administrador no puede tener cuentas asociadas";
					resBoolean =false;
				}
			if(lCuenta.isEmpty()&& Integer.parseInt(dni) != 40192839)
			{
				resString="El cliente no tiene cuentas asociadas";
				resBoolean=true;
			}
			else
				if(Integer.parseInt(dni) == 40192839)
				{
					resString="El Administrador no puede tener cuentas asociadas";
					resBoolean =false;
				}
				else
				{
					resString="go";
					resBoolean =true;			
				}
		}
		else
		{
			resString="No se encontraron clientes";
			resBoolean =false;
		}
			
		request.setAttribute("Cuentas", lCuenta);
		request.setAttribute("dni", dni);
		request.setAttribute("resString", resString);
		request.setAttribute("resBoolean", resBoolean);
		request.setAttribute("listaTC", negTC.BuscarTodos());
		
	switch(i)
	{
	case 0:
		 rd = request.getRequestDispatcher("/adminAltaCuenta.jsp");
		 break;
	case 1:
		 rd = request.getRequestDispatcher("/adminBajaCuenta.jsp");
		 break;
	default:
		 rd = request.getRequestDispatcher("/index.jsp");
		 break;
	}
		
		rd.forward(request, response);
	}

	private void registrarCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd;
		
		int tc = Integer.parseInt(request.getParameter("TC"));
		String dni = request.getParameter("dni");
		CuentaNegocio neg = new CuentaNegocioImpl();
		boolean agregado = false;
		String resString="";
		
		if(neg.VerificarCliente(dni))
		{
			if(!neg.VerificarMaxCuentas(dni))
			{
			 agregado = neg.Insert(dni,tc);

				if (agregado) {

					resString="Cuenta agregada Satisfactoriamente";
				}
				else
				{
					resString="Cuenta no pudo ser agregada satisfactoriamente";
				}
			}
			else
				resString="El cliente con DNI: "+dni+" tiene mas de 3 cuentas a su nombre";
		}
		else
			resString="No se encontraron clientes";
			
			request.setAttribute("resString", resString);
			request.setAttribute("resBoolean", agregado);
			rd = request.getRequestDispatcher("/adminAltaCuenta.jsp");
			rd.forward(request, response);
		}
			
	
	private void setearCurrentCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher rd;
	    CuentaNegocio cuenta = new CuentaNegocioImpl();
	    Cuenta cta = new Cuenta();
	    int nroCuenta = Integer.parseInt(request.getParameter("cuentaSeleccionada"));
	    cta = cuenta.BuscarUno(nroCuenta);
	    BigDecimal saldo = cta.getSaldo();
	    long cbu = cta.getCbu();
	    Map<String, Object> cuentaInfo = new HashMap<>();
	    cuentaInfo.put("cuentaSeleccionada", nroCuenta);
	    cuentaInfo.put("saldo", saldo);
	    cuentaInfo.put("cbu", cbu);
	    request.getSession().setAttribute("cuentaInfo", cuentaInfo);
	    
	    rd = request.getRequestDispatcher("/gestionarCuentas.jsp");
	    rd.forward(request, response);
	}
	
	private void consultarCbu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    RequestDispatcher rd;
	    CuentaNegocio cuenta = new CuentaNegocioImpl();
	    Cuenta cta = new Cuenta();
	    int nroCuenta = Integer.parseInt(request.getParameter("cuentaSeleccionada"));
	    cta = cuenta.BuscarUno(nroCuenta);
	    BigDecimal saldo = cta.getSaldo();
	    long cbu = cta.getCbu();
	    
	    Map<String, Object> cuentaInfo = new HashMap<>();
	    cuentaInfo.put("cuentaSeleccionada", nroCuenta);
	    cuentaInfo.put("saldo", saldo);
	    cuentaInfo.put("cbu", cbu);
	    
	    request.getSession().setAttribute("cuentaInfo", cuentaInfo);
	    
	    rd = request.getRequestDispatcher("/consultaCbu.jsp");
	    rd.forward(request, response);
	}
	
	private void validarSaldo( int nroCuenta ) throws SaldoCuenta {
		
		Cuenta cuenta = neg.BuscarUno(nroCuenta);	

		if ( cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0) {

			throw new SaldoCuenta();
		} 
	}

	private void BajaCurrentCuenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd;
		boolean borrado = false;
		String resString = "";
		
		try {
			int nroCuenta = Integer.parseInt(request.getParameter("cuenta"));
			validarSaldo(nroCuenta);
			
			borrado = neg.Delete(neg.BuscarUno(nroCuenta));
			if (borrado) 
				resString="Cuenta dada de baja Satisfactoriamente";
			else
				resString="Cuenta "+neg.BuscarUno(nroCuenta).getNroCuenta()+" no pudo ser dada de baja satisfactoriamente";
		} catch(SaldoCuenta ex) {
			resString = "La cuenta debe estar en cero para poder darse de baja";
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
				
		request.setAttribute("resString", resString);
		request.setAttribute("resBoolean", borrado);
		rd = request.getRequestDispatcher("/adminBajaCuenta.jsp");
		rd.forward(request, response);
	}

}
		
