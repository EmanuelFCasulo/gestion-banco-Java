<%@page import="entidad.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cuenta" %>
<%@page import="entidad.Usuario" %>
<%@page import="entidad.TipoCuenta" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Alta Cuenta - Admin</title>
</head>


<body onLoad="resultado();">

<% 
	ArrayList<Cuenta> listaCuentas= null;
	ArrayList<TipoCuenta> listaTC= null;
	String dni=null;
	String resString=null;
	Boolean resBoolean=false;
	if(request.getAttribute("Cuentas")!=null)
	{
		listaCuentas = (ArrayList<Cuenta>) request.getAttribute("Cuentas");
	}
	if(request.getAttribute("listaTC")!=null)
	{
		listaTC = (ArrayList<TipoCuenta>) request.getAttribute("listaTC");
	}
	if(request.getAttribute("dni")!=null)
	{
		dni =  request.getAttribute("dni").toString();
	}
	if(request.getAttribute("resString")!=null)
	{
		resString =  request.getAttribute("resString").toString();
	}
	if(request.getAttribute("resBoolean")!=null)
	{
		resBoolean =  Boolean.parseBoolean( request.getAttribute("resBoolean").toString());
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
        		<a class="nav-link" aria-current="page" href="adminCuentas.jsp">
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

 
<div class="menu">
	

	<h2>Alta de Cuenta</h2>
	<%
		if(!resBoolean && resString!= null){%>
	<br>
	 <h2 style="color:red;"><%=resString%></h2>
	<%}
	else
	{ 
		if( resString!= null)
		{
			if(!resString.equals("go") ){
	%>
		
		<br>
	 <h2 style="color:green;"><%=resString%></h2>
		
	<%}}}
	%>

<div class="container mt-5">
  <form method="get" action="ServletCuenta" class="w-50">
    <div class="form-group row">
      <div class="col-sm-3 text-right">
        <label for="txtdni" class="col-form-label col-form-label-lg mb-0">Buscar DNI:</label>
      </div>
      <div class="col-sm-6">
        <input type="text" class="form-control" id="txtdni" name="txtdni" required>
      </div>
      <div class="col-sm-3">
        <button type="submit" class="btn btn-primary" name="btnBuscar">Buscar</button>
      </div>
    </div>
  </form>
</div>




	<script>
		<%
		 	if(resString!= null && resBoolean!= null){	
		 		if(!resString.equals("go"))
		 		{
		 			%>
					function resultado(){alert("<%=resString%>");}
				<%}}
		 	%>
	</script>
	
	
	
	<%	
	if(listaCuentas != null && resBoolean!= null && resBoolean )
		
	{%>

<div class="container mt-4">
    <div class="table-container" style="background-color: #76D7C4; padding: 15px; border-radius: 10px;">
        <h4>Cuentas disponibles para el DNI <%= dni %></h4>
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Numero de cuenta</th>
                    <th>CBU</th>
                    <th>Fecha Creación</th>
                    <th>Tipo de cuenta</th>
                </tr>
            </thead>
            <tbody>
                <% for (Cuenta c : listaCuentas) {
                    if (c.isEstado()) { %>
                        <tr>
                            <td><%= c.getNroCuenta() %></td>
                            <td><%= c.getCbu() %></td>
                            <td><%= c.getFecha_creacion() %></td>
                            <td><%= c.getTipoCuenta().getTipoCuenta() %></td>
                        </tr>
                <% } } %>
            </tbody>
        </table>
    </div>
</div>


<br>

<%if(listaTC!=null && resBoolean ){ %>

<label for="TC">Elige un tipo de cuenta:</label>
 
<form method="get" action="ServletCuenta" class="form-inline">
    <div class="input-group">
        <select name="TC" class="form-control">
            <% for (TipoCuenta c : listaTC) { %>
                <option value="<%= c.getCodTipo() %>"><%= c.getTipoCuenta() %></option>
            <% } %>
        </select>
        <div class="input-group-append">
        	
            <input type="hidden" name="dni" value="<%= dni %>">
            &nbsp
            <button type="submit" class="btn btn-primary" name="btnAgregar"> Agregar </button>
        </div>
    </div>
</form>


		<%}
		else
		{%>
			<br>
			 <h3 style="color:red;"> <No disponible></h3>
		<%}}%>

		 </div>
		 
	 
		 
</body>
</html>