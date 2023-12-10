<%@page import="java.util.Map"%>
<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://cdn.jsdelivr.net/npm/chart.js@3.5.1/dist/chart.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#miTabla').DataTable();
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#miTabla2').DataTable();
	});
</script>

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
<!-- Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<!-- Bootstrap icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css">
	<style type="text/css">
		<jsp:include page="css/style.css"></jsp:include>
	</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Estadísticas</title>
</head>
<body id="page-top" class="bg-light">
	<header style="padding: 50px;"> <nav
		class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
	<div class="container-fluid">
		<div class="collapse navbar-collapse" id="navbarExample01">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item active" style="background-color: highlight;">
					<a class="nav-link" aria-current="page" href="inicioAdmin.jsp">
						<i class="volverIcon fa fa-home"></i> Volver
				</a>
				</li>
			</ul>

			<div class="alert alert-info ml-auto">
				<i class="fas fa-user"></i> <span>admin</span>
			</div>
		</div>
	</div>
	</nav> </header>
	<div class="container">
		<div class="row mt-4">
			<div class="col-lg-6">
				<div class="card shadow mb-4 mx-4">
					<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">Gráfico de
							Cuentas por Tipo</h6>
					</div>
					<div class="card-body">
						<canvas id="graficoCuentasPorTipo" style="max-width: 100%;"></canvas>
					</div>
				</div>
			</div>

			<div class="col-lg-6">
				<div class="card shadow mb-4 ">
					<div class="card-header py-3">
						<h6 class="m-0 font-weight-bold text-primary">Gráfico de
							Tipos de movimientos registrados</h6>
					</div>
					<div class="card-body">
						<canvas id="graficoMovimientosPorTipo" style="max-width: 100%;"></canvas>
					</div>
				</div>
			</div>
		</div>

		<div class="card shadow mb-4 mx-4">
			<div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">Saldo Promedio
					por Tipo de Cuenta</h6>
			</div>
			<div class="card-body">
				<canvas id="graficoSaldoPromedio" style="max-width: 100%;"></canvas>
			</div>
		</div>


		<h2
			style="margin: auto; margin-bottom: 40px; text-align: center; margin-top: 120px">
			<b>Prestamos Activos por cliente</b>
		</h2>

		<%
			Map<String, Integer> prestamosPorClienteMap = (Map<String, Integer>) request
					.getAttribute("prestamosPorCliente");
			if (prestamosPorClienteMap != null && !prestamosPorClienteMap.isEmpty()) {
		%>
		<div class="container">
			<table id="miTabla2"
				class="table table-info table-striped table-hover mt-5 shadow-lg p-3 mb-5 bg-body-tertiary rounded">
				<thead>
					<tr>
						<th>Nombre y Apellido</th>
						<th>Prestamos</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Map.Entry<String, Integer> entry : prestamosPorClienteMap.entrySet()) {
					%>
					<tr>
						<td><%=entry.getKey()%></td>
						<td><%=entry.getValue()%></td>
					</tr>

					<%
						}
					%>
				</tbody>
			</table>
		</div>
		<%
			} else {
		%>
		<p class="text-center">No hay clientes con prestamos activos.</p>
		<%
			}
		%>

		<h2
			style="margin: auto; margin-bottom: 40px; text-align: center; margin-top: 120px">
			<b>Clientes por localidad</b>
		</h2>

		<%
			Map<String, Integer> clientesPorLocalidad = null;
			if (request.getAttribute("clientesPorLocalidad") != null) {
				clientesPorLocalidad = (Map<String, Integer>) request.getAttribute("clientesPorLocalidad");
		%>
		<div class="container">
			<table id="miTabla"
				class="table table-info table-striped table-hover mt-5 shadow-lg p-3 mb-5 bg-body-tertiary rounded">
				<thead>
					<tr>
						<th>Localidad</th>
						<th>Cantidad de clientes</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (Map.Entry<String, Integer> entry : clientesPorLocalidad.entrySet()) {
					%>
					<tr>
						<td><%=entry.getKey()%></td>
						<td><%=entry.getValue()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<%
		} else {
	%>
	<p class="text-center">No hay clientes registrados.</p>
	<%
		}
	%>



	<script>
var cuentasPorTipo = {}; // Declarar la variable aquí una vez

<%Map<String, Integer> cuentasPorTipoMap = (Map<String, Integer>) request.getAttribute("cuentasXTipo");
			if (cuentasPorTipoMap != null) {
				for (Map.Entry<String, Integer> entry : cuentasPorTipoMap.entrySet()) {%>
            cuentasPorTipo['<%=entry.getKey()%>'] = <%=entry.getValue()%>;
<%}
			}%>

    // Obtener el canvas donde se renderizará el gráfico
    var ctx = document.getElementById('graficoCuentasPorTipo').getContext('2d');

    // Configurar los datos para el gráfico de torta
    var data = {
        labels: Object.keys(cuentasPorTipo),
        datasets: [{
            label: 'Cuentas por Tipo',
            data: Object.values(cuentasPorTipo),
            backgroundColor: [
                'rgba(255, 99, 132, 0.6)',
                'rgba(54, 162, 235, 0.6)',
                'rgba(255, 206, 86, 0.6)',
                // Agrega más colores si hay más tipos de cuentas
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
            ],
            borderWidth: 1
        }]
    };

    // Configurar opciones del gráfico
    var options = {
        responsive: true,
        maintainAspectRatio: false,
    };

    // Crear el gráfico de torta
    var graficoCuentasPorTipo = new Chart(ctx, {
        type: 'pie',
        data: data,
        options: options
    });
</script>

	<script>

var movimientosPorTipo = {}; // Declarar la variable aquí una vez

<%Map<String, Integer> movimientosPorTipoMap = (Map<String, Integer>) request
					.getAttribute("movimientosXTipo");
			if (movimientosPorTipoMap != null) {
				for (Map.Entry<String, Integer> entry : movimientosPorTipoMap.entrySet()) {%>
            movimientosPorTipo['<%=entry.getKey()%>'] = <%=entry.getValue()%>;
<%}
			}%>

var ctxMovimientos = document.getElementById('graficoMovimientosPorTipo').getContext('2d');

// Datos para los tipos de movimientos registrados
var dataMovimientos = {
    labels: Object.keys(movimientosPorTipo),
    datasets: [{
        label: 'Tipos de movimientos registrados',
        data: Object.values(movimientosPorTipo),
        backgroundColor: [
            'rgba(255, 99, 132, 0.6)',
            'rgba(54, 162, 235, 0.6)',
            'rgba(255, 206, 86, 0.6)',
            'rgba(75, 192, 192, 0.6)',
            // Agrega más colores si hay más tipos de movimientos
        ],
        borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            // Agrega más colores si hay más tipos de movimientos
        ],
        borderWidth: 1
    }]
};

