INSERT INTO Sphere(id, name) VALUES (1, 'Политика');
INSERT INTO Sphere(id, name) VALUES (2, 'Техника');
INSERT INTO Sphere(id, name) VALUES (3, 'Творчество');
INSERT INTO Sphere(id, name) VALUES (4, 'Кино');
INSERT INTO Sphere(id, name) VALUES (5, 'Другое');

INSERT INTO Sphere(id, name, parent_id) VALUES (101, 'Государственный строй', 1);
INSERT INTO Sphere(id, name, parent_id) VALUES (102, 'Политические режимы', 1);
INSERT INTO Sphere(id, name, parent_id) VALUES (103, 'Риторика', 1);
INSERT INTO Sphere(id, name, parent_id) VALUES (104, 'Либералы и консерваторы', 1);
INSERT INTO Sphere(id, name, parent_id) VALUES (105, 'ЗеКоманда', 1);

INSERT INTO Sphere(id, name, parent_id) VALUES (201, 'Инновации', 2);
INSERT INTO Sphere(id, name, parent_id) VALUES (202, 'Космос', 2);
INSERT INTO Sphere(id, name, parent_id) VALUES (203, 'Тесла', 2);
INSERT INTO Sphere(id, name, parent_id) VALUES (204, 'Роботы', 2);
INSERT INTO Sphere(id, name, parent_id) VALUES (205, 'Криптовалюта', 2);

INSERT INTO Sphere(id, name, parent_id) VALUES (301, 'Современное искусство', 3);
INSERT INTO Sphere(id, name, parent_id) VALUES (302, 'Художественные направления', 3);
INSERT INTO Sphere(id, name, parent_id) VALUES (303, 'Цензура', 3);
INSERT INTO Sphere(id, name, parent_id) VALUES (304, 'Арт искуство', 3);
INSERT INTO Sphere(id, name, parent_id) VALUES (305, 'Живопись', 3);

INSERT INTO Sphere(id, name, parent_id) VALUES (401, 'СМИ', 4);
INSERT INTO Sphere(id, name, parent_id) VALUES (402, 'Пропаганда', 4);
INSERT INTO Sphere(id, name, parent_id) VALUES (403, 'Сериалы', 4);
INSERT INTO Sphere(id, name, parent_id) VALUES (404, 'Мультфильмы', 4);
INSERT INTO Sphere(id, name, parent_id) VALUES (405, 'В прокате топ 5', 4);

INSERT INTO Sphere(id, name, parent_id) VALUES (501, 'Кризис средней специальности', 5);


INSERT INTO User_Statistics(id, activity, reputation) VALUES (1, 62, 44);
INSERT INTO User_Statistics(id, activity, reputation) VALUES (2, 80, 56);
INSERT INTO User_Statistics(id, activity, reputation) VALUES (3, 34, 15);
INSERT INTO User_Statistics(id, activity, reputation) VALUES (4, 98, 45);
INSERT INTO User_Statistics(id, activity, reputation) VALUES (5, 56, 52);

INSERT INTO Lobby_Statistics(id, debat_count, discussion_count) VALUES (1, 12, 5);
INSERT INTO Lobby_Statistics(id, debat_count, discussion_count) VALUES (2, 15, 11);
INSERT INTO Lobby_Statistics(id, debat_count, discussion_count) VALUES (3, 3, 1);
INSERT INTO Lobby_Statistics(id, debat_count, discussion_count) VALUES (4, 23, 6);
INSERT INTO Lobby_Statistics(id, debat_count, discussion_count) VALUES (5, 13, 3);

INSERT INTO User(id, email, name, password, lobby_statistics_id, statistics_id) VALUES (1, 'chda98@bk.ru', 'DimasikAlmazzik', '123456', 1, 1);
INSERT INTO User(id, email, name, password, lobby_statistics_id, statistics_id) VALUES (2, 'animeLiker@gmail.ru', 'Alfred', '234567', 2, 2);
INSERT INTO User(id, email, name, password, lobby_statistics_id, statistics_id) VALUES (3, 'messy@inbox.ru', 'amacrim', '345678', 3, 3);
INSERT INTO User(id, email, name, password, lobby_statistics_id, statistics_id) VALUES (4, 'sulmatUUl@bk.ru', 'magIvoin2001', '456789', 4, 4);
INSERT INTO User(id, email, name, password, lobby_statistics_id, statistics_id) VALUES (5, 'ZarinM@inbox.ru', 'CARstark', '567890', 5, 5);

INSERT INTO Theme(id, first_position, name, second_position, creator_id, background_image) VALUES (1, 'Java', 'Java vs C++', 'C++(Best of the Best)', 1, 'nizkij flex.jpg');
INSERT INTO Theme(id, first_position, name, second_position, creator_id, background_image) VALUES (2, 'Крым русский', 'Крым наш или не наш?', 'Украина', 2, 'slidone.jpg');
INSERT INTO Theme(id, first_position, name, second_position, creator_id, background_image) VALUES (3, 'Android', 'Android VS Apple', 'Apple', 3, 'svidetel.jpg');
INSERT INTO Theme(id, first_position, name, second_position, creator_id, background_image) VALUES (4, 'Прогрессивный либерализм', 'Либеры против Консерв', 'Тупые консерваторы', 4, 'tupaja_elf.jpg');
INSERT INTO Theme(id, first_position, name, second_position, creator_id, background_image) VALUES (5, 'Nikes', 'Найки и Adidas!!!', 'Adidas', 5, 'vanomas.jpg');
