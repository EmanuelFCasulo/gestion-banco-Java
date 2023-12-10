<%@page import="entidad.Usuario" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Prestamo" %>
<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Cuota" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.math.BigDecimal"%>
<%-- <%@page import="jakarta.servlet.RequestDispatcher" %> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pagar prestamo</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style type="text/css">
<jsp:include page="css/style.css"></jsp:include>
</style>
</head>
<body>
<%
		
		Usuario usuario = new Usuario();
	 	if(session.getAttribute("Usuario")!=null){	
	 		usuario = (Usuario)session.getAttribute("Usuario");
	 	}
	 	%>


	<header class="header bg-info p-3" > 
		<div>
			<a href="#">
				<img style = "float: left; margin: 2px 20px 10px 0; ; " src="img/pngwing.com.png"  alt="logo" width="50" height="50"  />
			</a>
		</div>
		<div class="logged ">
			<span class="mx-2 fw-bold">
			<svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 448 512">
			<path d="M224 256A128 128 0 1 0 224 0a128 128 0 1 0 0 256zm-45.7 48C79.8 304 0 383.8 0 482.3C0 498.7 13.3 512 29.7 512H418.3c16.4 0 29.7-13.3 29.7-29.7C448 383.8 368.2 304 269.7 304H178.3z"/>
			</svg> <%=usuario.getUsuario()%></span>
			
			<a class="btn btn-link" href="gestionarCuentas.jsp"> 
			<svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
			<path d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"/>
			</svg>  <span style="color: black; font-weight: bold;">Gestion</span></a>
		
		</div>
	</header>



