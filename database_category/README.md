## build db container with: 

```docker build -t database_category .```

PASSWORD IS: postgres

## Start server with 

``` docker run -p 5432:5444 database_category ```
## commect to DB with the following command:

```psql -h localhost -p 5444 -U postgres ```

List all databases: ```\l```
Use database  ``` \c category ```
SHOW CONTENT ```SELECT * FROM category; ```

INSERT DUMMY DATA ```INSERT INTO category (id, name) VALUES (0, 'getraenke');```
