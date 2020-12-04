## build db container with: 

```psql -h postgresql.guebs.net -U user_name-ddatabase_name```

PASSWORD IS: postgres

## commect to DB with the following command:

```psql -h localhost -p 5432 -U postgres ```

List all databases: ```\l```
Use database  ``` \c category ```
SHOW CONTENT ```SELECT * FROM category; ```

INSERT INTO category(id, name) VALUES (0, "getraenke");
