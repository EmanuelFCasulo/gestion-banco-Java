insert into usuarios(usuario, dni, tipoUsuario, clave, estado) 
	select 	nombre as usuario, 
			dni, 
            2 as tipoUsuario,
            'clave' as clave, 
            true as estado
	from clientes
;
    
UPDATE usuarios U
JOIN clientes C ON U.dni = C.dni
SET U.tipoUsuario = 1
WHERE C.dni = '40192839';

UPDATE usuarios U
JOIN clientes C ON U.dni = C.dni
SET U.usuario = 'admin'
WHERE C.dni = '40192839';


