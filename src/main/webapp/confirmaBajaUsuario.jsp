<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Usuario" %>
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
<title>Eliminar Cliente - Admin</title>
</head>
<body>

<% 
	String dni = null;
	String mensaje = " ";

	if(request.getAttribute("dni") != null) {
		dni = (String) request.getAttribute("dni");
	}
	
	boolean eliminado = false;
	if( request.getAttribute("eliminado") != null) {
		eliminado = (boolean)request.getAttribute("eliminado");
	}
	
	Usuario usuario = new Usuario();
 	if(session.getAttribute("Usuario")!=null){	
 		usuario = (Usuario)session.getAttribute("Usuario");
 	}
	
 	
 	if( request.getAttribute("mensaje") != null ) {
		mensaje = (String) request.getAttribute("mensaje");
	}
 	
	boolean conSaldo = false;
 	if( request.getAttribute("conSaldo") != null ) {
 		conSaldo = (Boolean) request.getAttribute("conSaldo");
	}
 	
 	
	%>
 
<header  style=" padding: 60px;">

  <nav style="" class="navbar navbar-expand-lg navbar-light bg-white fixed-top" >
    <div class="container-fluid">
      <div class="collapse navbar-collapse" id="navbarExample01">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
   			 <li class="nav-item active" style="background-color: highlight;">
        		<a class="nav-link" aria-current="page" href="/TPINT_GRUPO_2_LAB4/ServletCliente?pag=1">
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


 
<div class="container">

<div class="row" >
	<div class="col-12 text-center ">
		<h1 class="fs-1 fw-bold mb-5">Eliminar cliente</h1>
	</div>
</div>
<div style="display:flex; flex-direction: column; align-items: center;">
<% if( eliminado == true || conSaldo == true) { %>	
	<div>
		<p style="font-size: 1.5rem;"> <%=mensaje %> <p>
	</div>
	<%} else { %>
	<div>
	<% if(request.getAttribute("clienteBaja") != null) {%>
		<%=request.getAttribute("clienteBaja")%>
	<%}%>
		<p class="mt-5 fs-3 fw-bold" > ¿Confirma realizar la baja del cliente y su usuario ? </p>
	</div>
	<form method="get" action="ServletCliente">
	  <p >
        <input class="btn btn-danger" id="btnSeleccionar" type="submit" value="Dar de baja" name="btnBajaUsuario">
        <a class="btn btn-secondary" aria-current="page" href="/TPINT_GRUPO_2_LAB4/ServletCliente?pag=1">Volver</a>
        <input type="hidden" name="dni" value=<%= dni %> >
      </p>
	</form>
<%} %>
</div>
</div>
</body>
</html>