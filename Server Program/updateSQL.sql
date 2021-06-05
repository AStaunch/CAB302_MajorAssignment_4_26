-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.6.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for cab302
DROP DATABASE IF EXISTS `cab302`;
CREATE DATABASE IF NOT EXISTS `cab302` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cab302`;

-- Dumping structure for table cab302.inventory
CREATE TABLE IF NOT EXISTS `inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_inventory_organisation` (`org_id`),
  CONSTRAINT `FK_inventory_organisation` FOREIGN KEY (`org_id`) REFERENCES `organisation` (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- Dumping data for table cab302.inventory: ~4 rows (approximately)
DELETE FROM `inventory`;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` (`id`, `org_id`, `type`, `quantity`) VALUES
	(1, 1, 'CPU HOURS', 100),
	(2, 2, 'CPU HOURS', 120),
	(3, 3, 'CPU HOURS', 140),
	(4, 5, 'CPU HOURS', 160);
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;

-- Dumping structure for table cab302.list_item
CREATE TABLE IF NOT EXISTS `list_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `asset_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `credits` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_list_item_organisation` (`org_id`),
  KEY `FK_list_item_user` (`seller_id`),
  KEY `FK_list_item_inventory` (`asset_id`),
  CONSTRAINT `FK_list_item_inventory` FOREIGN KEY (`asset_id`) REFERENCES `inventory` (`id`),
  CONSTRAINT `FK_list_item_organisation` FOREIGN KEY (`org_id`) REFERENCES `organisation` (`org_id`),
  CONSTRAINT `FK_list_item_user` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table cab302.list_item: ~0 rows (approximately)
DELETE FROM `list_item`;
/*!40000 ALTER TABLE `list_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `list_item` ENABLE KEYS */;

-- Dumping structure for table cab302.organisation
CREATE TABLE IF NOT EXISTS `organisation` (
  `org_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `credits` int(11) NOT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Dumping data for table cab302.organisation: ~4 rows (approximately)
DELETE FROM `organisation`;
/*!40000 ALTER TABLE `organisation` DISABLE KEYS */;
INSERT INTO `organisation` (`org_id`, `name`, `credits`) VALUES
	(1, 'Jaydens org', 100),
	(2, 'Alexs org', 180),
	(3, 'Andres org', 150),
	(5, 'Toms org', 120);
/*!40000 ALTER TABLE `organisation` ENABLE KEYS */;

-- Dumping structure for table cab302.transaction
CREATE TABLE IF NOT EXISTS `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `asset_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `credits` int(11) DEFAULT NULL,
  `buyer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_transaction_organisation` (`org_id`),
  KEY `FK_transaction_user` (`seller_id`),
  KEY `FK_transaction_inventory` (`asset_id`),
  KEY `FK_transaction_user_2` (`buyer_id`),
  CONSTRAINT `FK_transaction_inventory` FOREIGN KEY (`asset_id`) REFERENCES `inventory` (`id`),
  CONSTRAINT `FK_transaction_organisation` FOREIGN KEY (`org_id`) REFERENCES `organisation` (`org_id`),
  CONSTRAINT `FK_transaction_user` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_transaction_user_2` FOREIGN KEY (`buyer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table cab302.transaction: ~0 rows (approximately)
DELETE FROM `transaction`;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;

-- Dumping structure for table cab302.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `hash_pwd` varchar(100) NOT NULL,
  `is_admin` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `org_ID` (`org_id`),
  CONSTRAINT `org_ID` FOREIGN KEY (`org_id`) REFERENCES `organisation` (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

-- Dumping data for table cab302.user: ~7 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `org_id`, `username`, `first_name`, `last_name`, `hash_pwd`, `is_admin`) VALUES
	(1, NULL, 'usernam', 'CAB', '302', 'cab302', b'1'),
	(2, 1, 'jaydo', 'Jayden', 'Truong', 'jayden', b'0'),
	(4, 2, 'wong', 'Andre', 'Something', 'andre', b'0'),
	(5, 5, 'king', 'Tom', 'Bartlett', 'tom', b'0'),
	(6, 1, 'AlexS', 'Alex', 'Stallard', 'Alex', b'0'),
	(8, NULL, 'AlexSally', 'AlexS', 'AStallard', 'AlexStallo', b'0'),
	(9, 2, 'Jack', 'Danny', 'Pinny', 'boink', b'0'),
	(10, 1, 'Biggboi', 'bigg', 'boi', 'biggyboi', b'0'),
	(11, 1, 'username', 'Ragnor the destroyer', 'Tom', 'B', b'0'),
	(12, 1, 'Tommy', 'Jayden', 'Andyre', 'password', b'0'),
	(13, 1, 'Tommy2', 'Jayden2', 'Andyre', 'password', b'0'),
	(14, 1, 'davensadlhnsaldk', 'kjlsdhjaklsdh', 'd', 'kjsadhklasdh', b'0'),
	(15, 1, 'asduashdo', 'kchwufhi', 'hiuhdiu', 'hiuc', b'0');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
