<%@page import="entidad.Usuario" %>
<%@page import="entidad.Pais" %>
<%@page import="entidad.Localidad" %>
<%@page import="entidad.Provincia" %>
<%@page import="entidad.Cliente" %>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
	integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
	crossorigin="anonymous">
<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<!-- Bootstrap icons -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
<style type="text/css">
	<jsp:include page="css/style.css"></jsp:include>
</style>
<title>Modificar Cliente - Admin</title>

<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
$(document).ready(function () {
    var provinciasCargadas = false;

    $("#provincia").click(function () {
        if (!provinciasCargadas) {
            $.post("ServletCliente", { action: "getProvincias" }, function (data) {
                $("#provincia").html(data);
                provinciasCargadas = true;
            });
        }
    });

    $("#provincia").change(function () {
        var provinciaId = $(this).val();
        $.post("ServletCliente", { action: "getLocalidades", provinciaId: provinciaId }, function (data) {
            $("#localidad").html(data);
        });
    });
});
</script>
</head>
<body>

<% 
	String dni = null;
	Cliente cl = null;
	
	ArrayList<String> sexo = new ArrayList<String>();
	sexo.add("F");
	sexo.add("M");
	sexo.add("X");
	
	if(request.getAttribute("dni") != null) {
		dni = (String) request.getAttribute("dni");
	}
	
	if(request.getAttribute("cliente") != null) {
		cl = (Cliente) request.getAttribute("cliente");
	}

	ArrayList<Pais> listaPaises = null;
	if(request.getAttribute("nacionalidad") != null)
	{
		listaPaises = (ArrayList<Pais>) request.getAttribute("nacionalidad");
	}
	
	ArrayList<Provincia> listaProvincias = null;
	if(request.getAttribute("provincia") != null)
	{
		listaProvincias = (ArrayList<Provincia>) request.getAttribute("provincia");
	}
	
	ArrayList<Localidad> listaLocalidades = null;
	if(request.getAttribute("localidad") != null)
	{
		listaLocalidades = (ArrayList<Localidad>) request.getAttribute("localidad");
	}
	
	boolean modificado = false;
	if( request.getAttribute("modificado") != null) modificado = (boolean)request.getAttribute("modificado");
	
	
	Usuario usuario = new Usuario();
 	if(session.getAttribute("Usuario")!=null){	
 		usuario = (Usuario)session.getAttribute("Usuario");
 	}
	
	%>
 
  
 <header style="padding: 60px;">

		<nav style=""
			class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
			<div class="container-fluid">
				<div class="collapse navbar-collapse" id="navbarExample01">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item active" style="background-color: highlight;">
							<a class="nav-link" aria-current="page" href="/TPINT_GRUPO_2_LAB4/ServletCliente?pag=1">
								<i class="volverIcon fa fa-home"></i> Volver
						</a>
						</li>
					</ul>

					<div class="alert alert-info ml-auto">
						<i class="fas fa-user"></i> <span><%=usuario.getUsuario()%></span>
					</div>
				</div>
			</div>
		</nav>
</header>

<div class="container">

<div class="row">
 
