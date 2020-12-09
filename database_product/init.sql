CREATE TABLE public.product (
    id SERIAL PRIMARY KEY,
    name varchar(255),
    price double precision,
    details varchar(255),
    categoryId int

);

INSERT INTO product (name, price, details ,categoryId) VALUES ('club_mate', 1.3, 'lecker und spritzig' , 1);
INSERT INTO product (name, price, details, categoryId) VALUES ('apple', 0.3, 'one per day', 2);
INSERT INTO product (name, price, details, categoryId) VALUES ('t_shirt', 10.0, 'made from happy sheeps', 3);