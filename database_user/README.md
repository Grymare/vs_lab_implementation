## build db container with: 

```docker build -t database_category .```

PASSWORD IS: postgres

## Start server with 

``` docker run -p 5666:5432 database_category ```

## commect to DB with the following command:

```psql -h localhost -p 5666 -U postgres ```

List all databases: ```\l```
Use database  ``` \c account ```
SHOW CONTENT ```SELECT * FROM account; ```