<h1>Modificar cliente</h1>
  <% if(cl != null )	{ %>
</div>
<div class="row">
<form method="get" action="ServletCliente">
    <fieldset>
    
      <legend>Modificar cliente</legend>
      <div class="form-floating">
        
        <input id="dni" type="text" value="<%=cl.getDni()%>" class="form-control mb-2" disabled > <input type="hidden" name="txtDNI" value="<%=cl.getDni()%>" maxlength=10 >
        <label for="floatingInput">DNI</label>
      </div>
      <div class="form-floating">
        
        <input id="nombres" type="text"required class="form-control mb-2" name="txtNombre" value="<%=cl.getNombre()%>" maxlength=50>
        <label for="floatingInput">Nombre</label>
      </div>
      <div class="form-floating">
        
        <input id="apellidos" type="text" required class="form-control mb-2" name="txtApellido" value="<%=cl.getApellido()%>" maxlength=50 >
        <label for="floatingInput">Apellido</label>
      </div>      
      <div class="form-floating">
        
        <input id="cuil" type="text" required class="form-control mb-2" name="txtCuil" value="<%=cl.getCuil()%>" maxlength=13>
        <label for="floatingInput">CUIL</label>
      </div>
      <div class="form-floating">
        <select id="sexo" name="sexo" required class="form-control mb-2">
         <%
		 	if(sexo!=null)
				for(String s:sexo)
				{
					if(!s.equals(cl.getSexo())){
			%>
				<option value="<%= s %>" > <%= s %></option>
			<%	} else { %>
				<option value="<%= s %>" selected> <%= s %></option>
			<%	} } %>
        </select>
        <label for="floatingInput">Sexo</label>
      </div>
      <div class="form-floating">
        <select id="nacionalidad" name="nacionalidad" required class="form-control mb-2">
		  <%
		 	if(listaPaises!=null)
				for(Pais p:listaPaises)
				{
					if(p.getCodPais() != cl.getNacionalidad().getCodPais()){
			%>
			<option value="<%=p.getCodPais()%>" > <%=p.getPais()%></option>
			<%	} else {%>
				<option value="<%=p.getCodPais()%>" selected > <%=p.getPais()%></option>
		<%	} }%>
        </select>
        <label for="floatingInput">Nacionalidad</label>
      </div>
      <div class="form-floating">
        <input id="fecha_nac" type="date" required class="form-control mb-2" name="txtFecha_nac" value="<%= cl.getFecha_nac() %>" >
        <label for="floatingInput">Fecha de nacimiento</label>
      </div>     
      <div class="form-floating">
        <input id="direccion" type="text" required class="form-control mb-2" name="txtDireccion" value="<%= cl.getDireccion() %>" maxlength=200>
        <label for="floatingInput">Dirección</label>
      </div>
      
      <div class="form-floating">
        <select id="provincia" name="provincia" required class="form-control mb-2">
		  
			<option value="<%=cl.getProvincia().getCodProvincia()%>" > <%=cl.getProvincia().getProvincia()%></option>
        </select>
        <label for="floatingInput">Provincia</label> 
      </div>
      
      <div class="form-floating">
        <select id="localidad" name="localidad" required class="form-control mb-2"> 
			<option value="<%=cl.getLocalidad().getCodLocalidad()%>" > <%=cl.getLocalidad().getLocalidad()%></option>
        </select>
        <label for="floatingInput">Localidad</label> 
      </div>
      <div class="form-floating">
        
        <input id="email" type="email" required class="form-control mb-2" name="txtEmail" value="<%= cl.getCorreo_electronico() %>" maxlength=200>
        <label for="floatingInput">E-mail</label>
      </div>
      <div class="form-floating">
        <%  if( cl.getTelefonos() == null) { %>
                <input id="telefonos" type="text" required class="form-control mb-2" name="txtTelefonos" value=" " maxlength=200 placeholder="Teléfono">
        	<%	} else { %>
        	<input id="telefonos" type="text" required class="form-control mb-2" name="txtTelefonos" value="<%= cl.getTelefonos() %>" maxlength=200 placeholder="Teléfono">
        <%	}  %>
        <label for="floatingInput">Teléfonos</label>
      </div>
      <p class="button">
        <input id="btnRegistrar" type="submit" value="Modificar" required name="btnModificarBD" class="btn btn-primary mt-3">
      </p>
    </fieldset>
</form>		

</div>
    <%	}%>

<div class="row">
<% if( modificado == true) 
	{
%>	
	<div>
		<p style="font-size: 1.5rem;">Cliente modificado con exito <p>
	</div>
	<div>
	<% if(request.getAttribute("resultado") != null) {%>
		<%=request.getAttribute("resultado")%>
	<%}%>
	</div>
<%} %>
</div>
<div class="row">
	<form method="get" action="ServletUsuario">
	  <div >
        <input class="btn btn-primary mt-3" id="btnRegistrar" type="submit" value="Alta / Modificación Usuario Home Banking" name="btnModifUsuario">
        <input type="hidden" name="dni" value=<%= dni %> >
      </div>
	</form>
</div>


</div>

</body>
</html>