
CREATE DATABASE IF NOT EXISTS `todolist`;
USE `todolist`;


-- Dumping structure for table work
CREATE TABLE IF NOT EXISTS work (
  id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
 name VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
  description LONGTEXT NOT NULL COLLATE 'utf8mb4_unicode_ci',
 status INT(10) UNSIGNED NOT NULL,
  created_by VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
  updated_by VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
  created_date VARCHAR(450) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
  updated_date VARCHAR(450) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
  Amount INT(10) UNSIGNED NOT NULL,
  due VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
  IsDelete ENUM('1', '0') NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table work: ~58 rows (approximately)
DELETE FROM work;
INSERT INTO work (id, name, description, status, created_by, updated_by, created_date, updated_date, Amount, due) VALUES
(1, 'Learn Japanese', 'Everyday', 1, 'Viett9961@gamil.com', 'nguyenngocduy352@gmail.com', '2024-03-22 02:13:04', '2024-03-21 02:14:37', 10000, '2024-03-25'),
(2, 'Stay up late', 'Coding', 1, 'sangtxqe170101@fpt.edu.vn', 'nguyenngocduy352@gmail.com', '2024-03-21 02:14:37', '2024-03-21 02:14:37', 7500, '2024-03-25'),
(3, 'Eating late at night', 'Afraid of fat', 0, 'Viett9961@gamil.com', 'nguyenngocduy352@gmail.com', '2024-03-21 02:14:37', '2024-03-21 02:14:37', 6000, '2024-03-25'),
(4, 'House cleaning', 'Everyday', 1, 'haidang12112002@gmail.com', 'nguyenngocduy352@gmail.com', '2024-03-14 02:14:37', '2024-03-21 02:14:37', 15000, '2024-03-25'),
(5, 'Fitness', '17h - 19h', 0, 'huynhgiabao853@gmail.com', 'nguyenngocduy352@gmail.com', '2024-03-15 02:14:37', '2024-03-21 02:14:37', 21500, '2024-03-25'),
(7, 'Gaming', '15h - 22h', 0, 'sangtxqe170101@fpt.edu.vn', 'nguyenngocduy352@gmail.com', '2024-03-16 02:14:37', '2024-03-21 02:14:37', 5500, '2024-03-25'),
(8, 'Play football', '20h - 21h', 1, 'haidang12112002@gmail.com', '', '2024-03-17 02:14:37', '', 5500, '2024-03-25'),
(9, 'Dish washing', 'Everyday', 1, 'huynhgiabao853@gmail.com', '', '2024-03-19 02:14:37', '', 4400, '2024-03-25'),
(10, 'Learning PRJ', 'THU - 23/11', 1, 'haidang12112002@gmail.com', '', '2024-03-18 02:14:37', '', 7500, '2024-03-25');
CREATE TABLE IF NOT EXISTS `users` (
 `id` INT PRIMARY KEY AUTO_INCREMENT,
 `username` VARCHAR(255) NOT NULL,
 `password` VARCHAR(255) NOT NULL,
`status` TINYINT(4) NOT NULL DEFAULT '1',
`firstName` VARCHAR(255) NOT NULL,
 `lastName` VARCHAR(255) NOT NULL,
 `role` INT(10) UNSIGNED NOT NULL,
 `PhoneNumber` VARCHAR(12),
`Moneyy` FLOAT not null default '0',
 `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
 `IsDelete` ENUM('1', '0') NOT NULL DEFAULT '0',
 UNIQUE KEY username_unique (username)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table users: ~7 rows (approximately)
DELETE  FROM `users`;
INSERT INTO `users` (`id`, `username`, `password`, `status`, `firstName`, `lastName`, `role`, `Moneyy`) VALUES
(1, 'hunglmqe170213@fpt.edu.vn', '$2a$12$3e7snHttf9Dwa4I4Z8eH2ulGmW0lKH3RxOHTe54RMfBTZHM/DBIKO', 1, 'Le Manh', 'Hung', 1,0),
(2, 'haidang12112002@gmail.com', '$2a$12$hMZM8gefFi7aXbBdTTRv2OaOtLHn5XO589L607BzvfveMn.KWH7QS', 1, 'Dang Hoang ', 'Dang', 1, 0),
(3, 'huynhgiabao853@gmail.com', '$2a$12$2JuYI/24OI1908tXIJxsau0XRCw.1LbluNq39fu.wnJOZSSUBSEka', 1, 'Tran ', 'Bao', 1, 0),
(4, 'nguyenngocduy352@gmail.com', '$2a$12$dWdbVwUcPgbTYoJIrU14VePCM5JX9AAahGwZ/VlK4TEF/sgTVQ4Ui', 1, 'Nguyen Ngoc','Duy', 2, 1000000),
(5, 'sangtxqe170101@fpt.edu.vn', '$2a$12$2JuYI/24OI1908tXIJxsau0XRCw.1LbluNq39fu.wnJOZSSUBSEka', 1, 'Tran ', 'Sang', 1, 0),
(6, 'Viett9961@gamil.com', '$2a$12$2JuYI/24OI1908tXIJxsau0XRCw.1LbluNq39fu.wnJOZSSUBSEka', 1, 'Tran', 'Viet', 1, 0),
(7, '1nguyenngocduy@gmail.com', '$2a$12$dWdbVwUcPgbTYoJIrU14VePCM5JX9AAahGwZ/VlK4TEF/sgTVQ4Ui', 1, 'Tran ', 'Sang', 1, 0);

-- email: nguyenngocduy352@gmail.com
-- pass: 123456789aA@duy



CREATE TABLE IF NOT EXISTS `comments` (
`id` int(11) unsigned NOT NULL AUTO_INCREMENT,
`text` varchar(500) NOT NULL,
`admin_id` int(11) unsigned NOT NULL,
`bill_id` int(11) unsigned NOT NULL,
`user_id` varchar(255) NOT NULL,
`date` varchar(255) NOT NULL,
PRIMARY KEY (`id`)

) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



DELETE FROM `comments`;
INSERT INTO `comments` (`id`, `text`, `admin_id`, `bill_id`, `user_id`, `date`) VALUES
 (1, '<p><strong>Haven\'t finished listening yet!!</strong></p>', 1, 1, 'nguyenngocduy352@gmail.com', '2023-11-25 06:56:05'),
  (2, '<p><strong>Please complete the listening and then switch back to the status done.</strong></p>', 4, 2, 'nguyenngocduy352@gmail.com', '2023-11-25 06:56:26'),
  (3, '<p><span style="color: rgb(224, 62, 45);"><strong>Not completed, please try again!</strong></span></p>', 4, 2, 'nguyenngocduy352@gmail.com', '2023-11-25 08:47:17'),
 (4,'<p><span style="color: rgb(224, 62, 45);"><strong>Complete this task before December 9, 2023.</strong></span></p>', 4, 4, 'nguyenngocduy352@gmail.com', '2023-11-26 03:56:47'),
 (5,'<p><span style="color: rgb(224, 62, 45);"><strong>Change the app\'s user interface!</strong></span></p>', 4, 4, 'nguyenngocduy352@gmail.com', '2023-11-26 04:10:40'),
 (6,'<p><span style="text-decoration: underline; color: rgb(224, 62, 45);"><strong>Help me finish it soon!</strong></span></p>\r\n<div class="ripple-wave active">&nbsp;</div>', 4, 1, 'nguyenngocduy352@gmail.com', '2023-11-26 11:45:09');

-- Dumping structure for table users


