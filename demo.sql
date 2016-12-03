-- --------------------------------------------------------
-- Host:                         192.168.0.99
-- Server version:               5.7.16 - MySQL Community Server (GPL)
-- Server OS:                    Linux
-- HeidiSQL Version:             9.3.0.5031
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for demo
DROP DATABASE IF EXISTS `demo`;
CREATE DATABASE IF NOT EXISTS `demo` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `demo`;


-- Dumping structure for table demo.t_course
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE IF NOT EXISTS `t_course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `code` char(8) DEFAULT NULL COMMENT '代码',
  `name` varchar(50) DEFAULT NULL COMMENT '课程名',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table demo.t_course: ~0 rows (approximately)
DELETE FROM `t_course`;
/*!40000 ALTER TABLE `t_course` DISABLE KEYS */;
INSERT INTO `t_course` (`id`, `code`, `name`) VALUES
	(6, '0006', '??6');
/*!40000 ALTER TABLE `t_course` ENABLE KEYS */;


-- Dumping structure for table demo.t_rel_course_student
DROP TABLE IF EXISTS `t_rel_course_student`;
CREATE TABLE IF NOT EXISTS `t_rel_course_student` (
  `student_id` bigint(20) NOT NULL,
  `course_id` bigint(20) NOT NULL,
  PRIMARY KEY (`student_id`,`course_id`),
  KEY `FK_t_rel_course_student_t_course` (`course_id`),
  CONSTRAINT `FK_t_rel_course_student_t_course` FOREIGN KEY (`course_id`) REFERENCES `t_course` (`id`),
  CONSTRAINT `FK_t_rel_course_student_t_student` FOREIGN KEY (`student_id`) REFERENCES `t_student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table demo.t_rel_course_student: ~0 rows (approximately)
DELETE FROM `t_rel_course_student`;
/*!40000 ALTER TABLE `t_rel_course_student` DISABLE KEYS */;
INSERT INTO `t_rel_course_student` (`student_id`, `course_id`) VALUES
	(1, 6);
/*!40000 ALTER TABLE `t_rel_course_student` ENABLE KEYS */;


-- Dumping structure for table demo.t_student
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE IF NOT EXISTS `t_student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Id',
  `id_number` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `gender` tinyint(4) DEFAULT NULL COMMENT '性别0=女,1=男,2=未知',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_number` (`id_number`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table demo.t_student: ~1 rows (approximately)
DELETE FROM `t_student`;
/*!40000 ALTER TABLE `t_student` DISABLE KEYS */;
INSERT INTO `t_student` (`id`, `id_number`, `name`, `gender`) VALUES
	(1, NULL, 'demo mission', 2);
/*!40000 ALTER TABLE `t_student` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
