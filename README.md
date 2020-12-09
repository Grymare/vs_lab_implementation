# How to run the current state of the project:

## Build and start eureka, user, product and category DB with:

```docker-compose up --build```

## Run the composite and all three coreservices in their corresponding folder:
```mvn spring-boot:run``` 

## Request all products with their categories from composite service

curl -X GET "http://localhost:8774/product"

## Connect to the eureka service

http://localhost:8761/

## Portmapping

- eureka_server: 8761
- zuul-application: 8791

- core_service_category: 8771 
- core_service_product: 8772
- core_service_user: 8773
- composit_service_category_product: 8774

- database_category:5444
- database_product: 5555
- database_user: 5666


**NOTE:** The expose comand in dockerfile does only opens ports to the container interface. In Order to communicate with the host
the port must be specified either in the startup docker command or the docker-compose file.