DELETE FROM user_roles;
DELETE FROM vote;
DELETE FROM  menu;
DELETE FROM rating;
DELETE FROM  restaurants CASCADE ;
DELETE FROM  users CASCADE;

ALTER SEQUENCE global_seq RESTART WITH 100000;
-- ALTER SEQUENCE user_seq RESTART WITH 100000;
-- ALTER SEQUENCE rest_seq RESTART WITH 100000;
-- ALTER SEQUENCE menu_seq RESTART WITH 100000;
-- ALTER SEQUENCE rating_seq RESTART WITH 100000;
-- ALTER SEQUENCE vote_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
    ('Nata', 'nata@gmail.com', '11111'),
    ('Dima', 'dima@gmail.com', '22222'),
    ('Seva', 'seva@gmail.com', '33333');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100002);

INSERT INTO restaurants (name, menu, rating) VALUES
    ('MD', 3, 0),
    ('Burger', 3, 0),
    ('Cafe', 3, 0);

INSERT INTO menu (id_restaurant, dishes, price) VALUES
    (100003, 'dish 1', 100),
    (100003, 'dish 2', 150),
    (100003, 'dish 3', 130),
    (100004, '2dish 1', 200),
    (100004, '2dish 2', 250),
    (100004, '2dish 3', 230),
    (100005, '3dish 1', 300),
    (100005, '3dish 2', 350),
    (100005, '3dish 3', 320);

INSERT INTO rating (id_restaurant, count_vote, date_vote) VALUES
    (100003, 12, '2020-03-30'),
    (100004, 20, '2020-03-30'),
    (100005, 8, '2020-03-30'),
    (100003, 10, '2020-05-10'),
    (100004, 15, '2020-05-10'),
    (100005, 250, '2020-05-10'),
    (100003, 5, '2020-06-05'),
    (100004, 22, '2020-06-05'),
    (100005, 3, '2020-06-05');

INSERT INTO vote (id_user, id_restaurant, vote_date_time) VALUES
    (100000, 100005, '2020-06-05'),
    (100001, 100004, '2020-06-05'),
    (100002, 100003, '2020-06-05');

