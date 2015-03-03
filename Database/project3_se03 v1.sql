-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 03, 2015 at 05:14 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
  `ActivityName` varchar(200) DEFAULT NULL,
  `DetailActivity` text,
  `Image` varchar(90) DEFAULT NULL,
  `Video` varchar(200) DEFAULT NULL,
  `Importance` text,
  `Suggestion` text,
  `Performance` text,
  `Benefit` text,
  `Objective` text,
  `year` int(4) NOT NULL,
  `Deleted` varchar(2) DEFAULT '0',
  `Activate` varchar(2) DEFAULT NULL,
  `InsertBy` varchar(45) DEFAULT NULL,
  `InsertDate` varchar(45) DEFAULT NULL,
  `UpdateBy` varchar(45) DEFAULT NULL,
  `UpdateDate` varchar(45) DEFAULT NULL,
  `AddressID` int(11) DEFAULT NULL,
  `TypeActivityID` int(11) NOT NULL,
  `SubjectID` int(11) NOT NULL,
  PRIMARY KEY (`ActivityID`),
  KEY `fk_activity_address_idx` (`AddressID`),
  KEY `fk_activity_type_activity1_idx` (`TypeActivityID`),
  KEY `fk_activity_subject1_idx` (`SubjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `activity`
--

INSERT INTO `activity` (`ActivityID`, `ActivityName`, `DetailActivity`, `Image`, `Video`, `Importance`, `Suggestion`, `Performance`, `Benefit`, `Objective`, `year`, `Deleted`, `Activate`, `InsertBy`, `InsertDate`, `UpdateBy`, `UpdateDate`, `AddressID`, `TypeActivityID`, `SubjectID`) VALUES
(1, 'ปลูกหญ้าทะเล', '', NULL, 'https://www.youtube.com/watch?v=qMOFry90OYo', '', '', '', '', '', 2557, '0', NULL, NULL, NULL, NULL, NULL, 1, 4, 1),
(12, 'เปิดโลกให้น้อง', 'โครงการเปิดโลกให้น้องๆชาวไทยใหม่บ้านสะปำ จ.ภูเก็ต\r\nปฏิบัติงานโดยกลุ่มนักศึกษามอ.ภูเก็ต ปี 2 คณะเทคโนโลยีและสารสนเทศ สาขาวิศวกรรมซอต์ฟแวร์', NULL, 'http://www.youtube.com/watch?v=Hg_chzf7n-g', 'โครงการที่มีชื่อว่า “เปิดโลกให้น้อง” ซึ่งโครงการนี้ได้จัดหากิจกรรมเพื่อส่งเสริมความกล้าแสดงออก ความมั่นใจ ความเป็นอยู่ที่ถูกสุขลักษณะและการดำรงชีวิตอยู่ในสังคมอย่างมีความสุขให้กับน้องๆในชุมชนชาวไทยใหม่บ้านสะปำ นอกจากนี้ ยังเป็นการสร้างความสัมพันธ์ที่ดีระหว่างมหาวิทยาลัยกับชุมชนอีกด้วย', '1. สถานที่ในการจัดกิจกรรมไม่เอื้ออำนวย\r\n\r\n2. การเดินทางไม่ค่อยสะดวกเท่าที่ควร\r\n\r\n3. อุปกรณ์ที่ต้องใช้ในสถานที่ขาดแคลน ต้องไปเอาจากที่ไกลๆ\r\n\r\nเช่น โต๊ะ เก้าอี้ ต้องไปขนที่ศาลเจ้าใกล้ๆ\r\n\r\n4. ในบางครั้ง ผู้ใหญ่บ้านไม่ค่อยสะดวกที่จะทำการติดต่อ\r\n\r\n5. สภาพอากาศไม่เอื้ออำนวย เนื่องจากฝนตก', 'สรุปผลการดำเนินงาน\r\n\r\n1. น้องๆในชุมชนชาวไทยใหม่บ้านสะปำ มีความกล้าแสดงออกมากขึ้น\r\n\r\n2. น้องๆสามารถตอบคำถามเกี่ยวกับ สุขลักษณะ สภาพแวดล้อมเป็นพิษ\r\n\r\nและโทษของยาเสพติดได้\r\n\r\n3. น้องๆสามารถนำความรู้จากกิจกรรมไปใช้ในชีวิตประจำวันได้\r\n\r\n4. ทำให้มีความรับผิดชอบและรู้จักการทำงานเป็นกลุ่ม\r\n\r\n5. ทำให้มีจิตสาธารณะและรู้จักการช่วยเหลือสังคมมากขึ้น', 'ประโยชน์ที่ได้รับจากการทำโครงการ\r\n\r\n· ด้านคุณธรรม จริยธรรม\r\n\r\n- มีจิตสาธารณะ\r\n\r\n- รู้จักเสียสละ มีน้ำใจ\r\n\r\n- มีความเอื้ออาทรต่อผู้อื่น และช่วยเหลือสังคม\r\n\r\n· ด้านความรู้\r\n\r\n- ได้ความรู้เกี่ยวกับยาเสพติด รักธรรมชาติมากขึ้น และสุขลักษณะอนามัย\r\n\r\n- มีจิตวิทยา เพื่อคุณภาพชีวิตที่ดีขึ้น และเข้าใจสังคมมากขึ้น\r\n\r\n· ด้านทักษะความสัมพันธ์ระหว่างบุคคลและความรับผิดชอบ\r\n\r\n- กล้าแสดงออกต่อที่ประชุมชนมากขึ้น\r\n\r\n- ได้เรียนรู้เกี่ยวกับการทำงาน ประสานงาน และติดต่อกับหน่วยงานต่างๆ\r\n\r\n- รู้จักการแบ่งเวลา และการตรงต่อเวลา\r\n\r\n- รู้จักการรับฟังความคิดเห็นของผู้อื่น\r\n\r\n- รู้จักการเป็นผู้นำ และผู้ตามที่ดี\r\n\r\n- รู้จักการทำงานร่วมกันเป็นหมู่คณะ\r\n\r\n· ด้านทักษะทางปัญญา\r\n\r\n- มีการวางแผนการทำงานมากขึ้น\r\n\r\n- รู้จักการแก้ปัญหาต่างๆ โดยเฉพาะการแก้ปัญหาเฉพาะหน้า\r\n\r\n· ด้านทักษะการวิเคราะห์เชิงตัวเลข และการใช้เทคโนโลยีสารสนเทศ\r\n\r\n- รู้วิธีการทำงานต่าง ๆ ของเครื่องคอมพิวเตอร์ที่ช่วยสร้างความบันเทิง\r\n\r\n- ใช้อินเตอร์เน็ตในการหาข้อมูลเพื่อจัดทำโครงการ', 'วัตถุประสงค์\r\n\r\n1. เพื่อลดความรู้สึกโดดเดี่ยว ถูกตัดขาดจากโลกภายนอก ให้แก่น้องๆชาวไทยใหม่\r\n\r\n2. เพื่อส่งเสริมให้น้องๆมีความกล้าในการใช้ชีวิตให้เข้ากับสังคมภายนอก\r\n\r\n3 เพื่อให้ความรู้เกี่ยวกับสุขลักษณะอนามัยที่สะอาดและถูกต้อง\r\n\r\n4. เพื่อฝึกให้น้องๆมีจิตสาธารณะ และรู้จักช่วยเหลือสังคม\r\n\r\n5. เพื่อพัฒนาทักษะต่างๆให้กับน้องๆ', 2556, '0', NULL, NULL, NULL, NULL, NULL, NULL, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `AddressID` int(11) NOT NULL,
  `Address` varchar(150) DEFAULT NULL,
  `TumbonID` int(45) DEFAULT NULL,
  `Latitude` varchar(45) DEFAULT NULL,
  `Longitude` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`AddressID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`AddressID`, `Address`, `TumbonID`, `Latitude`, `Longitude`) VALUES
