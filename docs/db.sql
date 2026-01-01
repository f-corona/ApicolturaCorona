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

-- Dump dei dati della tabella apicoltura_db.categoria: dati omessi per sicurezza

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

-- Dump dei dati della tabella apicoltura_db.dettaglio_ordine: dati omessi per sicurezza

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

-- Dump dei dati della tabella apicoltura_db.ordine: dati omessi per sicurezza

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

-- Dump dei dati della tabella apicoltura_db.prodotto: dati omessi per sicurezza

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

-- Dump dei dati della tabella apicoltura_db.utente: dati omessi per sicurezza

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
