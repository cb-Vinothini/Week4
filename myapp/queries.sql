DROP DATABASE IF EXISTS `self_service_portal`;
CREATE DATABASE IF NOT EXISTS `self_service_portal`;

USE `self_service_portal`;

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
INSERT INTO countries (country) VALUE ('--NA--'), ('India'), ('China'), ('Australia');

DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address`(
  `address_id` INT(10) AUTO_INCREMENT PRIMARY KEY,
  `line1` VARCHAR(50) DEFAULT "--NA--",
  `line2` VARCHAR(50) DEFAULT "--NA--",
  `city` VARCHAR(50) DEFAULT "--NA--",
  `state_id` INT(10) DEFAULT 1,
  `country_id` INT(10) DEFAULT 1,
  `zip` VARCHAR(6) DEFAULT "--NA--",
  CONSTRAINT `fk_state_id_address` FOREIGN KEY(`state_id`) REFERENCES `states` (`state_id`),
  CONSTRAINT `fk_country_id_address` FOREIGN KEY(`country_id`) REFERENCES `countries` (`country_id`)
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users`(
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `email` VARCHAR(100) NOT NULL PRIMARY KEY,
  `password` VARCHAR(100) NOT NULL,
  `address_id` INT(10) DEFAULT NULL,
  CONSTRAINT `fk_users_address` FOREIGN KEY(`address_id`) REFERENCES `address` (`address_id`)
);
INSERT INTO users(`first_name`, `last_name`, `email`, `password`) VALUES ('vinothini', 'meganathan', 'm.vinothini93@gmail.com', 'vino');

DELIMITER $$
DROP TRIGGER IF EXISTS `update_address`;
CREATE TRIGGER `update_address`
BEFORE UPDATE
ON `address`
FOR EACH ROW
BEGIN
IF NEW.line1 = '' THEN
  SET NEW.line1 = OLD.line1;
END IF;
IF NEW.line2 = '' THEN
  SET NEW.line2 = OLD.line2;
END IF;
IF NEW.city = '' THEN
  SET NEW.city = OLD.city;
END IF;
IF NEW.state_id = '' THEN
  SET NEW.state_id = OLD.state_id;
END IF;
IF NEW.country_id = '' THEN
  SET NEW.country_id = OLD.country_id;
END IF;
IF NEW.zip = '' THEN
  SET NEW.zip = OLD.zip;
END IF;
END $$
DELIMITER ;


DELIMITER $$
DROP TRIGGER IF EXISTS `update_user`;
CREATE TRIGGER `update_user`
BEFORE UPDATE
ON `users`
FOR EACH ROW
BEGIN
IF NEW.first_name = '' THEN
  SET NEW.first_name = OLD.first_name;
END IF;
IF NEW.last_name = '' THEN
  SET NEW.last_name = OLD.last_name;
END IF;
END $$
DELIMITER ;
