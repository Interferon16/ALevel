-- Creating database

DROP DATABASE IF EXISTS social_network;
CREATE DATABASE IF NOT EXISTS social_network;
USE social_network;

-- Creating tables


DROP TABLE IF EXISTS 	entity,
						person,
						foto,
						comments,
                        likes;
						
CREATE TABLE entity (
id 				INT 		NOT NULL auto_increment,
PRIMARY KEY (id)
);

CREATE TABLE person (
id 				INT 			NOT NULL,
first_name 		VARCHAR(20) 	NOT NULL,
last_name 		VARCHAR(20) 	NOT NULL,
FOREIGN KEY (id) REFERENCES entity (id) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY (id)
);

CREATE TABLE foto (
id 				INT 			NOT NULL,
id_from_person	INT 			NOT NULL,
title			VARCHAR(100)	NOT NULL,
url				VARCHAR(255) 	NOT NULL,
FOREIGN KEY (id) REFERENCES entity (id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_from_person) REFERENCES person (id) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY (id)
);

CREATE TABLE comments (
id 					INT 	NOT NULL,
id_from_person		INT 	NOT NULL,
id_to_entity		INT 	NOT NULL,
com_text 			VARCHAR(255),
PRIMARY KEY (id),
FOREIGN KEY (id) REFERENCES entity (id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_from_person) REFERENCES person (id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_to_entity) REFERENCES entity (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE likes (
id 					INT 	NOT NULL auto_increment,
id_from_person 		INT 	NOT NULL,
id_to_entity		INT 	NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY (id_to_entity,id_from_person),
FOREIGN KEY (id_to_entity) REFERENCES entity (id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (id_from_person) REFERENCES person (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- creation procedures for like or revoke like

DROP procedure IF EXISTS `like_from_person_to_entity_create_delete`;

DELIMITER $$
USE `social_network`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `like_from_person_to_entity_create_delete`(from_person INT,to_entity INT)
BEGIN
if exists(SELECT * FROM likes WHERE id_to_entity = to_entity AND id_from_person=from_person) then
delete FROM likes WHERE id_to_entity = to_entity AND id_from_person=from_person;
else
INSERT INTO likes VALUES(null,from_person,to_entity);
END IF;
END$$

DELIMITER ;
