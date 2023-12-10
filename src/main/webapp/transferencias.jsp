<%@page import="entidad.Usuario" %>
<%@page import="negocioImpl.CuentaNegocioImpl" %>
<%@page import="entidad.Cuenta" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page session="true"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.math.BigDecimal"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transferencias</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<style type="text/css">
<jsp:include page="css/style.css"></jsp:include>
</style>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
</head>
<body>

<%
	Cuenta cuenta = null;
	int currentCuenta = 0;
	int nroCuenta = 0;
	BigDecimal currentSaldo = null;

	ArrayList<Cuenta> cuentasList = null;
	Usuario usuario = new Usuario();
	
	if(session.getAttribute("Usuario")!=null && request.getParameter("getCuenta")!=null && request.getSession().getAttribute("cuentasDDL") != null){	
		cuentasList = (ArrayList<Cuenta>) request.getSession().getAttribute("cuentasDDL");
		usuario = (Usuario)session.getAttribute("Usuario");	
		currentCuenta = Integer.parseInt(request.getParameter("getCuenta"));
		nroCuenta = Integer.parseInt(request.getParameter("getCuenta"));	
		currentSaldo = new BigDecimal(request.getParameter("getSaldo"));

		for(int i=0;i<cuentasList.size();i++) { 
 			if(cuentasList.get(i).isEstado()){
 				if (cuentasList.get(i).getNroCuenta()==nroCuenta){
 					cuenta = cuentasList.get(i);
 					break;
 				}
 			}
		}
		
	}
	else {
	%>
	<script>
		alert("Permiso denegado. Inicie sesion para continuar")
	</script>
	<%
 	response.sendRedirect("/TPINT_GRUPO_2_LAB4/index.jsp");
	}
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
			</svg>  <span style="color: black; font-weight: bold;">Volver</span></a>
		
		</div>
	</header>

<br><br>
<h1 style="margin:auto;text-align:center;margin-bottom:130px;">Transferencias</h1>

<form class="container card shadow-lg p-3 mb-5 bg-body-tertiary rounded" method="post">
    <fieldset>
        <legend class="card-header text-center fw-bold">Nueva transferencia</legend>

        <div class="mb-3 card">
            <label for="txtCtaOrigen" class="col-form-label">Cuenta origen origen</label>
            <input readonly value="<%=cuenta.getNroCuenta()%>" type="text" class="form-control" id="txtCtaOrigen" name="txtCtaOrigen">
            <input hidden value="<%=cuenta.getNroCuenta()%>" type="text" id="txtCtaOrigenHidden" name="txtCtaOrigenHidden">
        </div>

        <div class="mb-3">
            <label for="txtImporteDisponible" class="col-form-label">Importe disponible:</label>
            <input id="txtImporteDisponible" type="text" value="$<%= String.format("%.2f",currentSaldo)%>" class="form-control" name="txtImporteDisponible" readonly>
        </div>

        <div class="row">
            <div class="col-md-6 mb-3">
                <label for="txtCbu" class="col-form-label">CBU destino:</label>
                <input id="txtCbu" type="number" required class="form-control" name="txtCbu" placeholder="CBU..">
            </div>

            <div class="col-md-6 mb-3">
                <label for="txtDNI" class="col-form-label">DNI destino:</label>
                <input id="txtDNI" type="number" required class="form-control" name="txtDNI" placeholder="DNI..">
            </div>
        </div>

        <div class="mb-3">
            <label for="txtMonto" class="col-form-label">Monto:</label>
            <input id="txtMonto" type="text" required class="form-control" name="txtMonto" placeholder="$00,00">
        </div>

        <div class="mb-3">
            <label for="txtDetalle" class="col-form-label">Detalle:</label>
            <input id="txtDetalle" type="text" class="form-control" name="txtDetalle" placeholder="..">
        </div>

        <div class="mb-3">
            <button id="btnRealizarTransferencia" name="btnRealizarTransferencia" onclick="confirmar();" value="1" class="btn btn-primary">Transferir</button>
        </div>
    </fieldset>
</form>


<script>
	function confirmar(){
		var monto = document.getElementById('txtMonto');
		var dni = document.getElementById('txtDNI');
		var cbu = document.getElementById('txtCbu');
		
		<%
 				%>if(cbu.value==<%=cuenta.getCbu()%>){
 					alert("No puede transferir dinero a la misma cuenta!");
 				}<%
		%>
		else if (monto.value == "" || dni.value =="" || cbu.value ==""){
			alert("Llene los campos");
		}
		else if(isNaN(monto.value)|| isNaN(dni.value) || isNaN(cbu.value)){
			alert("Los datos ingresados no estan en el formato correcto.");
		}
		else if (<%=cuenta.getSaldo()%><monto.value){
			alert("El saldo de la cuenta es insuficiente para realizar la transferencia");
		}
		else if(monto.value<500){
			alert("El monto minimo de transferencia es de $500");
		}
		else if (confirm("Presione aceptar para confirmar la transferencia..")){
			document.forms[0].action = "ServletTransferencia";
			document.forms[0].submit();
		}
	}
</script>	
</body>
</html>