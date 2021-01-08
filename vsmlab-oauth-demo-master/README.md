# Demo OAuth2 project for VSMLab


This is a sample to show how authorization with oauth2 works.

implemented grant types are:

- authorization grant type

- client credentials grant type

- resource owner password grant type
 

Parts of this sample are

- Authorization Server

- Client Application

- Resource Server


## Getting Started

Build the project with 
maven build

start the projects with spring boot

goto http://localhost:8080/client

login with user1/password which is a local login at client app

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

* AuthorizationServer from [Spring Security OAuth 2.4 Migration Sample](https://github.com/jgrandja/spring-security-oauth-2-4-migrate)
	using spring security OAuth2 2.4.0.RELEASE 
  http://localhost:8090/auth
  
* Client Application from  [Spring Security OAuth 2.4 Migration Sample](https://github.com/jgrandja/spring-security-oauth-2-4-migrate)
	using spring security OAuth2 2.4.0.RELEASE  http://localhost:8080/client

* Resource Server from  [Spring Security OAuth 2.4 Migration Sample](https://github.com/jgrandja/spring-security-oauth-2-4-migrate)
	using spring security OAuth2 2.4.0.RELEASE  http://localhost:8092/resource


