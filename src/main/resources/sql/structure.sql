SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP DATABASE IF EXISTS `todolist_servlet`;
CREATE DATABASE `todolist_servlet` CHARACTER SET utf8 COLLATE utf8_general_ci;
USE todolist_servlet;

CREATE TABLE `task`
(
  `id`                 bigint(20)   NOT NULL AUTO_INCREMENT,
  `name`               varchar(255) NOT NULL,
  `description`        varchar(512) NOT NULL,
  `event_date`         bigint(20)   NOT NULL,
  `creation_date_time` bigint(20)   NOT NULL,
  `state`            int(11)        NOT NULL,
  PRIMARY KEY (`id`),
  KEY `deleted` (`deleted`),
  KEY `eventDate` (`event_date`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `attachment`
(
  `id`             bigint(20)   NOT NULL AUTO_INCREMENT,
  `task_id`        bigint(20)   NOT NULL,
  `original_name`  varchar(255) NOT NULL,
  `generated_path` varchar(255) NOT NULL,
  `generated_name` varchar(36)  NOT NULL,
  `mime_type`      varchar(11)  NOT NULL,
  PRIMARY KEY (`id`),
  KEY `taskId` (`task_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
//

CREATE TABLE todolist_servlet.attachment (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  task_id bigint(20) NOT NULL,
  original_name varchar(255) NOT NULL,
  generated_path varchar(255) NOT NULL,
  generated_name varchar(36) NOT NULL,
  mime_type varchar(11) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
CHARACTER SET utf8,
COLLATE utf8_general_ci;

ALTER TABLE todolist_servlet.attachment
ADD INDEX taskId (task_id);

//

CREATE TABLE todolist_servlet.task (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  description varchar(512) DEFAULT NULL,
  event_date bigint(20) DEFAULT NULL,
  creation_date_time bigint(20) NOT NULL,
  state int(11) NOT NULL,
  user_id bigint(20) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 16,
AVG_ROW_LENGTH = 2048,
CHARACTER SET utf8,
COLLATE utf8_general_ci;

ALTER TABLE todolist_servlet.task
ADD INDEX deleted (state);

ALTER TABLE todolist_servlet.task
ADD INDEX eventDate (event_date);

ALTER TABLE todolist_servlet.task
ADD CONSTRAINT FK_task_user_id FOREIGN KEY (user_id)
REFERENCES todolist_servlet.user (id);

//

CREATE TABLE todolist_servlet.user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 5,
AVG_ROW_LENGTH = 5461,
CHARACTER SET utf8,
COLLATE utf8_general_ci;