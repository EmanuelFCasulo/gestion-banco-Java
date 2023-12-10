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
<title>Administrar Clientes - Admin</title>
</head>
<body>

<% 
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
 
	
<div class="container-fluid menu">
<h1>Administrar Clientes</h1>
<span>Seleccione la opción deseada: </span>

<div class="row mt-3">
    <div class="col-md-12">
        <div class="card-deck d-flex justify-content-between">
            <div class="card text-center bg-light mx-3">
                <a href="/TPINT_GRUPO_2_LAB4/ServletCliente?getTxtDni" class="card-body">
                    <i class="fas fa-user-plus card-icon fa-3x text-primary"></i>
                    <h5 class="card-title mt-2 text-primary"> Alta nuevo Cliente</h5>
                </a>
            </div>
            <div class="card text-center bg-light mx-3">
                <a href="/TPINT_GRUPO_2_LAB4/ServletCliente?pag=1" class="card-body">
                    <i class='fas fa-user-edit card-icon fa-3x text-primary'></i>
                    <h5 class="card-title mt-2 text-primary">Modificar - Eliminar Cliente</h5>
                </a>
            </div>
        </div>
    </div>
</div>

</div>
</body>
</html>