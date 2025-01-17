-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.17-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping database structure for combo_digital
CREATE DATABASE IF NOT EXISTS `combo_digital`;
USE `combo_digital`;

-- Dumping structure for table combo_digital.clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `nombre` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `telefono` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table combo_digital.clientes: ~0 rows (approximately)
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;

-- Dumping structure for table combo_digital.cuentas
CREATE TABLE IF NOT EXISTS `cuentas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `dia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table combo_digital.cuentas: ~0 rows (approximately)
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;

-- Dumping structure for table combo_digital.ganancias
CREATE TABLE IF NOT EXISTS `ganancias` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mes` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table combo_digital.ganancias: ~12 rows (approximately)
/*!40000 ALTER TABLE `ganancias` DISABLE KEYS */;

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

/*!40000 ALTER TABLE `ganancias` ENABLE KEYS */;

-- Dumping structure for table combo_digital.suscripciones
CREATE TABLE IF NOT EXISTS `suscripciones` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `correo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_final` date DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `perfil` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `pin` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `precio` int(11) DEFAULT NULL,
  `proveedor` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `cuenta_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK25b48nosx6eu5tvobpqk706i7` (`cliente_id`),
  KEY `FK1efev08rujjx2sm7yo69upkgy` (`cuenta_id`),
  CONSTRAINT `FK1efev08rujjx2sm7yo69upkgy` FOREIGN KEY (`cuenta_id`) REFERENCES `cuentas` (`id`),
  CONSTRAINT `FK25b48nosx6eu5tvobpqk706i7` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Dumping data for table combo_digital.suscripciones: ~0 rows (approximately)
/*!40000 ALTER TABLE `suscripciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `suscripciones` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
