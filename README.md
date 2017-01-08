# Spring Boot-CRUD-demo

Spring Boot CRUD demo is demonstrating how to implement simple CRUD operations with a `Person` entity.

## What's inside 
This project is based on the [Spring Boot](http://projects.spring.io/spring-boot/) project and uses these packages :
- Maven
- Spring Core
- Spring Data (Hibernate & MySQL)
- Spring MVC (Tomcat)

## Installation 
The project is created with Maven, so you just need to import it to your IDE and build the project to resolve the dependencies

## Database configuration 
Create a MySQL database with the name `springbootdb`and add the credentials to `/resources/application.properties`.  
The default ones are :

```
spring.datasource.url=jdbc:mysql://localhost:3306/person_db
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

## Usage 
Run the project and head out to [http://localhost:7777](http://localhost:7777)
