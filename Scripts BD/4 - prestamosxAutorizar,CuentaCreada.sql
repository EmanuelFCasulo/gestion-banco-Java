/*DROP trigger `bdbanco`.`CuentaCreada`;*/

/*DROP PROCEDURE `bdbanco`.`Prestamos_x_Autorizar`;*/
delimiter //
  create procedure prestamos_x_autorizar
(
in nroCuenta int,
in importe_pedido DECIMAL(20,6),
in cantidad_cuotas INT
)
begin
     insert into prestamos_x_autorizar(`nroCuenta`, `fecha_creacion`, `importe_pedido`, `cantidad_cuotas`, `estado`) values (nroCuenta, current_date(),importe_pedido,cantidad_cuotas,'1');
 end //
   delimiter //;

  
