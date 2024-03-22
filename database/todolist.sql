-- --------------------------------------------------------
-- Máy chủ:                      127.0.0.1
-- Server version:               11.3.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Phiên bản:           12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for todolist
CREATE DATABASE IF NOT EXISTS `receipt_payment_app` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `receipt_payment_app`;

-- Dumping structure for table todolist.comments
CREATE TABLE IF NOT EXISTS `comments` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `text` varchar(500) NOT NULL,
  `admin_id` int(11) unsigned NOT NULL,
  `task_id` int(11) unsigned NOT NULL,
  `user_id` varchar(255) NOT NULL,
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_comments_task` (`task_id`) USING BTREE,
  KEY `fk_comments_admin` (`admin_id`) USING BTREE,
  CONSTRAINT `fk_comments_admin` FOREIGN KEY (`admin_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_comments_task` FOREIGN KEY (`task_id`) REFERENCES `work` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table todolist.comments: ~6 rows (approximately)
DELETE FROM `comments`;
INSERT INTO `comments` (`id`, `text`, `admin_id`, `task_id`, `user_id`, `date`) VALUES
	(71, '<p><strong>Haven\'t finished listening yet!!</strong></p>', 1, 30, 'dungnnqe170175@fpt.edu.vn', '2023-11-25 06:56:05'),
	(72, '<p><strong>Please complete the listening and then switch back to the status done.</strong></p>', 1, 30, 'dungnnqe170175@fpt.edu.vn', '2023-11-25 06:56:26'),
	(75, '<p><span style="color: rgb(224, 62, 45);"><strong>Not completed, please try again!</strong></span></p>', 1, 30, 'dungnnqe170175@fpt.edu.vn', '2023-11-25 08:47:17'),
	(78, '<p><span style="color: rgb(224, 62, 45);"><strong>Complete this task before December 9, 2023.</strong></span></p>', 1, 61, 'dungnnqe170175@fpt.edu.vn', '2023-11-26 03:56:47'),
	(79, '<p><span style="color: rgb(224, 62, 45);"><strong>Change the app\'s user interface!</strong></span></p>', 1, 63, 'tranngocsang9a3@gmail.com', '2023-11-26 04:10:40'),
	(80, '<p><span style="text-decoration: underline; color: rgb(224, 62, 45);"><strong>Help me finish it soon!</strong></span></p>\r\n<div class="ripple-wave active">&nbsp;</div>', 1, 57, 'tranngocsang9a3@gmail.com', '2023-11-26 11:45:09');

-- Dumping structure for table todolist.users
CREATE TABLE IF NOT EXISTS `users` (
    `id` INT PRIMARY KEY AUTO_INCREMENT,
     `username` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_unicode_ci',
    `password` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_unicode_ci',
    `status` TINYINT(4) NOT NULL DEFAULT '1',
    `firstName` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_unicode_ci',
    `lastName` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_unicode_ci',
    `role` INT(10) UNSIGNED NOT NULL,
    PhoneNumber VARCHAR(12),
    Moneyy FLOAT not null default '0',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    IsDelete ENUM('1', '0') NOT NULL DEFAULT '0',
    UNIQUE KEY username_unique (username)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table todolist.users: ~7 rows (approximately)
DELETE FROM `users`;
INSERT INTO `users` (`id`, `username`, `password`, `status`, `firstName`, `lastName`, `role`,`Moneyy`) VALUES
	(1, 'hunglmqe170213@fpt.edu.vn', '$2a$12$3e7snHttf9Dwa4I4Z8eH2ulGmW0lKH3RxOHTe54RMfBTZHM/DBIKO', 1, 'Le Manh', 'Hung', 1),
	(2, 'oanhdhqs170130@fpt.edu.vn', '$2a$12$hMZM8gefFi7aXbBdTTRv2OaOtLHn5XO589L607BzvfveMn.KWH7QS', 1, 'Dang Hoang ', 'Oanh', 1),
	(3, 'tranngocsang9a3@gmail.com', '$2a$12$2JuYI/24OI1908tXIJxsau0XRCw.1LbluNq39fu.wnJOZSSUBSEka', 1, 'Tran ', 'Sang', 1);
(4, 'nguyenngocduy352@gmail.com', '$2a$12$dWdbVwUcPgbTYoJIrU14VePCM5JX9AAahGwZ/VlK4TEF/sgTVQ4Ui', 1, 'Nguyen Ngoc','Duy', 2, 1000000)

-- email: nguyenngocduy352@gmail.com
-- pass: 123456789aA@duy
-- Dumping structure for table todolist.view
CREATE TABLE IF NOT EXISTS `view` (
  `viewed` int(10) unsigned NOT NULL,
  PRIMARY KEY (`viewed`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table todolist.view: ~1 rows (approximately)
DELETE FROM `view`;
INSERT INTO `view` (`viewed`) VALUES
	(370);

-- Dumping structure for table todolist.work
CREATE TABLE IF NOT EXISTS `work` (
    `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
    `description` LONGTEXT NOT NULL COLLATE 'utf8mb4_unicode_ci',
    `status` INT(10) UNSIGNED NOT NULL,
    `created_by` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
    `updated_by` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
    `created_date` VARCHAR(450) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
    `updated_date` VARCHAR(450) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
    `Amount` INT(10) UNSIGNED NOT NULL,
    `due` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
    IsDelete ENUM('1', '0') NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table todolist.work: ~58 rows (approximately)
