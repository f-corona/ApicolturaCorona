-- --------------------------------------------------------
-- Host:                         localhost
-- Versione server:              11.6.2-MariaDB - mariadb.org binary distribution
-- S.O. server:                  Win64
-- HeidiSQL Versione:            12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dump della struttura del database apicoltura_db
CREATE DATABASE IF NOT EXISTS `apicoltura_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci */;
USE `apicoltura_db`;

-- Dump della struttura di tabella apicoltura_db.categoria
CREATE TABLE IF NOT EXISTS `categoria` (
  `ID_Categoria` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_Categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dump dei dati della tabella apicoltura_db.categoria: ~2 rows (circa)
REPLACE INTO `categoria` (`ID_Categoria`, `Nome`) VALUES
	(1, 'Prodotti dell\'alveare'),
	(2, 'Materiale apistico');

-- Dump della struttura di tabella apicoltura_db.dettaglio_ordine
CREATE TABLE IF NOT EXISTS `dettaglio_ordine` (
  `ID_Ordine` int(11) NOT NULL,
  `ID_Prodotto` int(11) NOT NULL,
  `QuantitaAcquistata` int(11) NOT NULL,
  `PrezzoAcquisto` decimal(10,2) NOT NULL,
  PRIMARY KEY (`ID_Ordine`,`ID_Prodotto`),
  KEY `ID_Prodotto` (`ID_Prodotto`),
  CONSTRAINT `dettaglio_ordine_ibfk_1` FOREIGN KEY (`ID_Ordine`) REFERENCES `ordine` (`ID_Ordine`) ON DELETE CASCADE,
  CONSTRAINT `dettaglio_ordine_ibfk_2` FOREIGN KEY (`ID_Prodotto`) REFERENCES `prodotto` (`ID_Prodotto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dump dei dati della tabella apicoltura_db.dettaglio_ordine: ~8 rows (circa)
REPLACE INTO `dettaglio_ordine` (`ID_Ordine`, `ID_Prodotto`, `QuantitaAcquistata`, `PrezzoAcquisto`) VALUES
	(3, 1, 1, 3.85),
	(3, 3, 1, 11.55),
	(4, 2, 1, 6.05),
	(5, 2, 1, 6.05),
	(6, 2, 1, 6.05),
	(6, 3, 3, 11.55),
	(7, 4, 1, 3.85),
	(7, 7, 1, 10.98);

