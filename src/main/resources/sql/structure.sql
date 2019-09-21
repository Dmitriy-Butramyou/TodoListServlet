CREATE DATABASE todolist_servlet
CHARACTER SET utf8
COLLATE utf8_general_ci;

CREATE TABLE todolist_servlet.user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  password varchar(255) NOT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 1,
AVG_ROW_LENGTH = 2730,
CHARACTER SET utf8,
COLLATE utf8_general_ci;

CREATE TABLE todolist_servlet.task (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  description varchar(512) DEFAULT NULL,
  event_date bigint(20) DEFAULT NULL,
  creation_date_time bigint(20) NOT NULL,
  state int(11) NOT NULL,
  user_id bigint(20) NOT NULL,
  original_file_name varchar(255) DEFAULT NULL,
  generated_file_name varchar(255) DEFAULT NULL,
  generated_file_path varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB,
AUTO_INCREMENT = 1,
AVG_ROW_LENGTH = 16384,
CHARACTER SET utf8,
COLLATE utf8_general_ci;

ALTER TABLE todolist_servlet.task
ADD CONSTRAINT FK_task_user_id FOREIGN KEY (user_id)
REFERENCES todolist_servlet.user (id);