INSERT INTO Sphere(id, name) VALUES (1, 'Политика');
INSERT INTO Sphere(id, name) VALUES (2, 'Техника');
INSERT INTO Sphere(id, name) VALUES (3, 'Творчество');
INSERT INTO Sphere(id, name) VALUES (4, 'Кино');
INSERT INTO Sphere(id, name) VALUES (5, 'Другое');

INSERT INTO Sphere(id, name, parent_id) VALUES (11, 'Государственный строй', 1);
INSERT INTO Sphere(id, name, parent_id) VALUES (12, 'Политические режимы', 1);
INSERT INTO Sphere(id, name, parent_id) VALUES (13, 'Риторика', 1);
INSERT INTO Sphere(id, name, parent_id) VALUES (14, 'Либералы и консерваторы', 1);

INSERT INTO Sphere(id, name, parent_id) VALUES (21, 'Инновации', 2);

INSERT INTO Sphere(id, name, parent_id) VALUES (31, 'Современное искусство', 3);
INSERT INTO Sphere(id, name, parent_id) VALUES (32, 'Художественные направления', 3);
INSERT INTO Sphere(id, name, parent_id) VALUES (33, 'Цензура', 3);

INSERT INTO Sphere(id, name, parent_id) VALUES (41, 'СМИ', 4);
INSERT INTO Sphere(id, name, parent_id) VALUES (42, 'Пропаганда', 4);

INSERT INTO Sphere(id, name, parent_id) VALUES (51, 'Кризис средней специальности', 5);


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

INSERT INTO Theme(id, first_position, name, second_position, creator_id) VALUES (1, 'Java', 'Java vs C++', 'C++(Best of the Best)', 1);
INSERT INTO Theme(id, first_position, name, second_position, creator_id) VALUES (2, 'Крым русский', 'Крым наш или не наш?', 'Украина', 2);
INSERT INTO Theme(id, first_position, name, second_position, creator_id) VALUES (3, 'Android', 'Android VS Apple', 'Apple', 3);
INSERT INTO Theme(id, first_position, name, second_position, creator_id) VALUES (4, 'Прогрессивный либерализм', 'Либеры против Консерв', 'Тупые консерваторы', 4);
INSERT INTO Theme(id, first_position, name, second_position, creator_id) VALUES (5, 'Nikes', 'Найки и Adidas!!!', 'Adidas', 5);
