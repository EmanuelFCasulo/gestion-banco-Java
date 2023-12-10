<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Usuario" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestionar cuentas</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css/style.css"></jsp:include>
</style>
</head>
<body>
	
	<% 
		Usuario usuario = null;
		if(request.getSession().getAttribute("Usuario") != null) {
			usuario = (Usuario) request.getSession().getAttribute("Usuario");
		}
		
		ArrayList<Cuenta> listaCuentas = null;
		ArrayList<Cuenta> listaCuentasddl = null;
		int currentCuenta = 0;
		BigDecimal currentSaldo = new BigDecimal(0);
		long currentCbu = 0;
		if(request.getSession().getAttribute("cuentasDDL") != null)
		{
			try{
				listaCuentasddl = (ArrayList<Cuenta>) request.getSession().getAttribute("cuentasDDL");
				currentCuenta = listaCuentasddl.get(0).getNroCuenta();
				currentSaldo = listaCuentasddl.get(0).getSaldo();
				currentCbu = listaCuentasddl.get(0).getCbu();			}
			catch(IndexOutOfBoundsException ex){
				ex.printStackTrace();
				
			}
			
		}
		
		if(request.getSession().getAttribute("cuentaInfo") != null) 
		{
			try{
				
				Map<String, Object> cuentaInfo = (Map<String, Object>) request.getSession().getAttribute("cuentaInfo");
				int nroCuenta = (int) cuentaInfo.get("cuentaSeleccionada");
				BigDecimal saldo = (BigDecimal) cuentaInfo.get("saldo");
				long cbu = (long) cuentaInfo.get("cbu");
				
				currentCuenta = (int) cuentaInfo.get("cuentaSeleccionada");;	
				currentSaldo = (BigDecimal) cuentaInfo.get("saldo");
				currentCbu = (long) cuentaInfo.get("cbu");
			}
			catch(IndexOutOfBoundsException ex){
				ex.printStackTrace();
			}
		}
		
		if (request.getAttribute("CuotaPaga")!=null){
			%><script><%
			boolean exito = (boolean) request.getAttribute("CuotaPaga");
			if (exito){
				%>alert("Cuota pagada con exitó!")<%
			}
			else{
				%>alert("Ocurrio un error durante el pago de la cuota.")<%
			}
			%></script><%
		}
		
		if (request.getAttribute("Transferencia")!=null){
			%><script><%
			boolean sucess = (boolean) request.getAttribute("Transferencia");
			if (sucess){
				%>alert("Transferencia realizada con exitó!")<%
			}
			else{
				%>alert("Ocurrio un error durante la transferencia.")<%
			}
			%></script><%
			
		}
		
		if(request.getAttribute("Transferencia") != null){
			try{
				listaCuentas = (ArrayList<Cuenta>) request.getSession().getAttribute("cuentas");
				currentCuenta = listaCuentas.get(0).getNroCuenta();
				currentSaldo = listaCuentas.get(0).getSaldo();
				currentCbu = listaCuentas.get(0).getCbu();			}
			catch(IndexOutOfBoundsException ex){
				ex.printStackTrace();
				
			}
			
	 	}
		
		if(request.getAttribute("CuotaPaga") != null){
			try{
				listaCuentas = (ArrayList<Cuenta>) request.getSession().getAttribute("cuentas");
				currentCuenta = listaCuentas.get(0).getNroCuenta();
				currentSaldo = listaCuentas.get(0).getSaldo();
				currentCbu = listaCuentas.get(0).getCbu();			}
			catch(IndexOutOfBoundsException ex){
				ex.printStackTrace();
				
			}
		}
	 %> 
	 
	 
<header class="header bg-info p-3"> 
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
		
		<a class="btn btn-link" href="index.jsp"> 
		<svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
		<path d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"/>
		</svg>  <span style="color: black; font-weight: bold;">Cerrar Sesion</span></a>
	
	</div>
</header>

	<div class= "selectorCuentas">
		<h1 style="margin:auto; margin-bottom:20px; text-align:center;" class= "fw-bold fs-2 mt-5 ">Gestionar Cuentas</h1>

		<%if(currentCuenta != 0){
			%>			
	<label for="cuentaSeleccionada"><span class="badge bg-secondary ps-5 px-5">Cuenta actual: <%=currentCuenta%>  <br> Saldo:$ <%= String.format("%.2f", currentSaldo)%> </span> </label><br>	

	<form method="get" action="ServletCuenta" class="d-flex">
    <div class="flex-grow-1 me-2">
        <select name="cuentaSeleccionada" class="form-select">
            <% if(listaCuentasddl!=null) for(Cuenta cta : listaCuentasddl) { 
                    if(cta.isEstado()) {
                        if(cta.getNroCuenta() != currentCuenta){
            %>
                        <option value="<%=cta.getNroCuenta()%>">
						    Cuenta <%=cta.getNroCuenta()%> - <%=cta.getTipoCuenta().getCodTipo() == 1 ? "Caja de Ahorro" : "Cuenta Corriente"%>
						</option>


            <%      } else { %>
                        <option value="<%=cta.getNroCuenta()%>" selected > Cuenta  <%=cta.getNroCuenta()%> - <%=cta.getTipoCuenta().getCodTipo() == 1 ? "Caja de Ahorro" : "Cuenta Corriente"%></option>
            <%      } } }%>
        </select>
    </div>
    	<input id="btnSeleccionar" type="submit" value="Seleccionar" name="btnSeleccionar" class="btn btn-primary ms-4">
	</form>
		<span class="badge text-bg-primary mt-3 p-2 fs-6">CBU - <%=currentCbu%></span>
	
	</div>
	<div >
		<div class="d-flex justify-content-center ">
		</div>
		<div class="d-grid gap-2 col-3 mx-auto mt-3 text-body-danger shadow-lg p-3 mb-5 bg-body-tertiary rounded">
		    <div class="btn btn-info  mb-2 ">
		        <a href="/TPINT_GRUPO_2_LAB4/ServletMovimientos?getCuenta=<%=currentCuenta%>" class="text-dark fw-bold fs-3 ">
		            <input class="btn-primary" type="hidden" name="cta" value="<%=currentCuenta%>">Movimientos
		        </a>
		    </div>
		    <div class="btn btn-info  mb-2">
		        <a href="/TPINT_GRUPO_2_LAB4/transferencias.jsp?getCuenta=<%=currentCuenta%>&getSaldo=<%=currentSaldo%>" class="text-dark fw-bold fs-3">Transferencias</a>
		    </div>
		    <div class="btn btn-info mb-2">
    			<a href="/TPINT_GRUPO_2_LAB4/solicitarPrestamo.jsp?getCuenta=<%=currentCuenta%>" class="text-dark fw-bold fs-3">
      			  <input type="hidden" name="Usuario" value="<%=usuario%>">
       				 Solicitar préstamo
    			</a>
			</div>
		    <div class="btn btn-info mb-2">
		        <a href="/TPINT_GRUPO_2_LAB4/ServletPrestamos?pagoPrestamos=<%=currentCuenta%>&getSaldo=<%=currentSaldo%>" class="text-dark fw-bold fs-3">Pagar prestamos</a>
		    </div>
			<a href="inicioClientes.jsp" class="btn btn-secondary text-center fw-bold"> <span class="fa fa-arrow-left"></span> Volver</a>
		</div>
	
		<%}
		else{			
		%>
	 	<label for="cuentaSeleccionada">Usted no posee cuentas asignadas aún.</label><br>
		<%}%>
	</div>


</body>
</html>