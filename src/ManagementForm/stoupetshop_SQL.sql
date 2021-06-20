-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 07, 2021 at 08:11 PM
-- Server version: 8.0.17
-- PHP Version: 7.3.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
--REATE Database stoupetshop;
--

-- --------------------------------------------------------

--
-- Table structure for table `camera`
--

CREATE TABLE `camera` (
  `CAM_NUM` varchar(5) NOT NULL,
  `CAM_STATUS` varchar(10) NOT NULL,
  `PET_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `CS_ID` int(11) NOT NULL,
  `CS_NAME` varchar(30) NOT NULL,
  `CARD_NUM` varchar(15) NOT NULL,
  `CS_ADDR` varchar(40) NOT NULL,
  `CS_PHONE` varchar(15) NOT NULL,
  `CS_MAIL` varchar(30) NOT NULL,
  `URGT_CONT` varchar(30) NOT NULL,
  `CS_USER` varchar(20) NOT NULL,
  `CS_PASSWORD` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CS_ID`, `CS_NAME`, `CARD_NUM`, `CS_ADDR`, `CS_PHONE`, `CS_MAIL`, `URGT_CONT`, `CS_USER`, `CS_PASSWORD`) VALUES
(1, 'Weerachai Nukitram', '1199999999999', 'ชลบุรี', '088-888-8888', 'i_oosa@good.com', '099-999-9999', 'weerachai', 'weerachai@1');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `EM_ID` int(11) NOT NULL,
  `EM_NAME` varchar(30) NOT NULL,
  `EM_POST` varchar(15) NOT NULL,
  `EM_CONT` varchar(40) NOT NULL,
  `EM_USER` varchar(20) NOT NULL,
  `EM_PASSWORD` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`EM_ID`, `EM_NAME`, `EM_POST`, `EM_CONT`, `EM_USER`, `EM_PASSWORD`) VALUES
(1, 'Surachai Sirisart', 'ผู้ดูแลสัตว์', '082-222-2222', 'Surachai', 'Surachai@1');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `INV_NUM` int(11) NOT NULL,
  `SERVPRO_NUM` int(11) NOT NULL,
  `INV_DATE` date NOT NULL,
  `INV_DUE` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `pet`
--

CREATE TABLE `pet` (
  `CS_ID` int(11) NOT NULL,
  `PET_ID` int(11) NOT NULL,
  `PET_NAME` varchar(10) NOT NULL,
  `PET_TYPE` varchar(10) NOT NULL,
  `PET_PHOTO` blob NOT NULL,
  `PET_NUM` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `petview`
--

CREATE TABLE `petview` (
  `PET_ID` int(11) NOT NULL,
  `PET_NUM` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `reservation_order`
--

CREATE TABLE `reservation_order` (
  `RESERV_NUM` int(11) NOT NULL,
  `SERV_CODE` int(11) NOT NULL,
  `CS_ID` int(11) NOT NULL,
  `CHECK_IN` int(11) NOT NULL,
  `CHECK_OUT` int(11) NOT NULL,
  `PET_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reservation_order`
--

INSERT INTO `reservation_order` (`RESERV_NUM`, `SERV_CODE`, `CS_ID`, `CHECK_IN`, `CHECK_OUT`, `PET_ID`) VALUES
(12, 1, 1, 7062021, 9062021, 15);

-- --------------------------------------------------------

--
-- Table structure for table `service_available`
--

CREATE TABLE `service_available` (
  `SERV_CODE` int(11) NOT NULL,
  `SERV_DESC` varchar(15) NOT NULL,
  `DAY_PRICE` int(11) NOT NULL,
  `DEPOSIT` int(11) NOT NULL,
  `ADD_PRICE` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service_available`
--

INSERT INTO `service_available` (`SERV_CODE`, `SERV_DESC`, `DAY_PRICE`, `DEPOSIT`, `ADD_PRICE`) VALUES
(1, 'ฝากสุนัข', 500, 1000, 0),
(2, 'ฝากแมว', 500, 1000, 0),
(3, 'ฝากนก', 400, 900, 0);

-- --------------------------------------------------------

--
-- Table structure for table `service_provided`
--

CREATE TABLE `service_provided` (
  `SERVPRO_NUM` int(11) NOT NULL,
  `RESERV_NUM` int(11) NOT NULL,
  `TOTAL_PRICE` int(11) NOT NULL,
  `TOTAL_DATE` int(11) NOT NULL,
  `TOTAL_CHARGE` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service_provided`
--

INSERT INTO `service_provided` (`SERVPRO_NUM`, `RESERV_NUM`, `TOTAL_PRICE`, `TOTAL_DATE`, `TOTAL_CHARGE`) VALUES
(1, 12, 1000, 2, 12),
(2, 12, 1000, 2, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `camera`
--
ALTER TABLE `camera`
  ADD PRIMARY KEY (`CAM_NUM`),
  ADD KEY `PET_ID` (`PET_ID`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CS_ID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`EM_ID`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`INV_NUM`),
  ADD KEY `SERVPRO_NUM` (`SERVPRO_NUM`);

--
-- Indexes for table `pet`
--
ALTER TABLE `pet`
  ADD PRIMARY KEY (`PET_ID`),
  ADD KEY `CS_ID` (`CS_ID`);

--
-- Indexes for table `reservation_order`
--
ALTER TABLE `reservation_order`
  ADD PRIMARY KEY (`RESERV_NUM`),
  ADD KEY `CS_ID` (`CS_ID`),
  ADD KEY `SERV_CODE` (`SERV_CODE`);

--
-- Indexes for table `service_available`
--
ALTER TABLE `service_available`
  ADD PRIMARY KEY (`SERV_CODE`);

--
-- Indexes for table `service_provided`
--
ALTER TABLE `service_provided`
  ADD PRIMARY KEY (`SERVPRO_NUM`),
  ADD KEY `RESERV_NUM` (`RESERV_NUM`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `camera`
--
ALTER TABLE `camera`
  ADD CONSTRAINT `camera_ibfk_1` FOREIGN KEY (`PET_ID`) REFERENCES `pet` (`PET_ID`);

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `SERVPRO_NUM` FOREIGN KEY (`SERVPRO_NUM`) REFERENCES `service_provided` (`SERVPRO_NUM`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `pet`
--
ALTER TABLE `pet`
  ADD CONSTRAINT `pet_ibfk_1` FOREIGN KEY (`CS_ID`) REFERENCES `customer` (`CS_ID`);

--
-- Constraints for table `reservation_order`
--
ALTER TABLE `reservation_order`
  ADD CONSTRAINT `CS_ID` FOREIGN KEY (`CS_ID`) REFERENCES `customer` (`CS_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `SERV_CODE` FOREIGN KEY (`SERV_CODE`) REFERENCES `service_available` (`SERV_CODE`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `service_provided`
--
ALTER TABLE `service_provided`
  ADD CONSTRAINT `RESERV_NUM` FOREIGN KEY (`RESERV_NUM`) REFERENCES `reservation_order` (`RESERV_NUM`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
