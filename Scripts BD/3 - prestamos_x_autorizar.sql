/*drop table Prestamos_x_Autorizar*/
create table Prestamos_x_Autorizar
(
codPrestamoPendinte INT NOT NULL AUTO_INCREMENT,
nroCuenta int NOT NULL,
fecha_creacion date NOT NULL,
importe_pedido DECIMAL(20,6) NOT NULL,
cantidad_cuotas INT NOT NULL ,
estado tinyint NOT NULL,
primary key (codPrestamoPendinte),
foreign key (nroCuenta) references cuentas(nroCuenta) 
);

/*DROP PROCEDURE agregarPrestamoxAutorizar*/
delimiter //
  create procedure agregarPrestamoxAutorizar
(
in nroCuenta int,
in importe_pedido DECIMAL(20,6),
in cantidad_cuotas INT
)
begin
     insert into prestamos_x_autorizar(`nroCuenta`, `fecha_creacion`, `importe_pedido`, `cantidad_cuotas`, `estado`) values (nroCuenta, current_date(),importe_pedido,cantidad_cuotas,'1');
 end //
   delimiter //;