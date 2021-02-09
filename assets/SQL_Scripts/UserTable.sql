
-- Run the following command on MySQL CLI to execute the below script
-- SOURCE /Users/khannosa/Desktop/spring-boot-REST/assets/SQL_Scripts/UserTable.sql

-- Creating Schema/Database
DROP SCHEMA IF EXISTS `user_db`;
CREATE SCHEMA `user_db`;
use `user_db`;
SET FOREIGN_KEY_CHECKS = 0;

-- Creating Table
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
     `id` varchar(64) NOT NULL,
     `first_name` varchar(30) DEFAULT NULL,
     `last_name` varchar(30) DEFAULT NULL,
     `gender` varchar(30) DEFAULT NULL,
     `age` int(30) DEFAULT NULL,
     `email` varchar(30) DEFAULT NULL,
     PRIMARY KEY (`id`)
)