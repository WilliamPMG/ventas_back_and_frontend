INSERT INTO `clientes` (`id`, `apellido`, `nombre`, `telefono`) values (null, 'Rodriguez', 'Giorman', '3152485896');
INSERT INTO `clientes` (`id`, `apellido`, `nombre`, `telefono`) values (null, 'Ramirez', 'Antonio', '1111111111');

INSERT INTO `cuentas` (`dia`, `nombre`, `precio`) VALUES ('30', 'Netflix', '10000');
INSERT INTO `cuentas` (`dia`, `nombre`, `precio`) VALUES ('60', 'Amazon', '5000');

INSERT INTO `suscripciones` (`correo`, `estado`, `fecha_final`, `fecha_inicio`, `password`, `perfil`, `pin`, `precio`, `proveedor`, `cliente_id`, `cuenta_id`) VALUES ('prueba2@gmail.com', '1', '2022-10-15', '2022-09-15', '22222222', 'perfil2', '8458', '6000', 'Mi Entretenimiento', '1', '1');
INSERT INTO `suscripciones` (`correo`, `estado`, `fecha_final`, `fecha_inicio`, `password`, `perfil`, `pin`, `precio`, `proveedor`, `cliente_id`, `cuenta_id`) VALUES ('prueba3@gmail.com', '1', '2022-10-15', '2022-09-15', '22222222', 'perfil2', '8458', '6000', 'Mi Entretenimiento', '2', '1');

INSERT INTO `ganancias` (`id`, `mes`, `valor`) VALUES
	(1, 'Enero', 0),
	(2, 'Febrero', 0),
	(3, 'Marzo', 0),
	(4, 'Abril', 0),
	(5, 'Mayo', 0),
	(6, 'Junio', 0),
	(7, 'Julio', 0),
	(8, 'Agosto', 0),
	(9, 'Septiembre', 0),
	(10, 'Octubre', 0),
	(11, 'Noviembre', 0),
	(12, 'Diciembre', 0);