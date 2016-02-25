DROP DATABASE IF EXISTS `phone_directory`;
CREATE DATABASE IF NOT EXISTS `phone_directory`;

USE `phone_directory`;

DROP TABLE IF EXISTS `states`;
CREATE TABLE IF NOT EXISTS `states`(
  `state_id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `state` VARCHAR(20)
);
INSERT INTO states (state) VALUE ('--NA--'), ('Tamil Nadu'), ('Kerala'), ("Karnataka");

DROP TABLE IF EXISTS `countries`;
CREATE TABLE IF NOT EXISTS `countries`(
  `country_id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `country` VARCHAR(20)
);
INSERT INTO countries (country) VALUE ('--NA--'), ('India');

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location`(
  `location_id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `state_id` INT(10),
  `country_id` INT(10),
  CONSTRAINT `fk_state_id_location` FOREIGN KEY(`state_id`) REFERENCES `states` (`state_id`),
  CONSTRAINT `fk_country_id_location` FOREIGN KEY(`country_id`) REFERENCES `countries` (`country_id`)
);
INSERT INTO location (state_id, country_id) VALUE (1,1), (2,2), (3,2), (4,2);

DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address`(
  `address_id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `line1` VARCHAR(50) DEFAULT "--NA--",
  `line2` VARCHAR(50) DEFAULT "--NA--",
  `city` VARCHAR(50) DEFAULT "--NA--",
  `location_id` INT(10) DEFAULT 1,
  `zip` VARCHAR(6) DEFAULT "--NA--",
  CONSTRAINT `fk_location_id_address` FOREIGN KEY(`location_id`) REFERENCES `location` (`location_id`)
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users`(
  `user_id` VARCHAR(100) NOT NULL PRIMARY KEY,
  `password` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL
);

DROP TABLE IF EXISTS `contacts`;
CREATE TABLE IF NOT EXISTS `contacts`(
  `contact_id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `user_id` VARCHAR(100) NOT NULL,
  `c_first_name` VARCHAR(100) NOT NULL,
  `c_last_name` VARCHAR(100) NOT NULL,
  `c_address_id` INT(10) DEFAULT NULL,
  `mobile` VARCHAR(10) DEFAULT NULL,
  `home` VARCHAR(10) DEFAULT NULL,
  `work` VARCHAR(10) DEFAULT NULL,
  CONSTRAINT `fk_contact_address` FOREIGN KEY(`c_address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `fk_username_contacts` FOREIGN KEY(`user_id`) REFERENCES `users` (`user_id`)
);
