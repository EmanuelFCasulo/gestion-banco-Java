<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Usuario" %>
<%@page import="entidad.TipoUsuario" %>
<%@page import="java.util.ArrayList"%>
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
<title>Alta Usuario - Admin</title>
</head>
<body>

<% 
	ArrayList<TipoUsuario> listaTipos = null;
	String dni = null;
	String mensaje = "";
	boolean isCliente = false;

	if(request.getAttribute("isCliente") != null)
	{
		isCliente = (Boolean) request.getAttribute("isCliente");

	}
	
	if(request.getAttribute("tiposUsuarios") != null)
	{
		listaTipos = (ArrayList<TipoUsuario>) request.getAttribute("tiposUsuarios");
		dni = (String) request.getAttribute("dni");

	}

	Usuario us = null;
	boolean agregado = false;
	if( request.getAttribute("agregado") != null) {
		agregado = (boolean)request.getAttribute("agregado");
		mensaje = (String)request.getAttribute("mensaje");
		us = (Usuario)request.getAttribute("usuario");
	}
	
	Usuario usuario = new Usuario();
 	if(session.getAttribute("Usuario")!=null){	
 		usuario = (Usuario)session.getAttribute("Usuario");
 	}
	
	%>
 
 
<header  style=" padding: 60px;">

  <nav style="" class="navbar navbar-expand-lg navbar-light bg-white fixed-top" >
    <div class="container-fluid">
      <div class="collapse navbar-collapse" id="navbarExample01">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
   			 <li class="nav-item active" style="background-color: highlight;">
   			 <% 	if(isCliente == false) { %>  
        		<a class="nav-link" aria-current="page" href="/TPINT_GRUPO_2_LAB4/adminClientes.jsp">
            		<i class="volverIcon fa fa-home"></i> 
            			Volver
       				 </a>
       		<%} else {%>
       		<a href="index.jsp"> <span class="fas fa-times-circle"></span>Salir</a>
       		<%	} %>
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
 
 <div class="container">

<div class="row">
 
<h1>Alta nuevo Usuario</h1>
  <%  if( agregado == false)	{ %>
<span>Por favor complete todos los datos </span>
</div>

<div class="row">

<form method="get" action="ServletUsuario">
    <fieldset>
      <legend>Nuevo usuario</legend>
<% 	if(us == null) { %> 
	<div class="form-floating">
        <input id="dni" type="text" value="<%= dni %>" disabled class="form-control mb-2"> <input type="hidden" name="txtDNI" value="<%= dni %>" >
        <label for="floatingInput">DNI</label>
      </div>
      <div class="form-floating">
      <%
		 	if(listaTipos!=null)		 		
				 if(isCliente == true) { %> 
      <input id="tipo" type="text" value="Cliente" disabled class="form-control mb-2"> <input type="hidden" name="txtTipo" value="<%= 2 %>" >
      <%	} else { %>
      
      <input id="tipo" type="text" value="<%= listaTipos.get(1).getTipoUsuario() %>" disabled class="form-control mb-2"> <input type="hidden" name="txtTipo" value="<%= listaTipos.get(1).getCodTipo() %>"> <%} %>
      <label for="floatingInput">Tipo Usuario</label>
      </div>
      <div class="form-floating">
        <input id="usuario" type="text"required class="form-control mb-2" name="txtUsuario" maxlength=20>
        <label for="floatingInput">Usuario</label>
      </div>
      <% } else if(us != null && agregado == false) { %>
      <div class="form-floating">
        <input id="dni" type="text" value="<%= dni %>" disabled class="form-control mb-2" > <input type="hidden" name="txtDNI" value="<%= dni %>" >
        <label for="floatingInput">DNI</label>
      </div>
      <div class="form-floating">
      <%
		 	if(listaTipos!=null)		 		
				 if(isCliente == true) { %> 
      <input id="tipo" type="text" value="Cliente" disabled class="form-control mb-2"> <input type="hidden" name="txtTipo" value="<%= 2 %>" >
      <%	} else { %>
      
      <input id="tipo" type="text" value="<%= listaTipos.get(1).getTipoUsuario() %>" disabled class="form-control mb-2"> <input type="hidden" name="txtTipo" value="<%= listaTipos.get(1).getCodTipo() %>"> <%} %>
      <label for="floatingInput">Tipo Usuario</label>
      </div>
      <div class="form-floating">
        <input id="usuario" type="text"required class="form-control mb-2" name="txtUsuario" value="<%= us.getUsuario() %>" maxlength=20>
        <label for="floatingInput">Usuario</label>
      </div>
      <% }  %>
      <div class="form-floating">
        <input id="contrasenia" type="password" required class="form-control mb-2" name="txtContrasenia" maxlength=10>
        <label for="floatingInput">contraseña</label>
      </div>
	  <div class="form-floating">
        <input id="contrasenia2" type="password" required class="form-control mb-2" name="txtContrasenia2" maxlength=10>
        <label for="floatingInput">Repita contraseña</label>
      </div>
      <div>
        <input class="btn btn-primary" type="submit" value="Registrar" name="btnAltaUs">
      </div>
    </fieldset>

</form>		

</div>

</div>
  <%	}%>
<div style="display:flex; flex-direction: column; align-items: center;">
<% if( us != null ) { %>
	<div>
	<% if(request.getAttribute("mensaje") != null) { %>
		<p style="font-size: 1.5rem;"> <%=mensaje%> </p>
	<%}%>
	</div>
	<%} %>
</div>

</div>
<%-- 	<form method="get" action="ServletCuenta">
	  <p class="button">
        <input id="btnRegistrar" type="submit" value="Agregar Cuenta" name="btnAltaCuenta">
        <input type="hidden" name="getDni" value=<%= dni %> >
      </p>
	</form> --%>

</body>
</html>