-- phpMyAdmin SQL Dump
-- version 3.5.8.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 18, 2015 at 12:31 PM
-- Server version: 5.1.73
-- PHP Version: 5.3.28

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `project3_se03`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity`
--

CREATE TABLE IF NOT EXISTS `activity` (
  `ActivityID` int(11) NOT NULL,
  `ActivityName` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  `DetailActivity` text CHARACTER SET utf8,
  `Image` varchar(90) CHARACTER SET ucs2 DEFAULT NULL,
  `Video` varchar(90) CHARACTER SET utf8 DEFAULT NULL,
  `Importance` text CHARACTER SET utf8,
  `Suggestion` text CHARACTER SET utf8,
  `Performance` text CHARACTER SET utf8,
  `Benefit` text CHARACTER SET utf8,
  `Solution` text CHARACTER SET utf8,
  `Objective` text CHARACTER SET utf8,
  `Deleted` varchar(2) CHARACTER SET utf8 DEFAULT '0',
  `Activate` varchar(2) CHARACTER SET utf8 DEFAULT NULL,
  `InsertBy` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `InsertDate` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `UpdateBy` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `UpdateDate` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `AddressID` int(11) NOT NULL,
  `FacultyID` int(11) NOT NULL,
  `TypeActivityID` int(11) NOT NULL,
  PRIMARY KEY (`ActivityID`),
  KEY `fk_activity_address_idx` (`AddressID`),
  KEY `fk_activity_faculty1_idx` (`FacultyID`),
  KEY `fk_activity_type_activity1_idx` (`TypeActivityID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `activity`
--

INSERT INTO `activity` (`ActivityID`, `ActivityName`, `DetailActivity`, `Image`, `Video`, `Importance`, `Suggestion`, `Performance`, `Benefit`, `Solution`, `Objective`, `Deleted`, `Activate`, `InsertBy`, `InsertDate`, `UpdateBy`, `UpdateDate`, `AddressID`, `FacultyID`, `TypeActivityID`) VALUES
(1, 'โครงการเปิดโลกให้พี่', 'โครงการ “เปิดโลกให้น้อง”\r\n\r\nซึ่งโครงการนี้ได้จัดหากิจกรรมเพื่อส่งเสริมความกล้าแสดงออก ความมั่นใจ ความเป็นอยู่ที่ถูกสุขลักษณะและการดำรงชีวิตอยู่ในสังคมอย่างมีความสุขให้กับน้องๆในชุมชนชาวไทยใหม่บ้านสะปำ นอกจากนี้ ยังเป็นการสร้างความสัมพันธ์ที่ดีระหว่างมหาวิทยาลัยกับชุมชนอีกด้วย', NULL, NULL, NULL, NULL, NULL, NULL, '1.ถูกตัดขาดจากโลกภายนอก ', 'วัตถุประสงค์  1. เพื่อลดความรู้สึกโดดเดี่ยว ถูกตัดขาดจากโลกภายนอก ให้แก่น้องๆชาวไทยใหม่  2. เพื่อส่งเสริมให้น้องๆมีความกล้าในการใช้ชีวิตให้เข้ากับสังคมภายนอก  3 เพื่อให้ความรู้เกี่ยวกับสุขลักษณะอนามัย', '0', NULL, NULL, NULL, NULL, NULL, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `AddressID` int(11) NOT NULL,
  `Address1` varchar(150) CHARACTER SET utf8 DEFAULT NULL,
  `Address2` varchar(150) CHARACTER SET utf8 DEFAULT NULL,
  `Tumbol` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `District` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `Latitude` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `Longitude` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`AddressID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`AddressID`, `Address1`, `Address2`, `Tumbol`, `District`, `Latitude`, `Longitude`) VALUES
(1, 'หมู่ที่ 3 ', NULL, 'เกาะแก้ว ', 'เมือง', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE IF NOT EXISTS `faculty` (
  `FacultyID` int(11) NOT NULL,
  `FacultyName` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`FacultyID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`FacultyID`, `FacultyName`) VALUES
(1, 'คณะเทคโนโลยีและสิ่งแวดล้อม'),
(2, 'คณะการโรงแรมและการท่องเที่ยว');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE IF NOT EXISTS `staff` (
  `StaffID` int(11) NOT NULL,
  `Username` varchar(90) CHARACTER SET utf8 DEFAULT NULL,
  `Password` varchar(90) CHARACTER SET utf8 DEFAULT NULL,
  `Firstname` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `Lastname` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `Email` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `Tel` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `Lastlogin` datetime DEFAULT NULL,
  `StaffRoleID` int(11) NOT NULL,
  PRIMARY KEY (`StaffID`),
  KEY `fk_staff_staff_role1_idx` (`StaffRoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `staff_role`
--

CREATE TABLE IF NOT EXISTS `staff_role` (
  `StaffRoleID` int(11) NOT NULL,
  `StaffRoleName` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`StaffRoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `type_activity`
--

CREATE TABLE IF NOT EXISTS `type_activity` (
  `TypeActivityID` int(11) NOT NULL,
  `TypeActivityName` varchar(150) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`TypeActivityID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `type_activity`
--

INSERT INTO `type_activity` (`TypeActivityID`, `TypeActivityName`) VALUES
(1, 'เด็กและเยาวชน'),
(2, 'คนชรา/ผู้ยากไร้'),
(3, 'ช่วยเหลือสัตว์'),
(4, 'สิ่งแวดล้อม');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activity`
--
ALTER TABLE `activity`
  ADD CONSTRAINT `fk_activity_address` FOREIGN KEY (`AddressID`) REFERENCES `address` (`AddressID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_activity_faculty1` FOREIGN KEY (`FacultyID`) REFERENCES `faculty` (`FacultyID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_activity_type_activity1` FOREIGN KEY (`TypeActivityID`) REFERENCES `type_activity` (`TypeActivityID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `fk_staff_staff_role1` FOREIGN KEY (`StaffRoleID`) REFERENCES `staff_role` (`StaffRoleID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
