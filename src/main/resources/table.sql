CREATE TABLE client
(
    id           SERIAL PRIMARY KEY,
    username     TEXT UNIQUE,
    password     TEXT,
    role         TEXT,
    first_name    TEXT,
    last_name     TEXT,
    phone        TEXT,
    email        TEXT,
    reg_date      TEXT,
    city         TEXT,
    image        TEXT
);


CREATE TABLE comment
(
    pk           SERIAL PRIMARY KEY,
    created_at   TEXT,
    text         TEXT,
    author       INTEGER REFERENCES client(id)
);


CREATE TABLE ad
(
    pk           SERIAL PRIMARY KEY,
    title        TEXT,
    price        INTEGER,
    text         TEXT,
    image        TEXT,
    description  TEXT,
    author       INTEGER REFERENCES client(id)
);



