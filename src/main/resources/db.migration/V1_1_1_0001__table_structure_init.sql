DROP TABLE IF EXISTS `user_detail_data`;
CREATE TABLE `user_detail_data` (
  `user_detail_data_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL COMMENT '权限名',
  `password` VARBINARY(255) NOT NULL COMMENT '权限描述',
  `account_non_expired` tinyint(1) NOT NULL COMMENT '权限地址',
  `account_non_locked` tinyint(1) NOT NULL COMMENT '权限地址',
  `credentials_non_expired` tinyint(1) NOT NULL COMMENT '权限地址',

  `created_at` datetime NOT NULL,
  `modified_at` datetime NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `active` tinyint(1) NOT NULL,

  PRIMARY KEY (`user_detail_data_id`),
  UNIQUE KEY `user_detail_data_uk_1` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `user_role_data`;
CREATE TABLE `user_role_data` (
  `user_role_data_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(45) NOT NULL COMMENT '权限名',
  `user_detail_data_id` bigint(20) NOT NULL COMMENT '权限描述',

  `created_at` datetime NOT NULL,
  `modified_at` datetime NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `active` tinyint(1) NOT NULL,

  PRIMARY KEY (`user_role_data_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;