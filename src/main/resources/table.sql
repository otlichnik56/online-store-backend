CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    username     TEXT UNIQUE,
    password     TEXT,
    enabled      TEXT,
    role         TEXT,
    first_name   TEXT,
    last_name    TEXT,
    phone        TEXT,
    email        TEXT,
    reg_date     TEXT,
    city         TEXT,
    image        TEXT
);


CREATE TABLE ads
(
    pk           SERIAL PRIMARY KEY,
    title        TEXT,
    price        INTEGER,
    text         TEXT,
    image        TEXT,
    description  TEXT,
    author       INTEGER REFERENCES users(id)
);


CREATE TABLE comments
(
    pk           SERIAL PRIMARY KEY,
    created_at   TEXT,
    text         TEXT,
    author       INTEGER REFERENCES users(id),
    ads_pk       INTEGER REFERENCES ads(pk)
);

CREATE TABLE images
(
    id SERIAL references ads(pk),
    file_size bigint, 
    media_type text, 
    file_name text,
    data bytea
);

