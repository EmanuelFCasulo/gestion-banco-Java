<%@page import="entidad.Pais" %>
<%@page import="entidad.Localidad" %>
<%@page import="entidad.Usuario" %>
<%@page import="entidad.Cliente" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css/style.css"></jsp:include>
</style>
<title>Modificar Cliente - Admin</title>
</head>
<body>

<% 

	
	Usuario usuario = new Usuario();
 	if(session.getAttribute("Usuario")!=null){	
 		usuario = (Usuario)session.getAttribute("Usuario");
 		
 	}
	Cliente cliente = usuario.getcliente();	

	
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
			</svg> Cerrar sesión</a>
		
		</div>
	</header>

<div style="display:flex; justify-content: space-around; align-items: center; flex-direction: column">


	<div class="">
	<h1 class="fs-1 mt-5  text-center fw-bold"><%=	cliente.getApellido()%> <%=cliente.getNombre() %> </h1>
	<p class="fs-4 mt-4 pt-1">Datos personales:</p>
	<div> 
	
			<table class="table table-primary table-striped-columns table-hover text-center fs-5">
				<tr>
					<th>Número de documento</th>
					<td><%=cliente.getDni() %></td>
				</tr>
				<tr>
			  		<th>Apellido</th>					
					<td><%=cliente.getApellido()%></td>
				</tr>
				<tr>
			  		<th>Nombre/s</th>					
					<td><%=cliente.getNombre()%></td>
				</tr>
				<tr>
			  		<th>CUIL</th>					
					<td><%=cliente.getCuil()%></td>
				</tr>
				
				<tr>
					<th>Sexo</th>
					<td><%=cliente.getSexo() %></td>
				</tr>
				<tr>
					<th>Nacionalidad</th>
					<td><%=cliente.getNacionalidad().getPais() %></td>
				</tr>
				<tr>
					<th>Domicilio</th>
					<td><%=cliente.getDireccion() %></td>
				</tr>
				<tr>
					<th>Localidad</th>
					<td><%= cliente.getLocalidad().getLocalidad()%>,
					<%= cliente.getProvincia().getProvincia()%>,
					<%= cliente.getPais().getPais()%>
					</td>
				</tr>
			
				<tr>
					<th>Fecha de nacimiento</th>
					<td><%=cliente.getFecha_nac() %></td>
				</tr>
			
				<tr>
					<th>Correo electrónico</th>
					<td><%= cliente.getCorreo_electronico() %></td>
				</tr>			
			</table>		
	<a href="inicioClientes.jsp" class="btn btn-warning text-center"> <span class="fa fa-arrow-left"></span> Volver</a>
	</div>
</div>
</div>
</body>
</html>
