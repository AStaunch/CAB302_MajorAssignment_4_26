-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.5.10-MariaDB - mariadb.org binary distribution
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
CREATE DATABASE IF NOT EXISTS `cab302` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cab302`;

-- Dumping structure for table cab302.inventory
CREATE TABLE IF NOT EXISTS `inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) NOT NULL,
  `type` enum('CPU HOURS') NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_inventory_organisation` (`org_id`),
  CONSTRAINT `FK_inventory_organisation` FOREIGN KEY (`org_id`) REFERENCES `organisation` (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- Dumping data for table cab302.inventory: ~0 rows (approximately)
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;

-- Dumping structure for table cab302.list_item
CREATE TABLE IF NOT EXISTS `list_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `org_id` int(11) DEFAULT NULL,
  `seller_id` int(11) DEFAULT NULL,
  `asset_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `ppu` int(11) DEFAULT NULL,
  `bs` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_list_item_organisation` (`org_id`),
  KEY `FK_list_item_user` (`seller_id`),
  KEY `FK_list_item_inventory` (`asset_id`),
  CONSTRAINT `FK_list_item_inventory` FOREIGN KEY (`asset_id`) REFERENCES `inventory` (`id`),
  CONSTRAINT `FK_list_item_organisation` FOREIGN KEY (`org_id`) REFERENCES `organisation` (`org_id`),
  CONSTRAINT `FK_list_item_user` FOREIGN KEY (`seller_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table cab302.list_item: ~0 rows (approximately)
/*!40000 ALTER TABLE `list_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `list_item` ENABLE KEYS */;

-- Dumping structure for table cab302.organisation
CREATE TABLE IF NOT EXISTS `organisation` (
  `org_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `credits` int(11) NOT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Dumping data for table cab302.organisation: ~1 rows (approximately)
/*!40000 ALTER TABLE `organisation` DISABLE KEYS */;
REPLACE INTO `organisation` (`org_id`, `name`, `credits`) VALUES
	(1, 'Administration', 0);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- Dumping data for table cab302.transaction: ~0 rows (approximately)
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
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

-- Dumping data for table cab302.user: ~1 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`id`, `org_id`, `username`, `first_name`, `last_name`, `hash_pwd`, `is_admin`) VALUES
	(1, 1, 'Admin', 'Admin', 'Admin', '3f3f8baad08c9ccc4a3e02a17d042b76a1b75162b22bdf00f7273efd896fd5d0', b'1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
