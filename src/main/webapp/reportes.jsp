<%@page import="entidad.Usuario" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Movimiento" %>
<%@page import="entidad.TipoMovimiento" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.math.BigDecimal"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<!-- Bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<!-- Bootstrap icons -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css/style.css"></jsp:include>
</style>
<title>Reportes - Admin</title>
</head>

<body>
<% 
	boolean isCliente = true;
	if(request.getAttribute("isCliente") != null)
	{
		isCliente = (Boolean) request.getAttribute("isCliente");
	
	}
	ArrayList<TipoMovimiento> listaTipoMovimientos = null;
	if(request.getAttribute("tipoMovimiento") != null)
	{
		listaTipoMovimientos = (ArrayList<TipoMovimiento>) request.getAttribute("tipoMovimiento");
	}
	ArrayList<Movimiento> listaMovimientos = null;
	if(request.getAttribute("listaMovimientos") != null)
	{
		listaMovimientos = (ArrayList<Movimiento>) request.getAttribute("listaMovimientos");
	}
	
	Usuario usuario = new Usuario();
 	if(session.getAttribute("Usuario")!=null){	
 		usuario = (Usuario)session.getAttribute("Usuario");
 	}
 %>

 
 <header  style=" padding: 50px;">
  <nav style="" class="navbar navbar-expand-lg navbar-light bg-white fixed-top" >
    <div class="container-fluid">
      <div class="collapse navbar-collapse" id="navbarExample01">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
   			 <li class="nav-item active" style="background-color: highlight;">
        		<a class="nav-link" aria-current="page" href="inicioAdmin.jsp">
            		<i class="volverIcon fa fa-home"></i> 
            			Volver
       				 </a>
    		</li>
			</ul>

        	<div class="alert alert-info ml-auto">
               <i class="fas fa-user"></i>
               <span><%=usuario.getUsuario()%></span>
            </div>
      </div>
    </div>
  </nav>
</header>





<div style="display:flex; justify-content: space-around; align-items: center; flex-direction: column">
<% if(isCliente == true ) { %>
<h1> Reportes </h1>
<div class= "ABM">
<form class= "form " method = "get" action="ServletMovimientos"> 
    <fieldset>
	   <div class="row">
        <div class="col-md-3">
            <div class="form-group">
                <label for="tipoMovimiento">Movimiento:</label>
                <select class="form-control" id="movimiento" name="movimiento" required>
                    <option value=""></option>
                    <% if (listaTipoMovimientos != null) for (TipoMovimiento t : listaTipoMovimientos) { %>
                    <option value="<%=t.getCodTipo()%>"><%=t.getTipoMovimiento()%></option>
                    <% } %>
                </select>
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <label for="labelDni">DNI:</label>
                <input type="text" class="form-control" maxlength="10" id="dni" name="txtDni" >
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <label for="labelFechaInicio">Fecha de inicio:</label>
                <input type="date" class="form-control" name="txtFechaInicial" required>
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <label for="labelFechaFin">Fecha de fin:</label>
                <input type="date" class="form-control" name="txtFechaFinal" required>
            </div>
        </div>
    </div>

	<br>
    <div class="form-group">
        <div style="display:flex; justify-content: space-evenly;">
            <input id="btnFiltrar" type="submit" class="btn btn-primary" value="Filtrar" name="btnFiltrarMovimiento"
                style="width: 200px;">
        </div>
    </div>


<table class="table table-bordered table-striped" style="width: 100%; margin-top: 20px;">
    <thead class="thead-dark">
        <tr>
            <th style="background-color: #76D7C4;">Numero de cuenta</th>
            <th style="background-color: #76D7C4;">Detalle</th>
            <th style="background-color: #76D7C4;">Fecha</th>
            <th style="background-color: #76D7C4;">Importe</th>
            <th style="background-color: #76D7C4;">Saldo</th>
        </tr>
    </thead>
    <tbody>
        <% 
            if(listaMovimientos != null)
                for(Movimiento mov:listaMovimientos) {            
        %>
        <tr class="table-pastel">
            <td><%=mov.getNroCuenta().getNroCuenta()%></td>   
            <% if( mov.getDetalle() == null) { %>
                <td> --- </td>    
            <% } else { %>
                <td><%=mov.getDetalle()%></td>    
            <% } %>
            <td><%=mov.getFecha()%></td>
            <td><%=mov.getImporte()%></td>
            <td><%=mov.getSaldo()%></td>
        </tr>
        <% } %>
    </tbody>
</table>




 	<br><br>
	<%
		int numLista = (int)request.getAttribute("numLista");
		int numCuenta = (int)request.getAttribute("numCuentas");
		BigDecimal saldos = (BigDecimal)request.getAttribute("saldos");
		
	%>


<table class="table table-bordered table-striped" style="width: 52%; margin-top: 20px; background-color: #F5E6E6;">
    <tr>
        <th>Cantidad de transacciones</th>
        <td><%=numLista%></td>
    </tr>
    <tr>
        <th>Cantidad de cuentas</th>
        <td><%=numCuenta%></td>
    </tr>
    <tr>
        <th>Sumatoria de saldos</th>
        <td><%=saldos%></td>
    </tr>
</table>


	</fieldset>
</form>
	 <%} else {%>	  
	 <form class="form" action="ServletMovimientos" method="post">
		 <h2>Cliente no encontrado. Por favor ingrese un dni válido</h2>
		 <div style="display:flex; justify-content: space-evenly;">
	     	<input id="btnAtras" type="submit" value="Atras" name="btnAtras" style="width: 200px;">
		 </div>      		
	 </form>
	  	 <%} %>	  
</div>
</div>
</body>
</html>