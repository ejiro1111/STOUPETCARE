-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 10, 2021 at 01:46 PM
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
-- Database: `stoupetshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `camera`
--

CREATE TABLE `camera` (
  `CAM_NUM` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CAM_STATUS` varchar(10) NOT NULL,
  `PET_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `camera`
--

INSERT INTO `camera` (`CAM_NUM`, `CAM_STATUS`, `PET_ID`) VALUES
('CAM_01', 'Enable', 1),
('CAM_02', 'Enable', 2),
('CAM_03', ' Disable', NULL),
('CAM_04', ' Disable', NULL),
('CAM_05', ' Disable', NULL);

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
  `URGT_CONT` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CS_ID`, `CS_NAME`, `CARD_NUM`, `CS_ADDR`, `CS_PHONE`, `CS_MAIL`, `URGT_CONT`) VALUES
(1, 'สุรชาติ สอนระเบียบดี', '111-11111-11111', 'ชลบุรี', '092-232-3488', 'Surachat@test.com', '099-999-9999'),
(2, 'มธุรส สายทอง', '111-11111-11112', 'กรุงเทพ', '092-332-3456', 'Mathurot@test.com', '089-988-8273'),
(3, 'กุญชร มากผู้ดี', '111-11111-11113', 'กรุงเทพ', '028-345-332', 'Kunchon@test.com', '099-283-4587');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `EM_ID` int(11) NOT NULL,
  `EM_NAME` varchar(30) NOT NULL,
  `EM_POST` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `EM_CONT` varchar(40) NOT NULL,
  `EM_USER` varchar(20) NOT NULL,
  `EM_PASSWORD` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`EM_ID`, `EM_NAME`, `EM_POST`, `EM_CONT`, `EM_USER`, `EM_PASSWORD`) VALUES
(1, 'Surachai Sirisart', 'ผู้ดูแลสัตว์', '082-222-2222', 'Surachai', 'Surachai@1'),
(2, 'Adisorn Bonsong', 'ผู้ดูแลสัตว์', '089-234-5678', 'Adisorn', 'Adisorn@2'),
(3, 'Passkorn Roungrong', 'สัตวแพทย์', '099-634-5448', 'Passkorn', 'Passkorn@3'),
(4, 'Chalee Angel', 'พนักงานบัญชี', '093-222-5679', 'Chalee', 'Chalee@4'),
(5, 'Suphakit noppornchai', 'ฝ่ายไอที', '089-234-8564', 'Suphakit', 'Suphakit@5'),
(6, 'Weerachai Nukitram', 'ผู้ช่วยสัตว์แพทย์', '082-993-4564', 'Weerachai', 'Weerachai@6');

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

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`INV_NUM`, `SERVPRO_NUM`, `INV_DATE`, `INV_DUE`) VALUES
(1, 1, '2021-06-11', '2021-06-14'),
(2, 2, '2021-06-18', '2021-06-21'),
(3, 3, '2021-06-17', '2021-06-21');

-- --------------------------------------------------------

--
-- Table structure for table `pet`
--

CREATE TABLE `pet` (
  `CS_ID` int(11) NOT NULL,
  `PET_ID` int(11) NOT NULL,
  `PET_NAME` varchar(10) NOT NULL,
  `PET_TYPE` varchar(20) NOT NULL,
  `PET_PHOTO` mediumblob NOT NULL,
  `PET_NUM` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pet`
--

INSERT INTO `pet` (`CS_ID`, `PET_ID`, `PET_NAME`, `PET_TYPE`, `PET_PHOTO`, `PET_NUM`) VALUES
(1, 1, 'Bo', 'สุนัข', '', NULL),
(2, 2, 'Sunny', 'กระต่าย', '', NULL),
(3, 3, 'Thon', 'แมว', '', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `petview`
--

CREATE TABLE `petview` (
  `PET_ID` int(11) NOT NULL,
  `PET_NUM` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `petview`
--

INSERT INTO `petview` (`PET_ID`, `PET_NUM`) VALUES
(1, '1'),
(2, '2'),
(3, '3');

-- --------------------------------------------------------

--
-- Table structure for table `reservation_order`
--

CREATE TABLE `reservation_order` (
  `RESERV_NUM` int(11) NOT NULL,
  `SERV_CODE` int(11) NOT NULL,
  `CS_ID` int(11) NOT NULL,
  `CHECK_IN` date NOT NULL,
  `CHECK_OUT` date NOT NULL,
  `PET_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reservation_order`
--

INSERT INTO `reservation_order` (`RESERV_NUM`, `SERV_CODE`, `CS_ID`, `CHECK_IN`, `CHECK_OUT`, `PET_ID`) VALUES
(1, 1, 1, '2021-06-11', '2021-06-13', 1),
(2, 3, 2, '2021-06-16', '2021-06-18', 2),
(3, 2, 3, '2021-06-15', '2021-06-17', 3);

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
(1, 'สุนัข', 500, 250, 100),
(2, 'แมว', 400, 200, 100),
(3, 'กระต่าย', 300, 150, 100);

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
(1, 1, 1800, 3, 1550),
(2, 2, 1200, 3, 1050),
(3, 2, 1500, 3, 1300);

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
-- Indexes for table `petview`
--
ALTER TABLE `petview`
  ADD KEY `PET_ID` (`PET_ID`);

--
-- Indexes for table `reservation_order`
--
ALTER TABLE `reservation_order`
  ADD PRIMARY KEY (`RESERV_NUM`),
  ADD KEY `CS_ID` (`CS_ID`),
  ADD KEY `SERV_CODE` (`SERV_CODE`),
  ADD KEY `PET_ID` (`PET_ID`);

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
  ADD CONSTRAINT `camera_ibfk_1` FOREIGN KEY (`PET_ID`) REFERENCES `pet` (`PET_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `SERVPRO_NUM` FOREIGN KEY (`SERVPRO_NUM`) REFERENCES `service_provided` (`SERVPRO_NUM`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `pet`
--
ALTER TABLE `pet`
  ADD CONSTRAINT `pet_ibfk_1` FOREIGN KEY (`CS_ID`) REFERENCES `customer` (`CS_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `petview`
--
ALTER TABLE `petview`
  ADD CONSTRAINT `petview_ibfk_1` FOREIGN KEY (`PET_ID`) REFERENCES `pet` (`PET_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `reservation_order`
--
ALTER TABLE `reservation_order`
  ADD CONSTRAINT `CS_ID` FOREIGN KEY (`CS_ID`) REFERENCES `customer` (`CS_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `SERV_CODE` FOREIGN KEY (`SERV_CODE`) REFERENCES `service_available` (`SERV_CODE`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `reservation_order_ibfk_1` FOREIGN KEY (`PET_ID`) REFERENCES `pet` (`PET_ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Constraints for table `service_provided`
--
ALTER TABLE `service_provided`
  ADD CONSTRAINT `RESERV_NUM` FOREIGN KEY (`RESERV_NUM`) REFERENCES `reservation_order` (`RESERV_NUM`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
