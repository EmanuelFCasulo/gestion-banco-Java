DELIMITER //
create procedure  SP_TRANSFERENCIA		(	
										nroCuentaOrigen int, 
										nroCuentaDestino int, 
										importe decimal(20,6) ,
                                        saldoCtaOrigen decimal(20,6),
										SaldoCtaDestino decimal(20,6),
										tipoMovimiento int,
										detalle varchar(200)
										)
BEGIN
    DECLARE FECHA  DATE DEFAULT CURDATE(); 
	DECLARE `_rollback` BOOL DEFAULT 0;
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET `_rollback` = 1;
	START TRANSACTION ;
		INSERT INTO MOVIMIENTOS(nroCuenta, fecha, importe, tipoMovimiento, saldo, detalle) values(nroCuentaOrigen,FECHA,-importe,tipoMovimiento,saldoCtaOrigen,detalle);
		INSERT INTO MOVIMIENTOS(nroCuenta, fecha, importe, tipoMovimiento, saldo, detalle) values(nroCuentaDestino,FECHA,importe,tipoMovimiento,SaldoCtaDestino,detalle);
        UPDATE 	CUENTAS 
		   SET	saldo = saldoCtaOrigen
		WHERE nroCuenta = nroCuentaOrigen;
		UPDATE CUENTAS 
		   SET	saldo = SaldoCtaDestino
		WHERE nroCuenta = nroCuentaDestino;
	 IF `_rollback` THEN
        ROLLBACK;
    else
        COMMIT;
    END IF;
END //
