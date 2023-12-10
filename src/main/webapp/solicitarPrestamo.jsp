<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Usuario" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Solicitar prestamo</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css/style.css"></jsp:include>
</style>
</head>
<body>
<% 

Usuario usuario = null;
int currentCuenta = 0;

if(request.getSession().getAttribute("Usuario") != null) {
    usuario = (Usuario) request.getSession().getAttribute("Usuario");
} else {
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", "index.jsp");
}

if(request.getParameter("getCuenta") != null) {
    currentCuenta = Integer.parseInt(request.getParameter("getCuenta"));
}


///Resultados para cuando vuelve la pagina con response
String resString=null;
Boolean resBoolean=false;
if(request.getAttribute("resString")!=null)
	{
		resString =  request.getAttribute("resString").toString();
	}
if(request.getAttribute("resBoolean")!=null)
	{
	resBoolean =  Boolean.parseBoolean( request.getAttribute("resBoolean").toString());
	}
/////Terminacion de resultados
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

<br>


<div class="container mt-2">
    <div class="row">
		
			<div class="col-md-3 d-flex flex-column align-items-center border border-dark" style="background-color: #D1F2EB; background-image: url('img/prestamos3.jpg'); background-size: cover; background-position: center;">
			</div>
					
		
        <div class="col-md-8" style="background-color: #34495E;">	
	 <form class="form border border-dark rounded p-4" style="background-color: #AED6F1;" method="get" action="ServletPrestamosxAutorizar" onsubmit="return confirm('¿Estás seguro de solicitar el préstamo?')">
        <h1 class="text-center mb-4">Nuevo préstamo</h1>

        <% if (resString != null && !resString.equals("go")) { %>
            <div class="mb-4">
                <% if (!resBoolean) { %>
                    <h2 style="color: red;"><%= resString %></h2>
                <% } else { %>
                    <h2 style="color: green;"><%= resString %></h2>
                <% } %>
            </div>
        <% } %>

        <fieldset>
            <legend>Solicitud de préstamo para cuenta <%= currentCuenta %></legend>

            <div class="mb-3">
                <label for="txtMonto" class="form-label">Monto solicitado</label>
                <input id="txtMonto" type="text" required name="txtMonto" class="form-control" placeholder="$..">
            </div>

            <div class="mb-3">
                <label for="cuotas" class="form-label">Cant. De cuotas</label>
                <select name="txtCuotas" id="cuotas" class="form-select">
                    <option value="3">3</option>
                    <option value="6">6</option>
                    <option value="9">9</option>
                    <option value="12">12</option>
                    <option value="18">18</option>
                    <option value="24">24</option>
                    <option value="48">48</option>
                    <option value="60">60</option>
                    <option value="72">72</option>
                </select>
            </div>

            <input type="hidden" name="getCuenta" value="<%= currentCuenta %>">

            <div class="mb-3">
                <button id="btnRealizarSolicitudPrestamo" type="submit" class="btn btn-primary" name="btnRealizarSolicitudPrestamo">Solicitar Préstamo</button>
            </div>
        </fieldset>
    </form>
            <h4 class="text-center mb-4" style="font-family: 'Your Preferred Font'; color: #3498db;">
   				Recibirás información sobre la solicitud en las próximas 48 horas.
			</h4>
    </div>
</div>




</body>
</html>