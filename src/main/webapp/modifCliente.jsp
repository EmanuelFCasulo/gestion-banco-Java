<%@page import="entidad.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
	integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
	crossorigin="anonymous">
<style type="text/css">
<
jsp
:include
 
page
="css/style
.css
"
>
</
jsp
:include
>
</style>
<title>Modificar Cliente - Admin</title>
</head>
<body>

	<% 
	ArrayList<Cliente> listaClientes = null;
	ArrayList<Cliente> clientesPaginados = null;
	if(request.getAttribute("clientes") != null)
	{
		listaClientes = (ArrayList<Cliente>) request.getAttribute("clientes");
		clientesPaginados = (ArrayList<Cliente>) request.getAttribute("clientesPaginados");
		
	}

	//Para paginado:
	int pag = 0;
	int cantPag = 0;
    //Al momento de dar siguiente o presionar otro botón, manda como parametro "pag" con el número de página.
    if (request.getAttribute("pag") != null) {
        pag = (int) request.getAttribute("pag");
        cantPag = (int) request.getAttribute("cantPag");
    } 
	
	
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
							<a class="nav-link" aria-current="page" href="adminClientes.jsp">
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
	
	<div class="container d-flex flex-column align-items-center">
    <h1 class="mb-3 "><span class="badge bg-info text-dark p-3 border border-info border-start-0 rounded-end bg-opacity-10 px-5">Modificar cliente</span></h1>

    <span class="fw-bold  mb-2 mt-3">Por favor seleccione el cliente que desea modificar</span>

	<div class="row">
    <form method="get" action="ServletCliente" class="d-flex justify-content-between">
        

	 <div class="mx-2 mb-2 d-flex align-items-center">
        <label for="campo" class="mb-3" >Campo:</label>
        <select name="campoBusqueda" id="campo" class="form-select mb-3 ms-2" required>
            <option value="dni">DNI</option>
            <option value="nombre">Nombre</option>
            <option value="apellido">Apellido</option>
            <option value="email">Email</option>
            <option value="localidad">Localidad</option>
        </select>
    </div>
    <div class="mr-2 mb-2">
        <input type="text" name="valorBusqueda" placeholder="Valor a buscar" class="form-control mb-3" required>
    </div>
	
	        <div class="mr-2 mb-2">
	            <input type="submit" value="Filtrar" name="btnFiltrar" class="btn btn-primary">
	            <input type="submit" value="Limpiar filtro" name="btnLimpiar" class="btn btn-outline-primary" onclick="limpiarFiltro()">
	        </div>
	    </form>
	</div>


</div>


		<div class="row">
			<div class="col-12">
				<table class="table table-striped table-hover text-center">
					<thead >
						<tr class="table-dark">
							<th>DNI</th>
							<th>Nombre</th>
							<th>Sexo</th>
							<th>Localidad</th>
							<th>Email</th>
							<th>Acción</th>
						</tr>
					</thead>
					<tbody>
						<%  if(clientesPaginados != null)
		for(Cliente cl : clientesPaginados) 
		{			
			if(cl.isEstado()) {   //Así solo muestra los que tienen el estado en true
	%>
						<tr>
							<form name="listadoClientes" action="ServletCliente" method="get">
								<td><%= cl.getDni() %> <input type="hidden" name="dni"
									value=<%=cl.getDni()%>></td>
								<td><%= cl.getApellido() %>, <%= cl.getNombre() %></td>
								<td><%= cl.getSexo()%></td>
								<td><%= cl.getLocalidad().getLocalidad() %>, <%= cl.getLocalidad().getProvincia().getProvincia() %>,
									<%= cl.getLocalidad().getPais().getPais() %></td>
								<td><%= cl.getCorreo_electronico() %></td>
								<td>
									<div style="display: flex; justify-content: space-evenly;">
										<button type="submit" name="btnModificar" class="btn btn-warning bg-opacity-10">
										    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
										        <path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
										    </svg>
										</button>
										<button type="submit" name="btnEliminar" class="btn btn-danger bg-opacity-10">
										    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
											  <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
											</svg>
										</button>
									</div>
								</td>

							</form>
						</tr>
						<% } } %>

					</tbody>
				</table>
			</div>
		</div>

		<div class="row justify-content-center mt-4">
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <% if (cantPag >= 1) {
                    // Si la página es diferente a uno, agrega el botón anterior.
                    if (pag != 1) { %>
                        <li class="page-item">
                            <a class="page-link" href="ServletCliente?pag=<%=pag - 1%>" aria-label="Previous">
                                <span aria-hidden="true">&lt;</span>
                            </a>
                        </li>
                    <% }
                    // Calcula la cantidad de páginas a mostrar.
                    for (int i = 0; i < cantPag; i++) {
                        if (i + 1 == pag) { // Si la página es igual a la página actual, muestra la etiqueta active.
                    %>
                            <li class="page-item active" aria-current="page">
                                <span class="page-link"><%=i + 1%><span class="visually-hidden">(current)</span></span>
                            </li>
                        <% } else { // Si no, sigue mostrando las etiquetas normales con la opción para desplazarse. %>
                            <li class="page-item">
                                <a class="page-link" href="ServletCliente?pag=<%=i + 1%>"><%=i + 1%></a>
                            </li>
                        <% }
                    }
                    // Si la página es diferente al número máximo de páginas, muestra la opción siguiente.
                    if (pag != cantPag) { %>
                        <li class="page-item">
                            <a class="page-link " href="ServletCliente?pag=<%=pag + 1%>" aria-label="Next">
                                <span aria-hidden="true">&gt;</span>
                            </a>
                        </li>
                    <% } } else { // Si el máximo de páginas no es mayor a 1, muestra solo una página %>
                    <li class="page-item active" aria-current="page">
                        <span class="page-link">1<span class="visually-hidden">(current)</span></span>
                    </li>
                <% } %>
            </ul>
        </nav>
    </div>
	</div>
	
	<script>
    document.getElementById("campo").addEventListener("change", function() {
        var campoBusqueda = this.value;
        var valorBusquedaInput = document.querySelector("input[name='valorBusqueda']");
        
        if (campoBusqueda === "dni") {
            valorBusquedaInput.setAttribute("type", "number");
            valorBusquedaInput.setAttribute("pattern", "[0-9]*"); 
        } else {
            valorBusquedaInput.setAttribute("type", "text");
            valorBusquedaInput.removeAttribute("pattern"); 
        }
    });

    document.getElementById("campo").dispatchEvent(new Event('change'));
    
    function limpiarFiltro() {
        var valorBusquedaInput = document.querySelector("input[name='valorBusqueda']");
        valorBusquedaInput.value = ""; 
        var valorBusquedaInput = document.querySelector("input[name='valorBusqueda']");
    valorBusquedaInput.removeAttribute("required");
    }
</script>
</body>
</html>