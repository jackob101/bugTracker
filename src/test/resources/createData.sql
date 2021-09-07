INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(1,24,'email1@gmail.com', 'Last name 1', 'Name 1', 0, 'Nickname 1', 'auth0|1111');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(2,24,'email2@gmail.com', 'Last name 2', 'Name 2', 0, 'Nickname 2', 'auth0|2222');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(3,24,'email3@gmail.com', 'Last name 3', 'Name 3', 0, 'Nickname 3', 'auth0|3333');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(4,24,'email4@gmail.com', 'Last name 4', 'Name 4', 0, 'Nickname 4', 'auth0|4444');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(5,24,'email5@gmail.com', 'Last name 5', 'Name 5', 0, 'Nickname 5', 'auth0|5555');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(6,24,'email6@gmail.com', 'Last name 6', 'Name 6', 0, 'Nickname 6', 'auth0|6666');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(7,24,'email7@gmail.com', 'Last name 7', 'Name 7', 0, 'Nickname 7', 'auth0|7777');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(8,24,'email8@gmail.com', 'Last name 8', 'Name 8', 0, 'Nickname 8', 'auth0|8888');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(9,24,'email9@gmail.com', 'Last name 9', 'Name 9', 0, 'Nickname 9', 'auth0|9999');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(10,24,'email10@gmail.com', 'Last name 10', 'Name 10', 0, 'Nickname 10', 'auth0|10101010');

INSERT INTO Project(id, name, description) VALUES (1,'Project number: 1', 'This is description for project number 1');
INSERT INTO Project(id, name, description) VALUES (2,'Project number: 2', 'This is description for project number 2');
INSERT INTO Project(id, name, description) VALUES (3,'Project number: 3', 'This is description for project number 3');
INSERT INTO Project(id, name, description) VALUES (4,'Project number: 4', 'This is description for project number 4');
INSERT INTO Project(id, name, description) VALUES (5,'Project number: 5', 'This is description for project number 5');

INSERT INTO Issue(id, title, description, openedTime, closedTime, priority, createdById, projectId) VALUES 
    (1, 'Title for issue number 1', 'Description for issue number 1', '2021-08-18 17:04:04', NULL, 0, 1, 1),
    (2, 'Title for issue number 2', 'Description for issue number 2', '2021-08-18 17:04:04', NULL, 1, 1, 1),
    (3, 'Title for issue number 3', 'Description for issue number 3', '2021-08-18 17:04:04', '2021-09-15 14:25:01',1, 1, 1),
    (4, 'Title for issue number 4', 'Description for issue number 4', '2021-08-18 17:04:04', NULL,2, 1, 1),
    (5, 'Title for issue number 5', 'Description for issue number 5', '2021-08-18 17:04:04', '2021-08-18 21:20:05',2, 1, 1),
    (6, 'Title for issue number 6', 'Description for issue number 6', '2021-08-18 17:06:06', NULL,2, 2, 2),
    (7, 'Title for issue number 7', 'Description for issue number 7', '2021-08-18 17:07:07', NULL,2, 2, 2),
    (8, 'Title for issue number 8', 'Description for issue number 8', '2021-08-18 17:08:08', '2021-08-18 21:20:05',2, 2, 2),
    (9, 'Title for issue number 9', 'Description for issue number 9', '2021-08-18 17:09:09', NULL,2, 3, 2),
    (10, 'Title for issue number 10', 'Description for issue number 10', '2021-08-18 17:10:10', NULL,2, 3, 3),
    (11, 'Title for issue number 11', 'Description for issue number 11', '2021-08-18 17:11:11', NULL,1, 4, 3),
    (12, 'Title for issue number 12', 'Description for issue number 12', '2021-08-18 17:12:12', '2021-08-18 21:20:05',1, 4, 3),
    (13, 'Title for issue number 13', 'Description for issue number 13', '2021-08-18 17:13:13', NULL,1, 4, 3),
    (14, 'Title for issue number 14', 'Description for issue number 14', '2021-08-18 17:14:14', NULL,1, 4, 3), 
    (15, 'Title for issue number 15', 'Description for issue number 15', '2021-08-18 17:15:15', NULL,1, 5, 3),
    (16, 'Title for issue number 16', 'Description for issue number 16', '2021-08-18 17:16:16', NULL,1, 5, 3),
    (17, 'Title for issue number 17', 'Description for issue number 17', '2021-08-18 17:17:17', '2021-08-18 21:20:05',0, 6, 3),
    (18, 'Title for issue number 18', 'Description for issue number 18', '2021-08-18 17:18:18', NULL,0, 7, 4),
    (19, 'Title for issue number 19', 'Description for issue number 19', '2021-08-18 17:19:19', NULL,0, 8, 4),
    (20, 'Title for issue number 20', 'Description for issue number 20', '2021-08-18 17:20:20', NULL,0, 7, 4),
    (21, 'Title for issue number 21', 'Description for issue number 21', '2021-08-18 17:21:21', '2021-08-18 21:20:05',1, 7, 4),
    (22, 'Title for issue number 22', 'Description for issue number 22', '2021-08-18 17:22:22', NULL,1, 8, 4),
    (23, 'Title for issue number 23', 'Description for issue number 23', '2021-08-18 17:23:23', NULL,2, 9, 4),
    (24, 'Title for issue number 24', 'Description for issue number 24', '2021-08-18 17:24:24', '2021-08-18 21:20:05',2, 10, 4),
    (25, 'Title for issue number 25', 'Description for issue number 25', '2021-08-18 17:25:25', NULL,2, 10, 4);