<h1 class="mt-5" style="margin:auto;text-align:center;margin-bottom:30px;">Pagar prestamos</h1>
		<%
	 	
		ArrayList<Prestamo> prestamoList = null;
		ArrayList<Cuenta> cuentasList = null;
		ArrayList<Cuota> cuotasList = null;
		int pos= 0;
		int nroCuenta = 0;
		BigDecimal currentSaldo = null;
		/*Verifico si estoy recibiendo todos los parametros que necesito*/
		if (request.getAttribute("Prestamos")!=null &&request.getAttribute("Cuotas")!=null && request.getSession().getAttribute("cuentasDDL") != null && request.getAttribute("NroCuenta")!=null){
			prestamoList = (ArrayList<Prestamo>) request.getAttribute("Prestamos");
			cuotasList = (ArrayList<Cuota>) request.getAttribute("Cuotas");
			nroCuenta = (int) request.getAttribute("NroCuenta");
			cuentasList = (ArrayList<Cuenta>) request.getSession().getAttribute("cuentasDDL");
			currentSaldo = (BigDecimal) request.getAttribute("Saldo");
			for(int i=0;i<cuentasList.size();i++) { 
	 			if(cuentasList.get(i).isEstado()){
	 				if (cuentasList.get(i).getNroCuenta()==nroCuenta){
	 					pos=i;
	 					break;
	 				}
	 			}
			}
			
		}
		else{
			%><script>alert("Ocurrio un error durante la carga de la pagina. Si el problema persiste contacte a soporte tecnico.")</script><%
			response.sendRedirect("/index.jsp");
		}
		//cuentasList.get(pos)
		%>
		
		<section class="eleccion-prestamos">
		<label>SELECCIONAR PRESTAMO</label>
		<select class="select" id="select-prestamo">
			<option value="-1">Seleccione un prestamo</option>
		<% for(Prestamo p : prestamoList){%>
			<option value="<%=p.getCodPrestamo()%>">Codigo: <%=p.getCodPrestamo()%> - Monto: $<%=p.getImporte_a_pagar()%></option>
		
		<%}%> 
		</select>
		<button id="btnElegirPrestamo" class="btnSeleccionar" onclick="getCuotasPretamo()">Elegir prestamo</button>
	</section>
	
	<section class="Cuenta" style="margin-bottom:30px;">
		<div class="Cuenta-Tipo ">
		<%  if(cuentasList.get(pos).getTipoCuenta().getCodTipo()== 1) {	%>
			<label id="lblCuentaTipo">CA$</label>
		<%	} else { %>
				<label id="lblCuentaTipo">CC$</label>
		<%	} %>
		</div>
		<div class="Cuenta-Detalle">
			<label id="">$<%= String.format("%.2f",currentSaldo)%></label>
			<label id="lblDetalleCuenta"><%=cuentasList.get(pos).getTipoCuenta().getTipoCuenta()%> - Cuenta Nro: <%=cuentasList.get(pos).getNroCuenta()%></label>
		</div>
	</section>
	<!-- -->
	<!--<%=cuentasList.get(pos).getNroCuenta()%>-->
	<section class="detalle-cuota mb-5 ">
	<table class="table text-dark shadow-lg p-3 mb-5 bg-body-tertiary rounded" id="tabla-cuotas" style="display:none; text-align:center; width:80%; margin:auto;">
		<tr class="" style="text-align:center;">
			<th>Cuota</th>
			<th>Importe</th>
			<th>Fecha de pago</th>
			<th>Fecha vencimiento</th>
			<th>Estado</th>
			<th>Pagar</th>
		</tr>
		<% for(Cuota c : cuotasList){%>
		<tr class="cuotasTr cuoPrestamo-<%=c.getCodPrestamo()%>" style="display:none">
			<td><%=c.getNroCuota()%></td>
			<td>$<%= String.format("%.2f", c.getImporte())%></td>
			<td><% if (c.getFecha_pago()==null ){%>
				<%="-"%>
			<%} 
			else{%>
				<%=c.getFecha_pago()%>
			<%}%></td>
			<td><%=c.getFecha_venc() %></td>
			<td><% if (c.getEstado()){%>
				<%="Pendiente de pago"%>
			<%} 
			else{%>
				<%="Pago"%>
			<%}%></td>
			<td><button class="btn btn-outline-success fw-bold" <%if(!c.getEstado()){%>
							disabled				
						<%}%> 
				onclick="cuotaSeleccionada(<%=c.getIdCuota()%>,<%=c.getImporte()%>,<%=c.getEstado()%>);" class="">Pagar</button></td>
		</tr>
		<%}%> 
	</table>
	</section>
	<form method="post" action ="ServletCuota" hidden>
		<input type="text" hidden id="IdCuotaAPagar" name="IdCuotaAPagar">
		<input type="text" hidden id="NroCuenta" name="NroCuenta" value="<%=cuentasList.get(pos).getNroCuenta()%>">
		<input type="text"  hidden id="impCuot" name="impCuota">
		<input type="text" name="OPPAGARCUOTA">
	</form>
	<script>
	
	function cuotaSeleccionada(idCuota, importe, estado){
		requestID = document.getElementById("IdCuotaAPagar");
		requestImporte = document.getElementById("impCuot");
		requestID.value= idCuota;
		requestImporte.value = importe;
		if(!estado)
			alert("La cuota esta paga")
		
		else if (importe><%=cuentasList.get(pos).getSaldo()%>)
			alert("No tiene saldo suficiente para realizar el pago.");
		
		else if(confirm("Presione aceptar para confirmar el pago de la cuota.."))
			document.forms[0].submit();
		
		
		
		else
			alert("Pago cancelado")
		
		}
	
	var tabla = document.getElementById("tabla-cuotas");
	var prestamoSeleccionado = document.getElementById("select-prestamo");
	
		
	function getCuotasPretamo(){
		/*Oculto Todas Las Cuotas*/
		var btnElegirPrestamo = document.getElementById("btnElegirPrestamo");
		var CodPrestamo = prestamoSeleccionado.options[prestamoSeleccionado.selectedIndex].value;
		
		var allCuo = document.querySelectorAll('.cuotasTr');
		OcultarCuotas(allCuo);
		
		/*Si se eligio un prestamo muestro las cuotas de ese prestamo*/
		if (CodPrestamo!=-1){
			var cuotasPrestamoSeleccionado = document.querySelectorAll(".cuoPrestamo-"+CodPrestamo);
			MostrarCuotas(cuotasPrestamoSeleccionado);
			MostrarTabla();
		}
		/*Si no oculto las cuotas*/
		else{
			OcultarTabla();
		}
		
	}
	
	function OcultarCuotas(allCuo){
		
		for (var cuo of allCuo){
				cuo.style.display='none';
		}	
	}
	
	function MostrarCuotas(allCuo){
		for (var cuo of allCuo){
			cuo.style.display="";
	}	
	}
	
	function OcultarTabla(){
		tabla.style.display='none';
	}
	
	function MostrarTabla(){
		tabla.style.display='';
	}
	
	</script>
</body>
</html>