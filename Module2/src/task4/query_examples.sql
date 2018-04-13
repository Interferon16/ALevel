


-- добавить пользователя c именем и фамилией
INSERT INTO social_network.person VALUES (null,'Georgi','Facello');

-- добавить фото от пользователя с ид 16 и урл фотки 'https://vignette.wikia.nocookie.net/blogclan-2/images/4/45/Random-turtle.gif'
INSERT INTO social_network.foto VALUES(null,16,'https://vignette.wikia.nocookie.net/blogclan-2/images/4/45/Random-turtle.gif');

-- добавить комментарий к фото с ид 875 от пользователя 16 с текстом 'randomtext599631'
INSERT INTO social_network.comments_to_foto VALUES(null,875,16,'randomtext599631');

-- лайк персоне с ид 3 от персоны с ид 16 или удалить если существует
call like_to_person_from_person_create_delete(3,16);

-- лайк фото с ид 1002 от персоны с ид 16
call like_to_foto_from_person_create_delete(1002,16);

-- лайк комментарию с ид 1002 от персоны с ид 16
call like_to_comments_to_foto_from_person_create_delete(1002,16);



-- количество лайков фотки с ид 16
SELECT COUNT(person.id) AS 'likes' FROM person
INNER JOIN likes_to_foto lf ON(lf.id_FROM_person=person.id)
where lf.to_foto_id=16;

-- список лайкнувших фотку с ид 16
SELECT concat(person.first_name,' ', person.lASt_name) AS 'person likes' FROM person
INNER JOIN likes_to_foto lf ON(lf.id_FROM_person=person.id)
where lf.to_foto_id=16;

-- количество лайков человека с ид 16
SELECT COUNT(person.id) AS 'likes' FROM person
INNER JOIN likes_to_person lp ON(lp.id_FROM_person=person.id)
where lp.to_person_id=16;

-- список лайкнувших человека с ид 16
SELECT person.first_name AS 'name', person.lASt_name AS 'last name'  FROM person
INNER JOIN likes_to_person lp ON(lp.id_FROM_person=person.id)
where lp.to_person_id=16;

-- количество лайков комментария с ид 16
SELECT COUNT(person.id) AS 'likes' FROM person
INNER JOIN likes_to_comments_to_foto lcf ON(lcf.id_FROM_person=person.id)
where lcf.to_foto_comments_id=16;

-- список лайкнувших комментарий с ид 16
SELECT person.first_name AS 'name', person.lASt_name AS 'last name'  FROM person
INNER JOIN likes_to_comments_to_foto lcf ON(lcf.id_FROM_person=person.id)
where lcf.to_foto_comments_id=16;