CREATE TABLE `work` (
                        `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
                        `name` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
                        `description` LONGTEXT NOT NULL COLLATE 'utf8mb4_unicode_ci',
                        `type` INT(10) UNSIGNED NOT NULL,
                        `created_by` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
                        `updated_by` VARCHAR(255) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
                        `created_date` VARCHAR(450) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
                        `updated_date` VARCHAR(450) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
                        `Amount` INT(10) UNSIGNED NOT NULL,
                        `due` VARCHAR(50) NOT NULL DEFAULT '' COLLATE 'utf8mb4_unicode_ci',
                        IsDelete ENUM('1', '0') NOT NULL DEFAULT '0',
                        status INT(10) UNSIGNED DEFAULT 1,
                            PRIMARY KEY (`id`) USING BTREE
--                             isdelete 0 = bth, 1 = isdelete
-- status 1: not paid, s2: paid, s3: expired
)
    COLLATE='utf8mb4_unicode_ci'
    ENGINE=InnoDB
    AUTO_INCREMENT=48
;
