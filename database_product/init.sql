CREATE TABLE public.product (
    id SERIAL PRIMARY KEY,
    name varchar(255),
    price double precision,
    details varchar(255),
    categoryId int

);

INSERT INTO product (id, name, price, details ,categoryId) VALUES (0, 'club_mate', 1.3, 'lecker und spritzig' , 0);
INSERT INTO product (id, name, price, details, categoryId) VALUES (1, 'apple', 0.3, 'one per day', 1);
INSERT INTO product (id,name, price, details, categoryId) VALUES (2, 't_shirt', 10.0, 'made from happy sheeps', 2);