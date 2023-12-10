#drop procedure SP_PAGO_CUOTA;
DELIMITER //
create procedure  SP_PAGO_CUOTA		(	
										IdCuota int, 
										NroCuenta int,
                                        tipoMovimiento int,
                                        saldo 	DECIMAL(20,6),
                                        detalle varchar(200)
										)
BEGIN
        UPDATE 	CUOTAS_X_PRESTAMO c
		 set   	estado = false,
				fecha_pago = CURDATE()
		where c.idCuota = IdCuota;
		update cuentas c
		set  c.saldo = saldo
        where c.nroCuenta = NroCuenta;
	INSERT INTO MOVIMIENTOS(nroCuenta, fecha, importe, tipoMovimiento, saldo, detalle)
        select  NroCuenta, 
				CURDATE(), 
                c.importe * -1 as importe, 
                tipoMovimiento, 
                saldo, 
                detalle
		from  CUOTAS_X_PRESTAMO c
        where c.idCuota = IdCuota;
END //