(1, '325/8 ม.2', 15, 'dd', 'dd');

-- --------------------------------------------------------

--
-- Table structure for table `area_volunteer`
--

CREATE TABLE IF NOT EXISTS `area_volunteer` (
  `AreaVolunteer` int(11) NOT NULL,
  `ContactName` varchar(200) DEFAULT NULL,
  `DetailActivity` text,
  `Tel` varchar(45) DEFAULT NULL,
  `AddressID` int(11) NOT NULL,
  `TypeActivityID` int(11) NOT NULL,
  PRIMARY KEY (`AreaVolunteer`),
  KEY `fk_area_volunteer_address1_idx` (`AddressID`),
  KEY `fk_area_volunteer_type_activity1_idx` (`TypeActivityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `area_volunteer`
--

INSERT INTO `area_volunteer` (`AreaVolunteer`, `ContactName`, `DetailActivity`, `Tel`, `AddressID`, `TypeActivityID`) VALUES
(0, 'นายธีระวัฒน์ แพะปลอด', 'โครงการอาสาดีๆ ที่คุณก็ร่วมเป็นส่วนหนึ่งในการทำความดีได้ ง่ายๆแค่ร่วมเข้าไปโหวตภาระกิจอาสาที่คุณชื่นชอบ ได้ที่ www.thelittlebigprojectthailand.com/blog', '085-463-7531', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `district`
--

CREATE TABLE IF NOT EXISTS `district` (
  `DistrictID` int(11) NOT NULL,
  `DistrictName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`DistrictID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `district`
--

INSERT INTO `district` (`DistrictID`, `DistrictName`) VALUES
(1, 'เมืองภูเก็ต'),
(2, 'กะทู้'),
(3, 'ถลาง');

-- --------------------------------------------------------

--
-- Table structure for table `solution`
--

CREATE TABLE IF NOT EXISTS `solution` (
  `SolutionID` int(11) NOT NULL,
  `SolutionName` varchar(200) DEFAULT NULL,
  `ActivityID` int(11) NOT NULL,
  PRIMARY KEY (`SolutionID`),
  KEY `fk_solution_activity1_idx` (`ActivityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE IF NOT EXISTS `staff` (
  `StaffID` int(11) NOT NULL,
  `Username` varchar(90) DEFAULT NULL,
  `Password` varchar(90) DEFAULT NULL,
  `Firstname` varchar(45) DEFAULT NULL,
  `Lastname` varchar(45) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Tel` varchar(45) DEFAULT NULL,
  `Lastlogin` datetime DEFAULT NULL,
  `StaffRoleID` int(11) NOT NULL,
  PRIMARY KEY (`StaffID`),
  KEY `fk_staff_staff_role1_idx` (`StaffRoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `staff_role`
--

CREATE TABLE IF NOT EXISTS `staff_role` (
  `StaffRoleID` int(11) NOT NULL,
  `StaffRoleName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`StaffRoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `SubjectID` int(11) NOT NULL,
  `SubjectName` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SubjectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`SubjectID`, `SubjectName`) VALUES
(1, 'WISDOM OF LIVING'),
(2, 'CO-CURRICULAR ACTIVITIES'),
(3, 'ชมรมฅนอาสา'),
(4, 'ชมรมโรตาแลกซ์');

-- --------------------------------------------------------

--
-- Table structure for table `tumbon`
--

CREATE TABLE IF NOT EXISTS `tumbon` (
  `TumbonID` int(11) NOT NULL,
  `TumbonName` varchar(100) DEFAULT NULL,
  `DistrictID` int(11) NOT NULL,
  PRIMARY KEY (`TumbonID`),
  KEY `fk_tumbon_district1_idx` (`DistrictID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tumbon`
--

INSERT INTO `tumbon` (`TumbonID`, `TumbonName`, `DistrictID`) VALUES
(1, 'ตลาดใหญ่', 1),
(2, 'ตลาดเหนือ', 1),
(3, 'เกาะแก้ว', 1),
(4, 'รัษฎา', 1),
(5, 'วิชิต', 1),
(6, 'ฉลอง', 1),
(7, 'ราไวย์', 1),
(8, 'กะรน', 1),
(9, 'กะทู้', 2),
(10, 'ป่าตอง', 2),
(11, 'กมลา', 2),
(12, 'เทพกระษัตรี', 3),
(13, 'ศรีสุนทร', 3),
(14, 'เชิงทะเล', 3),
(15, 'ป่าคลอก', 3),
(16, 'ไม้ขาว ', 3),
(17, 'สาคู', 3);

-- --------------------------------------------------------

--
-- Table structure for table `type_activity`
--

CREATE TABLE IF NOT EXISTS `type_activity` (
  `TypeActivityID` int(11) NOT NULL,
  `TypeActivityName` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`TypeActivityID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  ADD CONSTRAINT `fk_activity_subject1` FOREIGN KEY (`SubjectID`) REFERENCES `subject` (`SubjectID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_activity_type_activity1` FOREIGN KEY (`TypeActivityID`) REFERENCES `type_activity` (`TypeActivityID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `area_volunteer`
--
ALTER TABLE `area_volunteer`
  ADD CONSTRAINT `fk_area_volunteer_address1` FOREIGN KEY (`AddressID`) REFERENCES `address` (`AddressID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_area_volunteer_type_activity1` FOREIGN KEY (`TypeActivityID`) REFERENCES `type_activity` (`TypeActivityID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `solution`
--
ALTER TABLE `solution`
  ADD CONSTRAINT `fk_solution_activity1` FOREIGN KEY (`ActivityID`) REFERENCES `activity` (`ActivityID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `fk_staff_staff_role1` FOREIGN KEY (`StaffRoleID`) REFERENCES `staff_role` (`StaffRoleID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tumbon`
--
ALTER TABLE `tumbon`
  ADD CONSTRAINT `fk_tumbon_district1` FOREIGN KEY (`DistrictID`) REFERENCES `district` (`DistrictID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
