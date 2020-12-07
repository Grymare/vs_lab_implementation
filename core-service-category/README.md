
## Source to this project template: https://spring.io/guides/gs/accessing-data-mysql/

## build with 

```mvn clean package```

## run with 

```mvn spring-boot:run```

## add and read all products with curl command line:

``` 
curl localhost:8771/category -d categoryName=bauteile
Saved%  
```

```
curl 'localhost:8771/category'                     
[{"id":0,"name":"getraenke"},{"id":1,"name":"lebensmittel"},{"id":2,"name":"kleidung"},{"id":3,"name":"stifte"},{"id":4,"name":"stifte"},{"id":5,"name":"stifte"},{"id":6,"name":"stifte"}]%
```