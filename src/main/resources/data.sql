INSERT INTO Spheres(id, name) VALUES (1, 'Политика');
INSERT INTO Spheres(id, name) VALUES (2, 'Техника');
INSERT INTO Spheres(id, name) VALUES (3, 'Творчество');
INSERT INTO Spheres(id, name) VALUES (4, 'Кино');
INSERT INTO Spheres(id, name) VALUES (6, 'IT');
INSERT INTO Spheres(id, name) VALUES (5, 'Другое');

INSERT INTO Spheres(id, name, parent_id) VALUES (101, 'Государственный строй', 1);
INSERT INTO Spheres(id, name, parent_id) VALUES (102, 'Политические режимы', 1);
INSERT INTO Spheres(id, name, parent_id) VALUES (103, 'Риторика', 1);
INSERT INTO Spheres(id, name, parent_id) VALUES (104, 'Либералы и консерваторы', 1);
INSERT INTO Spheres(id, name, parent_id) VALUES (105, 'ЗеКоманда', 1);

INSERT INTO Spheres(id, name, parent_id) VALUES (201, 'Инновации', 2);
INSERT INTO Spheres(id, name, parent_id) VALUES (202, 'Космос', 2);
INSERT INTO Spheres(id, name, parent_id) VALUES (203, 'Тесла', 2);
INSERT INTO Spheres(id, name, parent_id) VALUES (204, 'Роботы', 2);
INSERT INTO Spheres(id, name, parent_id) VALUES (205, 'Криптовалюта', 2);
INSERT INTO Spheres(id, name, parent_id) VALUES (206, 'Операционная система', 2);

INSERT INTO Spheres(id, name, parent_id) VALUES (301, 'Современное искусство', 3);
INSERT INTO Spheres(id, name, parent_id) VALUES (302, 'Художественные направления', 3);
INSERT INTO Spheres(id, name, parent_id) VALUES (303, 'Цензура', 3);
INSERT INTO Spheres(id, name, parent_id) VALUES (304, 'Арт искуство', 3);
INSERT INTO Spheres(id, name, parent_id) VALUES (305, 'Живопись', 3);

INSERT INTO Spheres(id, name, parent_id) VALUES (401, 'СМИ', 4);
INSERT INTO Spheres(id, name, parent_id) VALUES (402, 'Пропаганда', 4);
INSERT INTO Spheres(id, name, parent_id) VALUES (403, 'Сериалы', 4);
INSERT INTO Spheres(id, name, parent_id) VALUES (404, 'Мультфильмы', 4);
INSERT INTO Spheres(id, name, parent_id) VALUES (405, 'В прокате топ 5', 4);

INSERT INTO Spheres(id, name, parent_id) VALUES (501, 'Кризис средней специальности', 5);

INSERT INTO Spheres(id, name, parent_id) VALUES (601, 'Java', 6);
INSERT INTO Spheres(id, name, parent_id) VALUES (602, 'C++', 6);
INSERT INTO Spheres(id, name, parent_id) VALUES (603, 'JS', 6);
INSERT INTO Spheres(id, name, parent_id) VALUES (604, 'SQL', 6);
INSERT INTO Spheres(id, name, parent_id) VALUES (605, '.NET', 6);
INSERT INTO Spheres(id, name, parent_id) VALUES (606, 'Python', 6);


INSERT INTO 
User_Statistics(id, activity, reputation) 
VALUES 
(1, 62, 44), (2, 80, 56), (3, 34, 15), (4, 98, 45), 
(5, 56, 52), (6, 50, 58), (7, 56, 52);

INSERT INTO 
Lobby_Statistics(id, debat_count, discussion_count) 
VALUES 
(1, 12, 5), (2, 15, 11), (3, 3, 1), (4, 23, 6), 
(5, 13, 3), (6, 15, 4), (7, 8, 1);

INSERT INTO Users
(id, email, login, password, lobby_statistics_id, statistics_id, user_image) 
VALUES 
(1, 'chda98@bk.ru', 'DimasikAlmazzik', '123456', 1, 1, '1.jpg'),
(2, 'animeLiker@gmail.ru', 'Alfred', '234567', 2, 2, '2.jpg'),
(3, 'messy@inbox.ru', 'amacrim', '345678', 3, 3, '3.jpg'),
(4, 'sulmatUUl@bk.ru', 'magIvoin2001', '456789', 4, 4, '4.jpg'),
(5, 'ZarinM@inbox.ru', 'CARstark', '567890', 5, 5, '5.jpg'),
(6, 'admin@inbox.ru', 'admin', 'admin', 6, 6, '6.jpg'),
(7, 'user@inbox.ru', 'user', 'user', 7, 7, '7.jpg');

INSERT INTO 
Roles(id, roles) 
VALUES 
(1,'admin'), (2,'user'), (3,'moderator');

INSERT INTO users_has_roles
(users_id, roles_id) 
VALUES 
(2,2), (1,1), (1,3), (6,1), (7,2);

INSERT INTO themes
(id, first_position, name, second_position, creator_id, background_image, sphere_id) 
VALUES 
(1, 'Java', 'Java vs C++', 'C++(Best of the Best)', 1, 'themes/nizkij flex.jpg', 601),
(2, 'Крым русский', 'Крым наш или не наш?', 'Украина', 2, 'themes/slidone.jpg', 601),
(3, 'Android', 'Android VS Apple', 'Apple', 3, 'themes/svidetel.jpg', 601),
(4, 'Прогрессивный либерализм', 'Либеры против Консерв', 'Тупые консерваторы', 4, 'themes/tupaja_elf.jpg', 603),
(9, 'Nikes', 'Найки и Adidas!!!', 'Adidas', 5, 'themes/vanomas.jpg', 603),
(5, 'Java', 'Java vs JS', 'JS', 1, 'themes/nizkij flex.jpg', 601),
(6, 'Java', 'Java vs SQL', 'SQL', 1, 'themes/nizkij flex.jpg', 601),
(7, 'Java', 'Java vs .NET', 'C++(Best of the Best)', 1, 'themes/nizkij flex.jpg', 601),
(8, 'Java', 'Java vs Python', 'Python', 1, 'themes/nizkij flex.jpg', 601);

INSERT INTO 
Spheres_Themes(sphere_id, themes_id) 
VALUES 
(601, 1), (601, 5), (601, 6), (601, 7), (601, 8), (101, 2), (206, 3), (104, 4);
