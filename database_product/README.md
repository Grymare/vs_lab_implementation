## build db container with: 

```docker build -t database_product .```

PASSWORD IS: postgres

## Start server with 

``` docker run -p 5555:5432 database_product ```

## commect to DB with the following command:

```psql -h localhost -p 5555 -U postgres ```

List all databases: ```\l```
Use database  ``` \c product ```
SHOW CONTENT ```SELECT * FROM product; ```

INSERT DUMMY DATA ```INSERT INTO product (name, price, category) VALUES ('schuhe', 20.0, 2);```
