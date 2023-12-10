<%@page import="entidad.Usuario" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
	<!-- bootstrap -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
	<style type="text/css">
		<jsp:include page="css/style.css"></jsp:include>
	</style>
	<title>Inicio - Cliente</title>
</head>
<body onLoad="bienvenido/a();">
<script>
		<%	Usuario usuario = new Usuario();
		 	if(session.getAttribute("Usuario")!=null){	
		 		usuario = (Usuario)session.getAttribute("Usuario");
		%>
	
			function bienvenida(){alert("Bienvenido <%=usuario.getcliente().getNombre()%> <%=usuario.getcliente().getApellido()%>");}
		<%
			}
		 	else {
		 %>
		 //alert("INICIE SESION PARA CONTINUAR.");
		 <%
		 	response.sendRedirect("/index.jsp");
		 	}
		%>
	</script>
	
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
			
			<a class="btn btn-link" href="index.jsp"> 
			<svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512">
			<path d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"/>
			</svg> Cerrar sesión</a>
		
		</div>
	</header>


<br>
	
<div class="container-fluid menu col-md-12">
    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Online Banking</h1>
            <p>Seleccione la opción deseada:</p>
        </div>
        <div class="col-md-2">
            <!-- Puedes dejar este espacio vacío o personalizar según necesites -->
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-md-12" style="width: 40%;">
            <div class="card text-center bg-light">
                <a href="gestionarCuentas.jsp" class="card-body">
                   	<i class='fas fa-book card-icon fa-3x'></i>
                    <h5 class="card-title mt-2">Mis Cuentas</h5>
                </a>
            </div>
        </div>
        <div class="col-md-12" style="width: 40%;">
            <div class="card text-center bg-light">
                <a href="clienteInforPersonal.jsp" class="card-body">
                    <i class='far fa-id-card card-icon fa-3x'></i>
                    <h5 class="card-title mt-2">Información Personal</h5>
                </a>
            </div>
        </div>
    </div>
</div>
	
	
	
</body>
</html>
