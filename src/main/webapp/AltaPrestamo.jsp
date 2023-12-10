<%@page import="java.util.ArrayList"%>
<%@page import="entidad.PrestamoxAutorizar" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"    pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Usuario" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
 <style>
 
 .dataTables_filter input {
    padding: 5px;
    border-radius: 5px;
    border: 1px solid #ccc;
}


.dataTables_length,
  .dataTables_filter {
    display: inline-block;
    margin-bottom: 10px; 
    margin-right: 5px/* Ajusta el espacio entre los elementos si es necesario */
  }

  /* Opcional: ajustes de estilo para los elementos individuales */
  .dataTables_length select,
  .dataTables_filter input[type="search"] {
    /* Agrega estilos según tus preferencias */
    /* Por ejemplo, ajusta el tamaño de la fuente, el color, etc. */
    padding: 6px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 4px;
  }
  
  
  
.dataTables_info {
  display: none;
}

/* Estilos para la paginación */
.dataTables_paginate {
  margin-top: 15px; /* Espacio entre la tabla y la paginación */
}

.dataTables_paginate a {
  display: inline-block;
  padding: 5px 10px;
  margin-right: 5px;
  border: 1px solid #ccc;
  border-radius: 3px;
  text-decoration: none;
  color: #333;
}

.dataTables_paginate a.current {
  background-color: #007bff;
  color: #fff;
  border: 1px solid #007bff;
}

#miTabla_info {
    display: none;
}

#miTabla_paginate {
    display: none;
}
</style>

<title>Administrar Prestamos - Admin</title>

</head>
<body>
<% 
	ArrayList<PrestamoxAutorizar> listaPrestamos= null;
	String resString=null;
	Boolean resBoolean=false;
	if(request.getAttribute("Prestamos")!=null)
	{
		listaPrestamos = (ArrayList<PrestamoxAutorizar>) request.getAttribute("Prestamos");
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
 	//Para paginado:
 		int pag = 0;
 		int cantPag = 0;
 	    //Al momento de dar siguiente o presionar otro botón, manda como parametro "pag" con el número de página.
 	    if (request.getAttribute("pag") != null) {
 	        pag = (int) request.getAttribute("pag");
 	        cantPag = (int) request.getAttribute("cantPag");
 	    } 
 		
%>
	

<header  style=" padding: 25px;">
  <nav style="" class="navbar navbar-expand-lg navbar-light bg-white fixed-top" >
    <div class="container-fluid">
      <div class="collapse navbar-collapse" id="navbarExample01">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
   			 <li class="nav-item active" style="background-color: highlight;">
        		<a class="nav-link" aria-current="page" href="inicioAdmin.jsp">
            		<i class="fas fa-arrow-left"></i> 
            		<!-- Icono de flecha -->
           				 Menu Principal
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


<div class="container col-8 mt-5 pt-5">
<br><br>
<h2 style="color: #007bff; font-family: 'Arial', sans-serif; text-align: center;">Autorización de Préstamos</h2>

<%
		if(!resBoolean && resString!= null){%>
	<br>
	 <h2 style="color:red;"><%=resString%></h2>
	<%}
	else
	{ 
		if(resString!= null)
		{
			if( !resString.equals("go")){
	%>
		
		<br>
	 <h2 style="color:green;"><%=resString%></h2>
		
	<%}}
		}

	%>
<script>
		<%
		 	if(resString!= null && resBoolean){	
		 		if(!resString.equals("go"))
		 		{
		 			%>
					function resultado(){alert("<%=resString%>");}
				<%}}
		 	%>
	</script>
<%if(listaPrestamos!=null && resBoolean){ %>

 <table id="miTabla" class="table table-striped table-hover text-center p-3" >
			<caption>Prestamos disponibles para aprobacion</caption>
			
	<thead class="thead-dark">			
			   
		<tr>
			<th>Codigo de prestamo pendiente</th>
		    <th>Numero de cuenta</th>
		    <th>Importe Solicitado</th>
		    <th>Cuotas</th>
		    <th>Fecha Alta</th>
		    <th>Estado</th>
		    <th colspan="2">&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;  Autorizacion</th>
		</tr>
	</thead>	
	<tbody>
<%

		for(PrestamoxAutorizar p:listaPrestamos)
		{%>
		
		<tr>
		 <form method="get" action="ServletPrestamosxAutorizar">
			<td><%=p.getCodPrestamoPendiente() %></td>
			<td><%=p.getNroCuenta() %></td>
			<td>$ <%= String.format("%.2f", p.getImporte())%></td>
			<td><%=p.getCantidad_cuotas()%></td>
			<td><%=p.getFecha_creacion() %></td>
			<%
			switch(p.getEstado())
			{
			case 0:%>
				<td>Desaprobado</td>
				<%break;
			case 1:%>
				<td>Pendiente de aprobacion</td>
			<%break;
			case 2:%>
			<td>Aprobado</td>
		<%break;
			 default: %>
				<td>Error</td>
			<%break;
			}%>
			
		
			<td>
			    <button type="submit" name="btnAutorizar" class="btn btn-success mr-3 mx-4" onclick="return confirm('¿Estás seguro de autorizar el prestamo solicitado?')">
			        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-lg" viewBox="0 0 16 16">
			            <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"/>
			        </svg>
			    </button>
			    <button type="submit" name="btnRechazar" class="btn btn-danger" onclick="return confirm('¿Rechazar el prestamo solicitado?')">
			        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
			            <path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
			        </svg>
			    </button>
			</td>

			<input type="hidden" name="codPrestamo" value="<%=p.getCodPrestamoPendiente()%>">

		</form>
	</tr>
		<%}%>
	</tbody>		
</table>

<%}
		else
		{%>
			<br>
			 <h3 style="color:red;"> <No disponible></h3>
		<%}%>
		
		
		<!---Empieza PAginado---->
<div class="dataTables_paginate" style="background-color: #f0f0f0; padding: 10px; border-radius: 5px;">
		 <%	if (cantPag >= 1) {
            //Si la página diferente a uno, si agrega el botón anterior.
               if(pag!=1){%>
                   <a href="ServletPrestamosxAutorizar?pag=<%=pag - 1%>">&lt;</a>
                      <%  }  //Calcula la cant de páginas a mostrar.
                            for (int i = 0; i < cantPag; i++) {
                         
                                if(i+1==pag){  //Si la página es igual a la página actual, muestra la etiqueta active.
                        %>
                            <span><%=i+1%></span>
                      
                      <%  } else { //Si no, sigue mostrando las etiquetas normales con la opción para desplazarse. %>
                             <a href="ServletPrestamosxAutorizar?pag=<%=i+1%>"><%=i+1%></a>
                        <%} }
                        //Sí pagina es diferente al número máximo de páginas, muestra la opción siguiente.
                        if(pag!=cantPag){%>
                            <a href="ServletPrestamosxAutorizar?pag=<%=pag + 1%>">&gt;</a>
                <%} }  else { //Si el máximo de páginas no es mayor a 1, muestra solo una página %>
                   			<span>1</span>
                <% }  %>						
	</div>
</div>	
		 
		 
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js">
</script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js">
</script>


		 
		 
<script type="text/javascript">
    $(document).ready(function() {
        $('#miTabla').DataTable({
            "language": {
                "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Spanish.json"
            },
            "columnDefs": [
                {
                    "targets": [0, 1, 2, 3, 4, 5, 6],
                    "searchable": true, // Permite la búsqueda en estas columnas
                    "orderable": false // Evita que se pueda ordenar por estas columnas si no es necesario
                }
            ]
        });
    });
</script>
</body>
</html>