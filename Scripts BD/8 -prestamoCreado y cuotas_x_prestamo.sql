DELIMITER //
CREATE TRIGGER PrestamoAutorizado
AFTER UPDATE ON `prestamos_x_autorizar`
FOR EACH ROW
BEGIN
    DECLARE monto DECIMAL(20, 6);
    DECLARE cta INT;
    DECLARE saldo_existente DECIMAL(20, 6);
    DECLARE nuevo_saldo DECIMAL(20, 6);
    
		SET monto = NEW.importe_pedido;
		SET cta = NEW.nroCuenta;
		SET saldo_existente = (SELECT Saldo FROM Cuentas WHERE nroCuenta = cta);
       
	IF saldo_existente IS NULL THEN
        SET saldo_existente = 0;
	END IF;
    
    IF NEW.estado > 1 THEN
      SET nuevo_saldo = saldo_existente + monto;
    END IF;
    
    IF monto > 0 and NEW.estado > 1 THEN
        UPDATE Cuentas SET Saldo = Saldo + monto WHERE nroCuenta = cta;
    END IF;
    
    IF NEW.estado > 1 then
    INSERT INTO movimientos (nroCuenta,fecha,importe,tipomovimiento,saldo,detalle) VALUES(cta,CURRENT_DATE(),monto,2,saldo_existente,'Acreditacion de Prestamo');
    ELSE
    INSERT INTO movimientos (nroCuenta,fecha,importe,tipomovimiento,saldo,detalle) VALUES(cta,CURRENT_DATE(),monto,2,saldo_existente,'Prestamo rechazado');
	END IF;
    
    IF NEW.estado > 1 THEN
        INSERT INTO prestamos
            (`dni`, `fecha`, `importe_a_pagar`, `importe_pedido`, `plazo_pago`, `monto_mensual`, `cantidad_cuotas`)
        VALUES
            ((SELECT dni FROM cuentas WHERE nroCuenta = NEW.nroCuenta),
             CURRENT_DATE(),
             CAST(NEW.importe_pedido * 1.15 AS DECIMAL(10, 0)),
             CAST(NEW.importe_pedido AS DECIMAL(10, 0)),
             '30',
             CAST((NEW.importe_pedido * 1.15) / NEW.cantidad_cuotas AS DECIMAL(10, 0)),
             NEW.cantidad_cuotas);
    END IF;
END //
DELIMITER ;

/* DROP TRIGGER IF EXISTS PrestamoAutorizado ;*/






 delimiter //
 CREATE TRIGGER PrestamoCreado AFTER  INSERT ON `prestamos`
  FOR EACH ROW 
  BEGIN
   declare x INT default 0;
   SET x=1;
   WHILE x <= new.cantidad_cuotas DO
    INSERT INTO cuotas_x_prestamo
(

`codPrestamo`,
`nroCuota`,
`fecha_venc`,
`importe`
)
VALUES
(
new.codPrestamo,
x,
DATE_ADD(new.fecha, INTERVAL 30*x DAY),
CAST((new.importe_pedido*1.15)/new.cantidad_cuotas AS decimal(10,0))
);
 SET x = x + 1;
 END WHILE;
  END //
 delimiter ;