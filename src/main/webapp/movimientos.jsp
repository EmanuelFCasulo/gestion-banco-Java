<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Usuario" %>
<%@page import="entidad.Movimiento" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.math.BigDecimal"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movimientos</title>
<style type="text/css">
	<jsp:include page="css/style.css"></jsp:include>
</style>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#miTabla').DataTable();
	});
</script>


</head>
<body>

<% 

	BigDecimal currentSaldo = new BigDecimal(0);
	ArrayList<Movimiento> listaMovimientos = null;
	int currentCuenta = 0;
	Cuenta cta = null;
	int tipoCta = 0;
	String nombreCta = null;
	
	if(request.getSession().getAttribute("cuentas") != null)
	{
		currentCuenta = Integer.parseInt(request.getParameter("getCuenta"));
		cta = (Cuenta) request.getAttribute("cuenta");
		tipoCta = cta.getTipoCuenta().getCodTipo();
		nombreCta = cta.getTipoCuenta().getTipoCuenta();
	}
	
	if( request.getAttribute("saldo") != null) 
	{
		currentSaldo = (BigDecimal) request.getAttribute("saldo");
		listaMovimientos = (ArrayList<Movimiento>) request.getAttribute("movimientos");
		
		for (int i = listaMovimientos.size() - 1; i >= 0; i--) {
		    Movimiento movimiento = listaMovimientos.get(i);
		    if (movimiento.getNroCuenta().getNroCuenta() != currentCuenta) {
		        listaMovimientos.remove(i);
		    }
		}
	}
		
	Usuario usuario = new Usuario();
 	if(session.getAttribute("Usuario")!=null){	
 		usuario = (Usuario)session.getAttribute("Usuario");
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
		
		<a class="btn btn-link" href="gestionarCuentas.jsp"> 
		<svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
		<path d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"/>
		</svg>  <span style="color: black; font-weight: bold;">Volver</span></a>
	
	</div>
</header>
 
 
	<h1 style="margin:auto; margin-bottom:40px;text-align:center;margin-top:120px"><b>Movimientos</b></h1>

<% if(listaMovimientos != null && !listaMovimientos.isEmpty()) { %>
    <div class="container">
        <table id="miTabla" class="table table-info table-striped table-hover mt-5 shadow-lg p-3 mb-5 bg-body-tertiary rounded">
            <thead>
                <tr>
                    <th>Fecha</th>
                    <th>Importe</th>
                    <th>Tipo de movimiento</th>
                    <th>Detalle</th>
                </tr>
            </thead>
            <tbody>
                <% for(Movimiento mov : listaMovimientos) { %>
                    <tr>
                        <td><%= mov.getFecha() %></td>
                        <td>$ <%= String.format("%.2f", mov.getImporte()) %></td>
                        <td><%= mov.getTipoMovimiento().getTipoMovimiento() %></td>
                        <% if(mov.getDetalle() == null) { %>
                            <td>---</td>
                        <% } else { %>
                            <td><%= mov.getDetalle() %></td>
                        <% } %>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
<% } else { %>
    <p class="text-center">El usuario no tiene movimientos registrados en la cuenta <%= currentCuenta %>.</p>
<% } %>

</body>
</html>