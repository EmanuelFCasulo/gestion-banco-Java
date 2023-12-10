#Clientes Argentinos por localidad 
SELECT L.localidad, COUNT(C.dni) AS cantidad_clientes
FROM Localidades L
LEFT JOIN Clientes C ON L.codLocalidad = C.codLocalidad AND L.codProvincia = C.codProvincia AND L.codPais = C.codPais
GROUP BY L.localidad
HAVING COUNT(C.dni) > 0;


#Cuentas por tipo --LISTO
SELECT TC.tipoCuenta, COUNT(C.nroCuenta) AS cantidad_cuentas
FROM TiposCuentas TC
LEFT JOIN Cuentas C ON TC.codTipo = C.tipoCuenta
GROUP BY TC.tipoCuenta;

#Movimientos por tipo -- LISTO
SELECT TM.tipoMovimiento, COUNT(M.codMovimiento) AS cantidad_movimientos
FROM TiposMovimientos TM
LEFT JOIN Movimientos M ON TM.codTipo = M.tipoMovimiento
GROUP BY TM.tipoMovimiento;


#Saldo promedio por tipo de cuenta --LISTO
SELECT TC.tipoCuenta, AVG(C.saldo) AS saldo_promedio
FROM TiposCuentas TC
LEFT JOIN Cuentas C ON TC.codTipo = C.tipoCuenta
GROUP BY TC.tipoCuenta;

#Clientes con préstamos activos 
SELECT CONCAT (C.nombre, " ", C.apellido) AS nombre_apellido, COUNT(P.codPrestamo) AS cantidad_prestamos
FROM Clientes C
LEFT JOIN Prestamos P ON C.dni = P.dni
WHERE P.estado = 1 and c.nombre <> 'Admin'
GROUP BY C.nombre, C.apellido;

#Cuotas de préstamos vencidas 
SELECT P.codPrestamo, COUNT(CP.idCuota) AS cuotas_vencidas
FROM Prestamos P
LEFT JOIN Cuotas_x_prestamo CP ON P.codPrestamo = CP.codPrestamo
WHERE CP.fecha_venc < CURRENT_DATE AND CP.estado = 1
GROUP BY P.codPrestamo;

select * from movimientos