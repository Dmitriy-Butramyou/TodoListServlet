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