DELETE FROM `work`;
INSERT INTO `work` (`id`, `name`, `description`, `status`, `created_by`, `updated_by`, `created_date`, `updated_date`, `Amount`, `due`) VALUES
	(1, 'Learn Japanese', 'Everyday', 2, 'nguyenhoangvu1201@gmail.com', 'nguyenhoangvu1201@gmail.com', '2023-11-22 02:13:04', '2023-11-22 02:13:25', 1, '2023-11-30'),
	(2, 'Stay up late', 'Coding', 1, 'nguyenhoangvu1201@gmail.com', 'nguyenhoangvu1201@gmail.com', '2023-11-22 02:14:37', '2023-11-22 02:31:48', 0, '2023-11-30'),
	(3, 'Eating late at night', 'Afraid of fat', 0, 'nguyenhoangvu1201@gmail.com', 'nguyenhoangvu1201@gmail.com', '2023-11-22 02:15:23', '2023-11-22 02:31:55', 0, '2023-11-30'),
	(4, 'House cleaning', 'Everyday', 3, 'nguyenhoangvu1201@gmail.com', 'nguyenhoangvu1201@gmail.com', '2023-11-22 02:16:31', '2023-11-22 02:18:13', 1, '2024-01-31'),
	(5, 'Fitness', '17h - 19h', 2, 'nguyenhoangvu1201@gmail.com', 'nguyenhoangvu1201@gmail.com', '2023-11-22 02:17:26', '2023-11-22 02:32:01', 0, '2023-12-23'),
	(7, 'Gaming', '15h - 22h', 0, 'nguyenhoangvu1201@gmail.com', 'nguyenhoangvu1201@gmail.com', '2023-11-22 02:19:43', '2023-11-22 02:34:26', 0, '2024-01-27'),
	(8, 'Play football', '20h - 21h', 1, 'nguyenhoangvu1201@gmail.com', '', '2023-11-22 02:34:20', '', 0, '2023-12-10'),
	(9, 'Dish washing', 'Everyday', 2, 'nguyenhoangvu1201@gmail.com', '', '2023-11-22 02:35:10', '', 0, '2023-12-31'),
	(10, 'Learning PRJ', 'THU - 23/11', 3, 'nguyenhoangvu1201@gmail.com', '', '2023-11-22 02:36:36', '', 0, '2023-11-23'),

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
