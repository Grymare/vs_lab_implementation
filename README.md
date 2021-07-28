# How to run the current state of the project:

## Build and start eureka, user, product and category DB with:

```docker-compose up --build```

## To delete all containers
``` docker rm -f $(docker ps -aq) ```

## To delete all IMAGES!!!!
``` docker rmi $(docker images -aq)```

## Run the composite, all three coreservices and the zuul-service in their corresponding folder with:
```mvn spring-boot:run``` 

## Build the executable jar files for docker containers with:
```mvn clean package -DskipTests```

## Request all products with their categories from composite service

curl -X GET "http://localhost:8774/product"

## Connect to the eureka service

http://localhost:8761/

# How to build a service of the project:

## At first switch into the service folder and execute
```mvn clean package -DskipTests```

## After building the new jar files rebuild the container with
```docker rmi service_name```

## Now rebuild the service with
```docker build``` or ```docker-compose build name_of_containter_1 name_of_containter_2 name_of_containter_3```


## Portmapping4-Spring-Cloud

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