-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 07. Apr 2015 um 13:59
-- Server Version: 5.6.21
-- PHP-Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `online_shop`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `auftrag`
--

CREATE TABLE IF NOT EXISTS `auftrag` (
`AuftragNr` int(4) NOT NULL,
  `UserID` int(4) NOT NULL,
  `BestellID` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `besteht_aus`
--

CREATE TABLE IF NOT EXISTS `besteht_aus` (
  `BestellID` int(4) NOT NULL,
  `ProduktID` int(4) NOT NULL,
  `Menge` varchar(3) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bestellung`
--

CREATE TABLE IF NOT EXISTS `bestellung` (
`BestellID` int(4) NOT NULL,
  `Datum` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `produkt`
--

CREATE TABLE IF NOT EXISTS `produkt` (
`ProduktID` int(4) NOT NULL,
  `Bezeichnung` text COLLATE utf8_bin NOT NULL,
  `Kategorie` text COLLATE utf8_bin NOT NULL,
  `Artikebestand` varchar(3) COLLATE utf8_bin NOT NULL,
  `Dateipfad` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`UserID` int(4) NOT NULL,
  `Vorname` text COLLATE utf8_bin NOT NULL,
  `Nachname` text COLLATE utf8_bin NOT NULL,
  `UserTyp` text COLLATE utf8_bin NOT NULL,
  `Ort` varchar(20) COLLATE utf8_bin NOT NULL,
  `PLZ` varchar(5) COLLATE utf8_bin NOT NULL,
  `Strasse` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `auftrag`
--
ALTER TABLE `auftrag`
 ADD PRIMARY KEY (`AuftragNr`), ADD UNIQUE KEY `User_ID` (`UserID`) COMMENT 'user', ADD UNIQUE KEY `Bestell_ID` (`BestellID`) COMMENT 'Bestell';

--
-- Indizes für die Tabelle `besteht_aus`
--
ALTER TABLE `besteht_aus`
 ADD PRIMARY KEY (`BestellID`,`ProduktID`), ADD KEY `ProduktID` (`ProduktID`);

--
-- Indizes für die Tabelle `bestellung`
--
ALTER TABLE `bestellung`
 ADD PRIMARY KEY (`BestellID`);

--
-- Indizes für die Tabelle `produkt`
--
ALTER TABLE `produkt`
 ADD PRIMARY KEY (`ProduktID`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`UserID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `auftrag`
--
ALTER TABLE `auftrag`
MODIFY `AuftragNr` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `bestellung`
--
ALTER TABLE `bestellung`
MODIFY `BestellID` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `produkt`
--
ALTER TABLE `produkt`
MODIFY `ProduktID` int(4) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
MODIFY `UserID` int(4) NOT NULL AUTO_INCREMENT;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `auftrag`
--
ALTER TABLE `auftrag`
ADD CONSTRAINT `UserID` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`);
ADD CONSTRAINT `BestellID` FOREIGN KEY (`BestellID`) REFERENCES `user` (`BestellID`);


--
-- Constraints der Tabelle `besteht_aus`
--
ALTER TABLE `besteht_aus`
ADD CONSTRAINT `BestellID` FOREIGN KEY (`BestellID`) REFERENCES `bestellung` (`BestellID`),
ADD CONSTRAINT `ProduktID` FOREIGN KEY (`ProduktID`) REFERENCES `produkt` (`ProduktID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
