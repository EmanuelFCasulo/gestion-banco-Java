package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReporteDao;
import daoImpl.ReporteDaoImpl;
import negocio.ReporteNegocio;
import negocioImpl.ReporteNegocioImpl;


/**
 * Servlet implementation class ServletReportes
 */
@WebServlet("/ServletReportes")
public class ServletReportes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportes() {
        super();
        // TODO Auto-generated constructor stub
    }

    private ReporteNegocio reporteNeg = new ReporteNegocioImpl();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("getReportes") != null) {
			cpl(request, response);
		}
		
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void cpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        Map<String, Integer> clientesPorLocalidad = reporteNeg.ClientesxLocalidad();

        request.setAttribute("clientesPorLocalidad", clientesPorLocalidad);
        
        Map<String, Integer> movimientosPorTipo = reporteNeg.MovimientosPorTipo();

        request.setAttribute("movimientosXTipo", movimientosPorTipo);
        
        Map<String, Integer> cuentasPorTipo = reporteNeg.CuentasPorTipo();

        request.setAttribute("cuentasXTipo", cuentasPorTipo);
        
        Map<String, BigDecimal> saldoProm = reporteNeg.SaldoPromXTipoCuenta();

        request.setAttribute("saldoPromedioxTipoCuenta", saldoProm);
        
        Map<String, Integer> prestamosPorCliente = reporteNeg.PrestamosXCliente();

        request.setAttribute("prestamosPorCliente", prestamosPorCliente);

        RequestDispatcher dispatcher = request.getRequestDispatcher("reportes-estadisticas.jsp");
        dispatcher.forward(request, response);
	}
	
	/*private void ctaXTipo (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Map<String, Integer> cuentasPorTipo = reporteNeg.CuentasPorTipo();

        request.setAttribute("cuentasXTipo", cuentasPorTipo);

        RequestDispatcher dispatcher = request.getRequestDispatcher("reportes-estadisticas.jsp");
        dispatcher.forward(request, response);
	}*/
	
}
