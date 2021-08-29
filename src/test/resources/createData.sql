INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(1,24,'email1@gmail.com', 'Last name 1', 'Name 1', 0, 'Nickname 1', 'auth0|1111');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(2,24,'email2@gmail.com', 'Last name 2', 'Name 2', 0, 'Nickname 2', 'auth0|2222');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(3,24,'email3@gmail.com', 'Last name 3', 'Name 3', 0, 'Nickname 3', 'auth0|3333');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(4,24,'email4@gmail.com', 'Last name 4', 'Name 4', 0, 'Nickname 4', 'auth0|4444');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(5,24,'email5@gmail.com', 'Last name 5', 'Name 5', 0, 'Nickname 5', 'auth0|5555');
INSERT INTO User(id, age, email, lastname, name, emailVerified, nickname, sub) VALUES(6,24,'email6@gmail.com', 'Last name 6', 'Name 6', 0, 'Nickname 6', 'auth0|6666');

INSERT INTO Project(id, name, description) VALUES (1,'Project number: 1', 'This is description for project number 1');
INSERT INTO Project(id, name, description) VALUES (2,'Project number: 2', 'This is description for project number 2');
INSERT INTO Project(id, name, description) VALUES (3,'Project number: 3', 'This is description for project number 3');
INSERT INTO Project(id, name, description) VALUES (4,'Project number: 4', 'This is description for project number 4');
INSERT INTO Project(id, name, description) VALUES (5,'Project number: 5', 'This is description for project number 5');

INSERT INTO Issue(id, title, description, openedTime, closedTime, priority, createdById, projectId) VALUES 
    (1, 'Title for issue number 1', 'Description for issue number 1', '2021-08-18 17:04:04', NULL, 0, 1, 1),
    (2, 'Title for issue number 2', 'Description for issue number 2', '2021-08-18 17:04:04', NULL, 1, 2, 1),
    (3, 'Title for issue number 3', 'Description for issue number 3', '2021-08-18 17:04:04', '2021-09-15 14:25:01',1, 3, 2),
    (4, 'Title for issue number 4', 'Description for issue number 4', '2021-08-18 17:04:04', NULL,2, 4, 2),
    (5, 'Title for issue number 5', 'Description for issue number 5', '2021-08-18 17:04:04', '2021-08-18 21:20:05',2, 5, 1);