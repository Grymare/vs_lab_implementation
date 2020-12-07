CREATE TABLE public.category (
    id SERIAL PRIMARY KEY,
    name varchar(255)
);
INSERT INTO category (name) VALUES ('getraenke');
INSERT INTO category (name) VALUES ('lebensmittel');
INSERT INTO category (name) VALUES ('kleidung');