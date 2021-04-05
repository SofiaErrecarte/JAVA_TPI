-- MySQL dump 10.13  Distrib 5.7.25, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	5.7.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cuota`
--

DROP TABLE IF EXISTS `cuota`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cuota` (
  `fecha` date NOT NULL,
  `idPersona` int(11) NOT NULL,
  `importe` float(9,3) DEFAULT NULL,
  `periodo` date NOT NULL,
  PRIMARY KEY (`fecha`,`idPersona`),
  KEY `fk_cuota_persona_idx` (`idPersona`),
  CONSTRAINT `fk_cuota_persona` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuota`
--

LOCK TABLES `cuota` WRITE;
/*!40000 ALTER TABLE `cuota` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuota` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ejemplar`
--

DROP TABLE IF EXISTS `ejemplar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ejemplar` (
  `idEjemplar` int(11) NOT NULL AUTO_INCREMENT,
  `idLibro` int(10) unsigned NOT NULL,
  `disponible` tinyint(4) NOT NULL,
  PRIMARY KEY (`idEjemplar`),
  KEY `fk_ejemplar_libro_idx` (`idLibro`),
  KEY `fk_ejemplar_lineaPrestamo_idx` (`disponible`),
  CONSTRAINT `fk_ejemplar_libro` FOREIGN KEY (`idLibro`) REFERENCES `libro` (`idLibro`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejemplar`
--

LOCK TABLES `ejemplar` WRITE;
/*!40000 ALTER TABLE `ejemplar` DISABLE KEYS */;
INSERT INTO `ejemplar` VALUES (1,17,0),(2,27,0),(8,17,0),(9,17,0),(10,17,0),(11,21,0),(12,21,1),(13,21,0);
/*!40000 ALTER TABLE `ejemplar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `libro` (
  `idLibro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) COLLATE utf8_bin NOT NULL,
  `isbn` int(11) NOT NULL,
  `fechaEdicion` date DEFAULT NULL,
  `nroEdicion` int(11) DEFAULT NULL,
  `cantDiasMaxPrestamo` int(11) DEFAULT NULL,
  `genero` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `imagen` blob,
  `idProveedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idLibro`),
  UNIQUE KEY `idLibro_UNIQUE` (`idLibro`),
  KEY `fk_libro_proveedor_idx` (`nroEdicion`),
  KEY `fk_libro_proveedor_idx1` (`idProveedor`),
  CONSTRAINT `fk_libro_proveedor` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`idProveedor`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (17,'prueba123',6,NULL,8,8,'iii',NULL,1),(21,'plis',77,NULL,7,5,'ttt',NULL,1),(27,'uuu',7,NULL,7,7,'uu',NULL,1);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linea_prestamo`
--

DROP TABLE IF EXISTS `linea_prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `linea_prestamo` (
  `idLineaPrestamo` int(11) NOT NULL AUTO_INCREMENT,
  `fechaDevolucion` date DEFAULT NULL,
  `devuelto` tinyint(4) DEFAULT NULL,
  `idPrestamo` int(11) DEFAULT NULL,
  `idEjemplar` int(11) NOT NULL,
  PRIMARY KEY (`idLineaPrestamo`),
  KEY `fk_lp_prestamo_idx` (`idPrestamo`),
  KEY `fk_lp_ejemplar_idx` (`idEjemplar`),
  CONSTRAINT `fk_lp_ejemplar` FOREIGN KEY (`idEjemplar`) REFERENCES `ejemplar` (`idEjemplar`) ON UPDATE CASCADE,
  CONSTRAINT `fk_lp_prestamo` FOREIGN KEY (`idPrestamo`) REFERENCES `prestamo` (`idPrestamo`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linea_prestamo`
--

LOCK TABLES `linea_prestamo` WRITE;
/*!40000 ALTER TABLE `linea_prestamo` DISABLE KEYS */;
INSERT INTO `linea_prestamo` VALUES (23,'2021-01-05',0,91,8),(24,'2021-01-30',0,92,10),(26,'2021-01-30',0,92,2),(27,NULL,0,91,9),(28,NULL,0,92,11),(29,NULL,0,92,13);
/*!40000 ALTER TABLE `linea_prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persona` (
  `apellido` varchar(45) COLLATE utf8_bin NOT NULL,
  `nombre` varchar(45) COLLATE utf8_bin NOT NULL,
  `telefono` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_bin NOT NULL,
  `direccion` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `dni` varchar(10) COLLATE utf8_bin NOT NULL,
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `montoAPagar` float(9,3) DEFAULT NULL,
  `admin` tinyint(4) NOT NULL DEFAULT '0',
  `contraseña` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`idPersona`),
  UNIQUE KEY `dni_UNIQUE` (`dni`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES ('erre','sof',NULL,'sofia',NULL,'111',1,NULL,1,'1234'),('bion','dol',NULL,'lola',NULL,'222',2,NULL,0,'1234');
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `politica_prestamo`
--

DROP TABLE IF EXISTS `politica_prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `politica_prestamo` (
  `cantMaximaSocio` int(11) NOT NULL,
  `cantMaximaNoSocio` varchar(45) COLLATE utf8_bin NOT NULL,
  `fechaAlta` date NOT NULL,
  `idPolitica` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idPolitica`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `politica_prestamo`
--

LOCK TABLES `politica_prestamo` WRITE;
/*!40000 ALTER TABLE `politica_prestamo` DISABLE KEYS */;
INSERT INTO `politica_prestamo` VALUES (4,'7','2020-09-04',1),(5,'2','2021-01-16',4),(8,'8','2021-01-29',7),(8,'7','2021-01-29',8),(8,'8','2021-01-29',9),(7,'7','2021-01-29',10),(2,'4','2021-04-02',11);
/*!40000 ALTER TABLE `politica_prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prestamo` (
  `idPrestamo` int(11) NOT NULL AUTO_INCREMENT,
  `fechaPrestamo` date DEFAULT NULL,
  `fechaADevolver` date DEFAULT NULL,
  `idPersona` int(11) DEFAULT NULL,
  `estado` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`idPrestamo`),
  UNIQUE KEY `idPrestamo_UNIQUE` (`idPrestamo`),
  KEY `fk_prestamo_persona_idx` (`idPersona`),
  CONSTRAINT `fk_prestamo_persona` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (91,'2020-01-23','2020-01-23',2,NULL),(92,'2020-01-03','2020-01-20',2,NULL);
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `cuit` varchar(12) COLLATE utf8_bin NOT NULL,
  `razonSocial` varchar(50) COLLATE utf8_bin NOT NULL,
  `telefono` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `direccion` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `idProveedor` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idProveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES ('13333','errr','3','e','e',1),('888','u','7','u','u',19),('88','i','8','i','i',20),('777','uu','7','u','u',28);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'biblioteca'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-05 10:39:37
