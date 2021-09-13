CREATE TABLE public.account(
    id SERIAL PRIMARY KEY,
    username varchar(255) UNIQUE,
    firstname varchar(255),
    lastname varchar(255),
    password varchar(255),
    permission int
);

INSERT INTO account (username, firstname, lastname, password, permission) VALUES ('admin','admin','admin','admin', 0);
INSERT INTO account (username, firstname, lastname, password, permission) VALUES ('user','user','user','user', 1);