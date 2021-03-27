-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuota` (
  `fecha` date NOT NULL,
  `idPersona` int NOT NULL,
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ejemplar` (
  `idEjemplar` int NOT NULL AUTO_INCREMENT,
  `idLibro` int unsigned NOT NULL,
  `disponible` tinyint NOT NULL,
  PRIMARY KEY (`idEjemplar`),
  KEY `fk_ejemplar_libro_idx` (`idLibro`),
  KEY `fk_ejemplar_lineaPrestamo_idx` (`disponible`),
  CONSTRAINT `fk_ejemplar_libro` FOREIGN KEY (`idLibro`) REFERENCES `libro` (`idLibro`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejemplar`
--

LOCK TABLES `ejemplar` WRITE;
/*!40000 ALTER TABLE `ejemplar` DISABLE KEYS */;
INSERT INTO `ejemplar` VALUES (1,17,1),(2,17,1),(3,18,0),(4,17,1),(5,17,1),(6,17,1),(7,17,1);
/*!40000 ALTER TABLE `ejemplar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `idLibro` int unsigned NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `isbn` int NOT NULL,
  `fechaEdicion` date DEFAULT NULL,
  `nroEdicion` int DEFAULT NULL,
  `cantDiasMaxPrestamo` int DEFAULT NULL,
  `genero` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `imagen` blob,
  `idProveedor` int DEFAULT NULL,
  PRIMARY KEY (`idLibro`),
  UNIQUE KEY `idLibro_UNIQUE` (`idLibro`),
  KEY `fk_libro_proveedor_idx` (`nroEdicion`),
  KEY `fk_libro_proveedor_idx1` (`idProveedor`),
  CONSTRAINT `fk_libro_proveedor` FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`idProveedor`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (17,'prueba123',6,NULL,8,8,'iii',NULL,1),(18,'uuuu',6,NULL,6,6,'yyyy',NULL,1),(21,'plis',77,NULL,5,5,'ttt',NULL,1),(23,'porfavor',666,NULL,6,6,'uiui',NULL,6),(26,'ttt',6,NULL,7,7,'eee',NULL,1),(27,'uuu',7,NULL,7,7,'uu',NULL,1),(28,'yyuy',0,NULL,7,7,'uu',NULL,1),(29,'iuiu',20,NULL,7,7,'yyy',NULL,1),(30,'uuu',78,NULL,7,7,'uiui',NULL,1),(31,'uu',100,NULL,8,8,'uu',NULL,1),(32,'uu',101,NULL,9,9,'ii',NULL,1),(33,'uu',102,NULL,9,9,'ii',NULL,1),(35,'tt',5,NULL,5,5,'tt',NULL,1),(39,'oioio',1,NULL,8,8,'ii',NULL,1),(40,'uu',11,NULL,7,8,'u',NULL,1),(41,'uu',1234,NULL,7,7,'uu',NULL,1),(42,'uuu',456,NULL,7,7,'uu',NULL,1);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `linea_prestamo`
--

DROP TABLE IF EXISTS `linea_prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `linea_prestamo` (
  `idLineaPrestamo` int NOT NULL AUTO_INCREMENT,
  `fechaDevolucion` date DEFAULT NULL,
  `devuelto` tinyint DEFAULT NULL,
  `idPrestamo` int DEFAULT NULL,
  `idEjemplar` int NOT NULL,
  PRIMARY KEY (`idLineaPrestamo`),
  KEY `fk_lp_prestamo_idx` (`idPrestamo`),
  KEY `fk_lp_ejemplar_idx` (`idEjemplar`),
  CONSTRAINT `fk_lp_ejemplar` FOREIGN KEY (`idEjemplar`) REFERENCES `ejemplar` (`idEjemplar`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_lp_prestamo` FOREIGN KEY (`idPrestamo`) REFERENCES `prestamo` (`idPrestamo`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `linea_prestamo`
--

LOCK TABLES `linea_prestamo` WRITE;
/*!40000 ALTER TABLE `linea_prestamo` DISABLE KEYS */;
INSERT INTO `linea_prestamo` VALUES (1,NULL,0,79,1),(9,'2020-01-22',0,87,1);
/*!40000 ALTER TABLE `linea_prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `apellido` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `nombre` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `telefono` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `direccion` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `dni` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `idPersona` int NOT NULL AUTO_INCREMENT,
  `montoAPagar` float(9,3) DEFAULT NULL,
  `admin` tinyint NOT NULL DEFAULT '0',
  `contraseña` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
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
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `politica_prestamo` (
  `cantMaximaSocio` int NOT NULL,
  `cantMaximaNoSocio` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `fechaAlta` date NOT NULL,
  `idPolitica` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idPolitica`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `politica_prestamo`
--

LOCK TABLES `politica_prestamo` WRITE;
/*!40000 ALTER TABLE `politica_prestamo` DISABLE KEYS */;
INSERT INTO `politica_prestamo` VALUES (9,'9','2020-09-04',1),(3,'4','2020-09-07',2),(10,'5','2020-09-07',3),(5,'2','2021-01-16',4),(5,'2','2021-01-13',5),(6,'5','2022-01-25',6);
/*!40000 ALTER TABLE `politica_prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `idPrestamo` int NOT NULL AUTO_INCREMENT,
  `fechaHoraPrestamo` datetime DEFAULT NULL,
  `fechaADevolver` date DEFAULT NULL,
  `idPersona` int DEFAULT NULL,
  PRIMARY KEY (`idPrestamo`),
  UNIQUE KEY `idPrestamo_UNIQUE` (`idPrestamo`),
  KEY `fk_prestamo_persona_idx` (`idPersona`),
  CONSTRAINT `fk_prestamo_persona` FOREIGN KEY (`idPersona`) REFERENCES `persona` (`idPersona`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (1,NULL,NULL,NULL),(7,NULL,NULL,1),(15,NULL,NULL,1),(20,NULL,NULL,1),(21,NULL,NULL,1),(22,NULL,NULL,1),(23,NULL,NULL,1),(24,NULL,NULL,1),(25,NULL,NULL,1),(26,NULL,NULL,1),(27,NULL,NULL,1),(28,NULL,NULL,1),(29,NULL,NULL,1),(30,NULL,NULL,1),(31,NULL,NULL,1),(32,NULL,NULL,1),(33,NULL,NULL,1),(34,NULL,NULL,1),(35,NULL,NULL,1),(36,NULL,NULL,1),(37,NULL,NULL,1),(38,NULL,NULL,1),(39,NULL,NULL,1),(40,NULL,NULL,1),(41,NULL,NULL,1),(42,NULL,NULL,1),(43,NULL,NULL,1),(44,NULL,NULL,1),(45,NULL,NULL,1),(46,NULL,NULL,1),(47,NULL,NULL,1),(48,NULL,NULL,1),(49,NULL,NULL,1),(50,NULL,NULL,1),(51,NULL,NULL,1),(52,NULL,NULL,1),(53,NULL,NULL,1),(54,NULL,NULL,1),(55,NULL,NULL,1),(56,NULL,NULL,1),(57,NULL,NULL,1),(58,NULL,NULL,1),(59,NULL,NULL,1),(60,NULL,NULL,1),(61,NULL,NULL,1),(62,NULL,NULL,1),(63,NULL,NULL,1),(64,NULL,NULL,1),(65,NULL,NULL,1),(66,NULL,NULL,1),(67,NULL,NULL,1),(68,NULL,NULL,1),(69,NULL,NULL,1),(70,NULL,NULL,1),(71,NULL,NULL,1),(72,NULL,NULL,1),(73,NULL,NULL,1),(74,NULL,NULL,1),(75,NULL,NULL,1),(76,NULL,NULL,1),(77,NULL,NULL,1),(78,NULL,NULL,1),(79,NULL,NULL,1),(80,NULL,NULL,1),(81,'2020-01-14 00:00:00','2020-01-28',1),(82,'2020-01-22 00:00:00','2020-01-31',1),(83,'2020-01-22 00:00:00','2020-01-22',1),(84,'2020-01-22 00:00:00','2020-01-22',1),(85,'2020-01-22 00:00:00','2020-01-22',1),(86,'2020-01-22 00:00:00','2020-01-22',1),(87,'2020-01-22 00:00:00','2020-01-22',1);
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `cuit` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `razonSocial` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `telefono` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `direccion` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `idProveedor` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idProveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES ('13333','e','3','e','e',1),('13333','yyy','97979','lolaa','gugugrequiredrequiredrequired',6),('99-99','y','7','y','yyrequired',9),('7444','uu','78','uuu','uurequired',10),('111','eee','333','eee','eee',12);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-27 19:57:46
