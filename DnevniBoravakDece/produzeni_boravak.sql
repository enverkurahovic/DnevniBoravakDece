-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2026 at 07:56 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `produzeni_boravak`
--

-- --------------------------------------------------------

--
-- Table structure for table `aktivnosti`
--

CREATE TABLE `aktivnosti` (
  `AktivnostID` int(11) NOT NULL,
  `NazivAktivnosti` varchar(50) DEFAULT NULL,
  `Dan` varchar(20) DEFAULT NULL,
  `Pocetak` time DEFAULT NULL,
  `Zavrsetak` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `aktivnosti`
--

INSERT INTO `aktivnosti` (`AktivnostID`, `NazivAktivnosti`, `Dan`, `Pocetak`, `Zavrsetak`) VALUES
(1, 'Sport', 'Sreda', '12:00:00', '14:00:00'),
(2, 'Engleski', 'Sreda', '09:00:00', '11:00:00'),
(3, 'Gluma', 'Sreda', '15:00:00', '17:00:00'),
(4, 'Matematika', 'Petak', '11:00:00', '13:00:00'),
(5, 'Nemacki', 'Ponedeljak', '13:00:00', '14:30:00'),
(6, 'Sport', 'Utorak', '11:00:00', '12:30:00'),
(8, 'Engleski', 'Ponedeljak', '11:30:00', '12:30:00'),
(9, 'Sport', 'Sreda', '12:30:00', '13:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `dete`
--

CREATE TABLE `dete` (
  `DeteID` int(11) NOT NULL,
  `PolID` int(11) DEFAULT NULL,
  `Ime` varchar(50) DEFAULT NULL,
  `Prezime` varchar(50) DEFAULT NULL,
  `DatumRodjenja` date DEFAULT NULL,
  `Beleske` text DEFAULT NULL,
  `RoditeljID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `dete`
--

INSERT INTO `dete` (`DeteID`, `PolID`, `Ime`, `Prezime`, `DatumRodjenja`, `Beleske`, `RoditeljID`) VALUES
(26, 1, 'Marko', 'Markovic', '2002-03-15', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `pol`
--

CREATE TABLE `pol` (
  `PolID` int(11) NOT NULL,
  `Pol` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pol`
--

INSERT INTO `pol` (`PolID`, `Pol`) VALUES
(1, 'Muski'),
(2, 'Zenski');

-- --------------------------------------------------------

--
-- Table structure for table `registar_aktivnosti`
--

CREATE TABLE `registar_aktivnosti` (
  `AktivnostID` int(11) NOT NULL,
  `DeteID` int(11) NOT NULL,
  `Datum` date DEFAULT NULL,
  `Prisustvo` tinyint(1) DEFAULT NULL,
  `Beleska` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roditelji`
--

CREATE TABLE `roditelji` (
  `RoditeljID` int(11) NOT NULL,
  `SvojstvoID` int(11) DEFAULT NULL,
  `Ime` varchar(50) DEFAULT NULL,
  `Prezime` varchar(50) DEFAULT NULL,
  `Adresa` varchar(100) DEFAULT NULL,
  `FiksniTelefon` varchar(20) DEFAULT NULL,
  `MobilniTelefon` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roditelji`
--

INSERT INTO `roditelji` (`RoditeljID`, `SvojstvoID`, `Ime`, `Prezime`, `Adresa`, `FiksniTelefon`, `MobilniTelefon`) VALUES
(1, 1, 'Milan', 'Markovic', 'Beograd', '011123456', ' 0611234567');

-- --------------------------------------------------------

--
-- Table structure for table `svojstvo_roditelja`
--

CREATE TABLE `svojstvo_roditelja` (
  `SvojstvoID` int(11) NOT NULL,
  `Svojstvo` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `svojstvo_roditelja`
--

INSERT INTO `svojstvo_roditelja` (`SvojstvoID`, `Svojstvo`) VALUES
(1, 'Otac'),
(2, 'Majka');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `aktivnosti`
--
ALTER TABLE `aktivnosti`
  ADD PRIMARY KEY (`AktivnostID`);

--
-- Indexes for table `dete`
--
ALTER TABLE `dete`
  ADD PRIMARY KEY (`DeteID`),
  ADD KEY `PolID` (`PolID`),
  ADD KEY `RoditeljID` (`RoditeljID`);

--
-- Indexes for table `pol`
--
ALTER TABLE `pol`
  ADD PRIMARY KEY (`PolID`);

--
-- Indexes for table `registar_aktivnosti`
--
ALTER TABLE `registar_aktivnosti`
  ADD PRIMARY KEY (`AktivnostID`,`DeteID`),
  ADD KEY `DeteID` (`DeteID`);

--
-- Indexes for table `roditelji`
--
ALTER TABLE `roditelji`
  ADD PRIMARY KEY (`RoditeljID`),
  ADD KEY `SvojstvoID` (`SvojstvoID`);

--
-- Indexes for table `svojstvo_roditelja`
--
ALTER TABLE `svojstvo_roditelja`
  ADD PRIMARY KEY (`SvojstvoID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `aktivnosti`
--
ALTER TABLE `aktivnosti`
  MODIFY `AktivnostID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `dete`
--
ALTER TABLE `dete`
  MODIFY `DeteID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT for table `pol`
--
ALTER TABLE `pol`
  MODIFY `PolID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `roditelji`
--
ALTER TABLE `roditelji`
  MODIFY `RoditeljID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `svojstvo_roditelja`
--
ALTER TABLE `svojstvo_roditelja`
  MODIFY `SvojstvoID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dete`
--
ALTER TABLE `dete`
  ADD CONSTRAINT `dete_ibfk_1` FOREIGN KEY (`PolID`) REFERENCES `pol` (`PolID`),
  ADD CONSTRAINT `dete_ibfk_2` FOREIGN KEY (`RoditeljID`) REFERENCES `roditelji` (`RoditeljID`);

--
-- Constraints for table `registar_aktivnosti`
--
ALTER TABLE `registar_aktivnosti`
  ADD CONSTRAINT `registar_aktivnosti_ibfk_1` FOREIGN KEY (`AktivnostID`) REFERENCES `aktivnosti` (`AktivnostID`),
  ADD CONSTRAINT `registar_aktivnosti_ibfk_2` FOREIGN KEY (`DeteID`) REFERENCES `dete` (`DeteID`);

--
-- Constraints for table `roditelji`
--
ALTER TABLE `roditelji`
  ADD CONSTRAINT `roditelji_ibfk_1` FOREIGN KEY (`SvojstvoID`) REFERENCES `svojstvo_roditelja` (`SvojstvoID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