INSERT INTO Issue_User(issue_id, user_id) VALUES (1,1),(1,2),(4,3),(4,2),(4,4),(10,5),(10,6),(10,4),(22,10),(22,4);
INSERT INTO Project_User(project_id, user_id) VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(3,2),(3,4),(4,6),(4,7),(4,9);

INSERT INTO Comment(id, comment, user_id, issue_id, creationDate) VALUES
(1, 'This is example comment number 1', 1, 1, '2021-08-18 17:01:01'),
(2, 'This is example comment number 2', 2, 1, '2022-08-28 17:02:02'),
(3, 'This is example comment number 3', 2, 1, '2023-08-28 17:03:03'),
(4, 'This is example comment number 4', 2, 1, '2024-08-28 17:04:04'),
(5, 'This is example comment number 5', 1, 1, '2025-08-18 17:05:05'),
(6, 'This is example comment number 6', 2, 2, '2026-08-18 17:06:06'),
(7, 'This is example comment number 7', 2, 2, '2027-08-18 17:07:07'),
(8, 'This is example comment number 8', 3, 3, '2028-08-18 17:08:08'),
(9, 'This is example comment number 9', 3, 3, '2029-08-18 17:09:09'),
(10, 'This is example comment number 10', 3, 3, '2029-08-18 17:09:09'),
(11, 'This is example comment number 11', 5, 10, '2029-08-18 17:09:09'),
(12, 'This is example comment number 12', 3, 10, '2029-08-18 17:09:09'),
(13, 'This is example comment number 13', 5, 10, '2029-08-18 17:09:09'),
(14, 'This is example comment number 14', 3, 10, '2029-08-18 17:09:09'),
(15, 'This is example comment number 15', 3, 15, '2029-08-18 17:09:09'),
(16, 'This is example comment number 16', 7, 15, '2029-08-18 17:09:09'),
(17, 'This is example comment number 17', 7, 15, '2029-08-18 17:09:09'),
(18, 'This is example comment number 18', 6, 15, '2029-08-18 17:09:09'),
(19, 'This is example comment number 19', 3, 20, '2029-08-18 17:09:09'),
(20, 'This is example comment number 20', 3, 20, '2029-08-18 17:09:09');
