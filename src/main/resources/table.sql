CREATE TABLE client
(
    id           INTEGER PRIMARY KEY,
    username     TEXT,
    password     TEXT,
    role         TEXT,
    firstName    TEXT,
    lastName     TEXT,
    phone        TEXT,
    email        TEXT,
    regDate      TEXT,
    city         TEXT,
    image        TEXT
);


CREATE TABLE comment
(
    pk           INTEGER PRIMARY KEY,
    createdAt    TEXT,
    text         TEXT,
    author       INTEGER REFERENCES client(id)
);


CREATE TABLE ad
(
    pk           INTEGER PRIMARY KEY,
    title        TEXT,
    price        INTEGER,
    text         TEXT,
    image        TEXT,
    description  TEXT,
    author       INTEGER REFERENCES client(id)
);



