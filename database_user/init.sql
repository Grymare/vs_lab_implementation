CREATE TABLE public.account(
    id SERIAL PRIMARY KEY,
    username varchar(255),
    firstname varchar(255),
    lastname varchar(255),
    password varchar(255),
    permission int
);

INSERT INTO account (username, firstname, lastname, password, permission) VALUES ('bech','christian','becker','asdf', 0);
INSERT INTO account (username, firstname, lastname, password, permission) VALUES ('grymare','hendrik','icet','wasd', 1);
INSERT INTO account (username, firstname, lastname, password, permission) VALUES ('alphapfote','jana','becker','qwas', 1);