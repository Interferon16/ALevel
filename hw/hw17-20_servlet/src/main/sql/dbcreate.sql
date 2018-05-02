DROP DATABASE IF EXISTS site;
CREATE DATABASE IF NOT EXISTS site;
USE site;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
`id` 		INT 		NOT NULL auto_increment,
`name` 		VARCHAR(30) NOT NULL,
`email` 	VARCHAR(30) NOT NULL,
`password` 	VARCHAR(30) NOT NULL,
PRIMARY KEY(id),
UNIQUE KEY(`name`),
UNIQUE KEY(`email`)
);