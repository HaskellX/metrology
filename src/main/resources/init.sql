INSERT INTO public.author (id, alias, birth_place, birthday, genre, name) VALUES (1, 'Harry', 'Canada', '1987-05-26', 'Джаз', 'Иван Иванович');
INSERT INTO public.author (id, alias, birth_place, birthday, genre, name) VALUES (2, 'Сердючка', 'Украина', '1974-10-08', 'Поп', 'Миша Петровкин');

INSERT INTO public.album (id, collection, genre, release_date, single, title, author) VALUES (1, true, 'Джаз', '2014-01-07', true, 'Супер Альбом', 1);
INSERT INTO public.album (id, collection, genre, release_date, single, title, author) VALUES (2, true, 'Поп', '2015-07-17', false, 'Музон', 2);
INSERT INTO public.album (id, collection, genre, release_date, single, title, author) VALUES (3, false, 'Хип Хоп', '2017-02-07', false, 'Взрыв Мозга', 2);

INSERT INTO public.track (id, created, duration, title, album) VALUES (1, '2017-08-10', 306, 'Poker Face', 1);