<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Usuario" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<style type="text/css">
		<jsp:include page="css/style.css"></jsp:include>
	</style>
	<title>Informes</title>
</head>
<body>
<%
	Usuario usuario = new Usuario();
 	if(session.getAttribute("Usuario")!=null){	
 		usuario = (Usuario)session.getAttribute("Usuario");
 	}
	
%>

<header class="header"> 
	<div>
		<a href="inicioAdmin.jsp">
			<img style = "float: left; margin: 2px 20px 10px 0; ; " src="img/logo.jpg"  alt="logo" width="50" height="50"  />
		</a>
	</div>
	<div class="logged">
		<span><%=usuario.getUsuario()%></span>
		<span>LOGGUEADO</span>
	</div>
</header>

	<a classh="volver" ref="inicioClientes.jsp"> <span class="volverIcon fa fa-home">Volver</span></a>
	<div class="informes">
		<h1>Informes</h1>
		<p class= "cuentas">
		 <label for="tipoCuentas">Selecciones una cuenta:</label>
		 <select id="tipoCuentas" name= "cuenta" required style="width: 176px;">
		 <option> cuenta</option>
		 <option> cuenta</option>
		 <option> cuenta</option>
		 </select>
		</p>
	</div>
	<div class="informes">
	 <label for="lblSaldo">Saldo: </label>
	 <label for="lblSaldoNumero"> $000000</label><br>
	<br><br>
	 <label for="lblTransacciones">Ultimas Transacciones: </label>
	<table class="tablaMovimientos">
		<tr>
			<td>Cod. movimiento</td>
			<td>Nro. cuenta</td>
			<td>Fecha movimiento</td>
			<td>Importe</td>
			<td>Tipo</td>
		</tr>
		<tr>
			<td>Ejemplo de movimiento</td>
			<td>Ejemplo de movimiento</td>
			<td>Ejemplo de movimiento</td>
			<td>Ejemplo de movimiento</td>
			<td>Ejemplo de movimiento</td>
		</tr>
		<tr>
			<td>Ejemplo de movimiento</td>
			<td>Ejemplo de movimiento</td>
			<td>Ejemplo de movimiento</td>
			<td>Ejemplo de movimiento</td>
			<td>Ejemplo de movimiento</td>		
		</tr>
	</table>
	</div>

</body>
</html>