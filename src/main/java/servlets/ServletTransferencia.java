package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuenta;
import entidad.TipoMovimiento;




/*
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;*/

import negocioImpl.CuentaNegocioImpl;
import negocioImpl.TransferenciaNegocioImpl;


/**
 * Servlet implementation class ServletTransferencia
 */
@WebServlet("/ServletTransferencia")
public class ServletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		if (request.getParameter("btnRealizarTransferencia")!=null) {
			realizarTransferencia(request,response);	
			RequestDispatcher rd = request.getRequestDispatcher("/gestionarCuentas.jsp");
			rd.forward(request, response);
		}
		
		
	}
	
	public void realizarTransferencia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Verifico que exista el CBU de destino*/
		String cbuDestino = request.getParameter("txtCbu");
		/*Levanto los datos*/
		String ctaOrigen = request.getParameter("txtCtaOrigenHidden");
		String DNI = request.getParameter("txtDNI");
		String importe = request.getParameter("txtMonto");
		String detalle = request.getParameter("txtDetalle");
		BigDecimal saldoOrigen;
		BigDecimal SaldoDestino;
        

		BigDecimal importeBig = new BigDecimal(importe);
		/*Centa origen*/
		TipoMovimiento Tmov= new TipoMovimiento(4,"Transferencia",true);
		
		TransferenciaNegocioImpl Transferencia = new TransferenciaNegocioImpl();
		if (Transferencia.CrearTrans(ctaOrigen, cbuDestino, importeBig, Tmov, detalle)) {
			request.setAttribute("Transferencia", true);
		
			CuentaNegocioImpl cuentaNegocio = new CuentaNegocioImpl();
		    List<Cuenta> cuentasActualizadas = cuentaNegocio.BuscarClienteNroCta(Integer.parseInt(ctaOrigen));
		    request.getSession().setAttribute("cuentas", cuentasActualizadas);
		}
		else 
			request.setAttribute("Transferencia", false);
		return;
	}

}
