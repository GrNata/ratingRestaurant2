DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS rating;
DROP TABLE IF EXISTS restaurants CASCADE ;
DROP TABLE IF EXISTS users CASCADE;
DROP SEQUENCE IF EXISTS user_seq;
DROP SEQUENCE IF EXISTS rest_seq;
DROP SEQUENCE IF EXISTS menu_seq;
DROP SEQUENCE IF EXISTS rating_seq;
DROP SEQUENCE IF EXISTS vote_seq;


CREATE SEQUENCE user_seq START WITH 100000;
CREATE SEQUENCE rest_seq START WITH 100000;
CREATE SEQUENCE menu_seq START WITH 100000;
CREATE SEQUENCE rating_seq START WITH 100000;
CREATE SEQUENCE vote_seq START WITH 100000;


CREATE TABLE users
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
    name            VARCHAR                 NOT NULL,
    email           VARCHAR                 NOT NULL,
    password        VARCHAR                 NOT NULL,
    registered      TIMESTAMP DEFAULT now() NOT NULL,
    enable          BOOL DEFAULT TRUE       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
--     user_id     INTEGER REFERENCES users (id) ON DELETE CASCADE,
    user_id INTEGER NOT NULL,
    role        VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('rest_seq'),
    name            VARCHAR                 NOT NULL,
    menu            INTEGER,
    rating          INTEGER
);

CREATE TABLE menu
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('menu_seq'),
    idRestaurant    INTEGER REFERENCES restaurants (id) ON DELETE CASCADE,
    dishes          VARCHAR             NOT NULL,
    price           INTEGER             NOT NULL
--     FOREIGN KEY (idRestaurant) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE rating
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('rating_seq'),
--     idRestaurant    INTEGER REFERENCES restaurants (id) ON DELETE CASCADE,
    id_restaurant   INTEGER             NOT NULL,
    count_vote       INTEGER,
    date_vote        TIMESTAMP           NOT NULL,
    FOREIGN KEY (id_restaurant) REFERENCES restaurants (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    id              INTEGER PRIMARY KEY DEFAULT nextval('vote_seq'),
    id_user          INTEGER REFERENCES users (id) ON DELETE CASCADE,
    id_restaurant    INTEGER REFERENCES restaurants (id) ON DELETE CASCADE,
    vote_date_time    TIMESTAMP           NOT NULL
--     FOREIGN KEY (idUser) REFERENCES users (id) ON DELETE CASCADE,
--     FOREIGN KEY (idRestaurant) REFERENCES restaurants (id) ON DELETE CASCADE
);