-- Dump della struttura di tabella apicoltura_db.ordine
CREATE TABLE IF NOT EXISTS `ordine` (
  `ID_Ordine` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Utente` int(11) NOT NULL,
  `DataOrdine` datetime NOT NULL,
  `Stato` varchar(50) NOT NULL,
  `Totale` decimal(10,2) NOT NULL,
  PRIMARY KEY (`ID_Ordine`),
  KEY `ID_Utente` (`ID_Utente`),
  CONSTRAINT `ordine_ibfk_1` FOREIGN KEY (`ID_Utente`) REFERENCES `utente` (`ID_Utente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dump dei dati della tabella apicoltura_db.ordine: ~5 rows (circa)
REPLACE INTO `ordine` (`ID_Ordine`, `ID_Utente`, `DataOrdine`, `Stato`, `Totale`) VALUES
	(3, 3, '2025-09-05 09:13:10', 'Confermato', 15.40),
	(4, 3, '2025-09-15 15:00:13', 'Confermato', 6.05),
	(5, 1, '2025-09-15 15:07:15', 'Confermato', 6.05),
	(6, 3, '2025-09-22 10:53:23', 'Confermato', 40.70),
	(7, 3, '2025-09-22 10:54:27', 'Confermato', 14.83);

-- Dump della struttura di tabella apicoltura_db.prodotto
CREATE TABLE IF NOT EXISTS `prodotto` (
  `ID_Prodotto` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(255) NOT NULL,
  `Descrizione` text DEFAULT NULL,
  `Prezzo` decimal(10,2) NOT NULL,
  `IVA` decimal(10,2) NOT NULL,
  `QuantitaDisponibile` int(11) NOT NULL,
  `Cancellato` tinyint(1) NOT NULL DEFAULT 0,
  `ImmagineURL` varchar(255) DEFAULT NULL,
  `ID_Categoria` int(11) NOT NULL,
  PRIMARY KEY (`ID_Prodotto`),
  KEY `ID_Categoria` (`ID_Categoria`),
  CONSTRAINT `prodotto_ibfk_1` FOREIGN KEY (`ID_Categoria`) REFERENCES `categoria` (`ID_Categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dump dei dati della tabella apicoltura_db.prodotto: ~7 rows (circa)
REPLACE INTO `prodotto` (`ID_Prodotto`, `Nome`, `Descrizione`, `Prezzo`, `IVA`, `QuantitaDisponibile`, `Cancellato`, `ImmagineURL`, `ID_Categoria`) VALUES
	(1, 'Miele Millefiori di Primavera 250g', 'Ottimo miele', 3.50, 10.00, 199, 0, 'images/products/miele_primavera_250.jpg', 1),
	(2, 'Miele Millefiori di Primavera 500g', 'Miele', 5.50, 10.00, 197, 0, 'images/products/miele_primavera_500.jpg', 1),
	(3, 'Miele Millefiori di Primavera 1000g', 'Miele', 10.50, 10.00, 196, 0, 'images/products/miele_primavera_1000.jpg', 1),
	(4, 'Miele Millefiori di Estate 250g', 'Miele', 3.50, 10.00, 199, 0, 'images/products/miele_estate_250.jpg', 1),
	(5, 'Miele Millefiori di Estate 500g', 'Miele', 5.50, 10.00, 200, 0, 'images/products/miele_estate_500.jpg', 1),
	(6, 'Miele Millefiori di Estate 1000g', 'Miele', 10.50, 10.00, 200, 0, 'images/products/miele_estate_1000.jpg', 1),
	(7, 'Affumicatore in acciaio', 'Classico affumicatore ma con struttura e fornace in acciaio e gabbia di protezione.\r\n', 9.00, 22.00, 29, 0, 'images/products/affumicatore.png', 2);

-- Dump della struttura di tabella apicoltura_db.utente
CREATE TABLE IF NOT EXISTS `utente` (
  `ID_Utente` int(11) NOT NULL AUTO_INCREMENT,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Cognome` varchar(50) NOT NULL,
  `Telefono` varchar(20) DEFAULT NULL,
  `IndirizzoSpedizione` varchar(255) NOT NULL,
  `CittaSpedizione` varchar(100) NOT NULL,
  `CAPSpedizione` varchar(5) NOT NULL,
  `ProvinciaSpedizione` varchar(2) NOT NULL,
  `IsAdmin` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID_Utente`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- Dump dei dati della tabella apicoltura_db.utente: ~3 rows (circa)
REPLACE INTO `utente` (`ID_Utente`, `Email`, `Password`, `Nome`, `Cognome`, `Telefono`, `IndirizzoSpedizione`, `CittaSpedizione`, `CAPSpedizione`, `ProvinciaSpedizione`, `IsAdmin`) VALUES
	(1, 'admin@apicolturacorona.it', '89a174917c5bedc27e1b44f0cd53e1764017583ee24b82617a1ad113f7e98a3f8581ed4403eb2acf0995b47de2a1d31d06e7e2ccdf6f051472358ae8d0747ec9', 'Francesco', 'Corona', '', 'Via della Laurea', 'Fisciano', '00000', 'SA', 1),
	(2, 'david@parenzo.it', 'f6611c6a99d125329f1c07142ea79750d7b7fcb95e3d1e6d55dbd57aaa3651224ffc0f04322d0183f0c61acbbb4a7e3701ee39c1e763ad9aa15e408f3c867813', 'David1', 'Parenzo', '', 'Via della Zanzara', 'Valva', '84020', 'SA', 0),
	(3, 'franco@unisa.it', 'fde0bf645471669a4d2bf54f94c527f0e8a35b9cbf9e0ac92f3632eeb9200db7dd56d4466f4881666824285ab105145bb2dba6fecd612a08029095ade18e20b3', 'Franco', 'Unisa', '', 'Via dei CFU', 'Valva', '84020', 'SA', 0);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