// Configuración de opciones para el gráfico de tipos de movimientos
var optionsMovimientos = {
    responsive: true,
    maintainAspectRatio: false,
};

// Crear el gráfico de torta para Tipos de movimientos registrados
var graficoMovimientosPorTipo = new Chart(ctxMovimientos, {
    type: 'pie',
    data: dataMovimientos,
    options: optionsMovimientos
});
</script>

	<script>
	
	
	 var saldoPromedio = {};
	    <% Map<String, BigDecimal> saldoPromedioMap = (Map<String, BigDecimal>) request.getAttribute("saldoPromedioxTipoCuenta");
	        if (saldoPromedioMap != null) {
	            for (Map.Entry<String, BigDecimal> entry : saldoPromedioMap.entrySet()) { %>
	                saldoPromedio['<%=entry.getKey()%>'] = <%=entry.getValue()%>;
	    <%      }
	        } %>
		// Obtener el canvas donde se renderizará el gráfico de barras
		var ctxSaldo = document.getElementById('graficoSaldoPromedio')
				.getContext('2d');

		// Configurar los datos para el gráfico de barras
		var dataSaldo = {
			labels : Object.keys(saldoPromedio),
			datasets : [ {
				label : 'Saldo Promedio por Tipo de Cuenta',
				data : Object.values(saldoPromedio),
				backgroundColor : [ 'rgba(255, 99, 132, 0.6)',
						'rgba(54, 162, 235, 0.6)', 'rgba(255, 206, 86, 0.6)',
						'rgba(75, 192, 192, 0.6)',
				// Puedes agregar más colores si hay más tipos de cuentas
				],
				borderColor : [ 'rgba(255, 99, 132, 1)',
						'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)',
						'rgba(75, 192, 192, 1)',
				// Puedes agregar más colores si hay más tipos de cuentas
				],
				borderWidth : 1
			} ]
		};

		// Configurar opciones del gráfico de barras
		var optionsSaldo = {
			responsive : true,
			maintainAspectRatio : false,
			indexAxis : 'y', // Cambia la escala al eje Y para barras más altas
			scales : {
				x : {
					beginAtZero : true
				// Asegura que la escala comience en cero
				}
			}
		};

		// Crear el gráfico de barras para el saldo promedio por tipo de cuenta
		var graficoSaldoPromedio = new Chart(ctxSaldo, {
			type : 'bar',
			data : dataSaldo,
			options : optionsSaldo
		});
	</script>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>