DELETE FROM user_roles;
DELETE FROM vote;
DELETE FROM  menu;
DELETE FROM rating;
DELETE FROM  restaurants CASCADE ;
DELETE FROM  users CASCADE;

ALTER SEQUENCE user_seq RESTART WITH 100000;
ALTER SEQUENCE rest_seq RESTART WITH 100000;
ALTER SEQUENCE menu_seq RESTART WITH 100000;
ALTER SEQUENCE rating_seq RESTART WITH 100000;
ALTER SEQUENCE vote_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
    ('Nata', 'nata@gmail.com', '1111'),
    ('Dima', 'dima@gmail.com', '2222'),
    ('Seva', 'seva@gmail.com', '3333');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100002);

INSERT INTO restaurants (name, menu, rating) VALUES
    ('MD', 3, 0),
    ('Burger', 3, 0),
    ('Cafe', 3, 0);

INSERT INTO menu (idrestaurant, dishes, price) VALUES
    (100000, 'dish 1', 100),
    (100000, 'dish 2', 150),
    (100000, 'dish 3', 130),
    (100001, '2dish 1', 200),
    (100001, '2dish 2', 250),
    (100001, '2dish 3', 230),
    (100002, '3dish 1', 300),
    (100002, '3dish 2', 350),
    (100002, '3dish 3', 320);

INSERT INTO rating (idrestaurant, countvote, datevote) VALUES
    (100000, 12, '2020-03-30'),
    (100001, 20, '2020-03-30'),
    (100002, 8, '2020-03-30'),
    (100000, 10, '2020-05-10'),
    (100001, 15, '2020-05-10'),
    (100002, 250, '2020-05-10'),
    (100000, 5, '2020-06-05'),
    (100001, 22, '2020-06-05'),
    (100002, 3, '2020-06-05');

INSERT INTO vote (iduser, idrestaurant, votedatetime) VALUES
    (100000, 100002, '2020-06-05 10:00:00'),
    (100001, 100001, '2020-06-05 10:00:00'),
    (100002, 100000, '2020-06-05 10:00:00');

