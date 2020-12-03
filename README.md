vs_lab_implementation

## First build eureka with maven: 

```mvn package spring-boot:repackage```

## Build the docker container by using the Dockerfile by executing:

```docker build -t eureka_server eureka-server```

## run the server by using docker compose in the main folder
```docker-compose up``` 

Try to reach the server by [localhost:8761](http://localhost:8761/)

**NOTE:** The expose comand in dockerfile does only opens ports to the container interface. In Order to communicate with the host
the port must be specified either in the startup docker command or the docker-compose file.