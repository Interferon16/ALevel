-- Creating database

DROP DATABASE IF EXISTS social_network;
CREATE DATABASE IF NOT EXISTS social_network;
USE social_network;

-- Creating tables


DROP TABLE IF EXISTS 	person,
						foto,
						comments_to_foto,
                        likes_to_person,
                        likes_to_foto,
                        likes_to_comments_to_foto;
						
						
CREATE TABLE person (
id 				INT 			NOT NULL AUTO_INCREMENT,
first_name 		VARCHAR(20) 	NOT NULL,
last_name 		VARCHAR(20) 	NOT NULL,
PRIMARY KEY (id)
);

CREATE TABLE foto (
id				INT 			NOT NULL AUTO_INCREMENT,
id_from_person	INT 			NOT NULL,
url				VARCHAR(255) 	NOT NULL,
FOREIGN KEY (id_from_person) REFERENCES person (id) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY (id)
);

CREATE TABLE comments_to_foto (
id					INT 			NOT NULL AUTO_INCREMENT,
to_foto_id			INT				NOT NULL,
id_from_person		INT 			NOT NULL,
com_text 			VARCHAR(255),
FOREIGN KEY (id_from_person) REFERENCES person (id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (to_foto_id) REFERENCES foto (id) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY (id)
);

CREATE TABLE likes_to_person (
id 					INT 			NOT NULL AUTO_INCREMENT,
to_person_id		INT				NOT NULL,
id_from_person 		INT 			NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY (to_person_id,id_from_person),
FOREIGN KEY (id_from_person) REFERENCES person (id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (to_person_id) REFERENCES person (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE likes_to_foto (
id 					INT 			NOT NULL AUTO_INCREMENT,
to_foto_id			INT				NOT NULL,
id_from_person 		INT 			NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY (to_foto_id,id_from_person),
FOREIGN KEY (id_from_person) REFERENCES person (id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (to_foto_id) REFERENCES foto (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE likes_to_comments_to_foto (
id 					INT 			NOT NULL AUTO_INCREMENT,
to_foto_comments_id	INT				NOT NULL,
id_from_person 		INT 			NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY (to_foto_comments_id,id_from_person),
FOREIGN KEY (id_from_person) REFERENCES person (id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (to_foto_comments_id) REFERENCES comments_to_foto (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- creation procedures for like or revoke like

DROP procedure IF EXISTS `like_to_person_from_person_create_delete`;

DELIMITER $$
USE `social_network`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `like_to_person_from_person_create_delete`(to_person INT,from_person INT)
BEGIN
if exists(SELECT * FROM likes_to_person WHERE to_person_id = to_person AND id_from_person=from_person) then
delete FROM likes_to_person WHERE to_person_id = to_person AND id_from_person=from_person;
else
INSERT INTO likes_to_person VALUES(null,to_person,from_person);
END IF;
END$$

DELIMITER ;

DROP procedure IF EXISTS `like_to_foto_from_person_create_delete`;

DELIMITER $$
USE `social_network`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `like_to_foto_from_person_create_delete`(to_foto INT,from_person INT)
BEGIN
if exists(SELECT * FROM likes_to_foto WHERE to_foto_id = to_foto AND id_from_person=from_person) then
delete FROM likes_to_foto WHERE to_foto_id = to_foto AND id_from_person=from_person;
else
INSERT INTO likes_to_foto VALUES(null,to_foto,from_person);
END IF;
END$$

DELIMITER ;

DROP procedure IF EXISTS `like_to_comments_to_foto_from_person_create_delete`;

DELIMITER $$
USE `social_network`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `like_to_comments_to_foto_from_person_create_delete`(to_foto_comments INT,from_person INT)
BEGIN
if exists(SELECT * FROM likes_to_comments_to_foto WHERE to_foto_comments_id = to_foto_comments AND id_from_person=from_person) then
delete FROM likes_to_comments_to_foto WHERE to_foto_comments_id = to_foto_comments AND id_from_person=from_person;
else
INSERT INTO likes_to_comments_to_foto VALUES(null,to_foto_comments,from_person);
END IF;
END$$

DELIMITER ;