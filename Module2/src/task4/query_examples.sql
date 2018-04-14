


-- добавить пользователя c именем и фамилией
INSERT INTO social_network.entity VALUES (null);
INSERT INTO social_network.person VALUES (last_insert_id(),'имя','фамилия');

-- добавить фото от пользователя с ид 16, название 'foto' и урл фотки 'https://vignette.wikia.nocookie.net/blogclan-2/images/4/45/Random-turtle.gif'
INSERT INTO social_network.entity VALUES (null);
INSERT INTO social_network.foto VALUES(last_insert_id(),16,'foto','https://vignette.wikia.nocookie.net/blogclan-2/images/4/45/Random-turtle.gif');

-- добавить комментарий к сущности с ид 10 от пользователя 16 с текстом 'randomtext599631'
INSERT INTO social_network.entity VALUES (null);
INSERT INTO social_network.comments VALUES(last_insert_id(),16,10,'randomtext599631');

-- лайк персоной 57 сущность 16 или удалить если существует
call like_from_person_to_entity_create_delete(57,16);






-- количество лайков сущности с ид 16
SELECT COUNT(person.id) AS 'likes' FROM person
INNER JOIN likes ON(likes.id_from_person=person.id)
where likes.id_to_entity=16;

-- список лайкнувших сущность с ид 16
SELECT person.first_name, person.last_name FROM person
INNER JOIN likes ON(likes.id_from_person=person.id)
where likes.id_to_entity=16;
