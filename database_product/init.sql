CREATE TABLE public.product (
    id int PRIMARY KEY,
    name varchar(255),
    price float,
    category int
);

INSERT INTO product (name, price, category) VALUES ('club_mate', 1.3, 0);
INSERT INTO product (name, price, category) VALUES ('apple', 0.3, 1);
INSERT INTO product (name, price, category) VALUES ('t_shirt', 10.0, 2);