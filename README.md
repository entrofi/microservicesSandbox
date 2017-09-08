# Microservices Sandbox (Draft Version)
This project is a playground project for developing demos of microservices concepts using Spring stack. Following the
 commits you can see various applications of the concepts in practice. 

 Some small chunks of concepts can be found by 
 tags in the repository, other large group of concepts are given in releases. Contents of these are given in release 
 notes of related to that release. You can find summary of release contents and links to release notes in 
 ***[Releases](#releases)*** section below. 

## Running Services and Projects
In order to run services, initially,  you need externalized (for now) instances of mongodb and rabbitmq running on you 
system. 

All of the services uses rabbitmq except web module. To learn what configuration parameters you need for 
rabbimq you can check the properties files for each of the services located in 
[project-root]/airline-booking-engine/config-repository.

MongoDB is used by only ***flight-search*** module currently. Corresponding cconfiguration parameters can be found in
 *flight-search.properties* file located in ***config-repository*** directory. 

 ### Services and Modules Summary
 This demo application has following modules: 
 1. **bom:** The bill of materials module for others
 2. **airline-booking-engine:** Project aggregator module for the actual application. 
     1. ***config-server:*** Spring config server for externalized configuration management
     2. ***kbms:*** Knowledge Base Management Service is the service which holds and serves static data like airports, 
     countries, divisions etc. 
     3. ***fms:*** Flight Management Service is the service for core flight management. 
     4. ***flight-search:*** Flight Search Service is the service for searching flights.
     5. ***booking:*** Inventory and booking management service. 
     6. ***web:*** User interface.
 

### Service Configurations

#### Config Server Configuration
To run config server in your environment you need to setup config repository parameter for your environment. You may 
choose to configure a local git repository for configuration files or you may direct it to a remote git repository. 
Check out the *bootstrap.properties* in ***[project-root]/airline-booking-engine/config-server/src/main/resources***

```properties
server.port=8888
spring.cloud.config.server.git.uri=http://192.168.99.100:980/entrofi/flight-booking-config.git
spring.cloud.config.server.git.searchPaths=flight-booking/config-repository, *
management.security.enabled=false
```

#### Useful Configuration Parameters for Other services
**Ports listened by services:**
- config-server port: 8888
- kbms port: 8070
- fms port: 8080
- booking port: 8010
- flight search port: 8090
- web port: 8001


### Running order for demos
You need to run services in the following order. 
- config-server
- kbms
- fms
- flight-search
- booking

## Releases

### v1.0.0
Implementation and identification of basic service boundaries. Application of Spring Data Rest, Spring Hateoas, 
Spring Data MongoDB, Spring Data JPA, and simple centralized configuration management using spring cloud config. 
