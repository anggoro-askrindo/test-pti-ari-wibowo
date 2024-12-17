# Transaksi penutupan produk Asuransi Mikro Service

This project is an API service built with Spring Boot 3.4.0, Maven, and Java 17. It uses PostgreSQL as the database and implements JWT and OAuth for authentication and authorization.

## Features - Add User - Login - Sample Transaction

## Roles
- `ROLE_ADMIN`: Access to user APIs
- `ROLE_ALL`: Access to login APIs
- `ROLE_MARKETING`: Access to transaction APIs

## Project Structure

```
├───main
│   ├───java
│   │   └───com
│   │       └───example
│   │           └───assesmentbackend
│   │               ├───config
│   │               ├───controller
│   │               ├───exception
│   │               ├───model
│   │               │   ├───entity
│   │               │   ├───request
│   │               │   └───response
│   │               ├───repo
│   │               ├───service
│   │               ├───util
│   │               └───validation
│   └───resources
└───test
```

## Dependencies 
- Spring Boot Starter Web 
- Spring Boot Starter Data JPA 
- Spring Boot Starter Security 
- Spring Boot Starter OAuth2 Resource Server 
- Spring Boot Starter OAuth2 Client 
- Spring Boot Starter Validation 
- PostgreSQL 
- Spring Boot Starter Test

## Configuration The security configuration is set up in `SecurityConfig.java` to handle JWT and OAuth authentication and authorization. 
## How to Run 
1. Clone the repository 
2. Update the database configuration in `application.properties` 
3. Run the application using `mvn spring-boot:run`

## Documentation
1. Swagger UI http://localhost:8080/swagger-ui/#
2. Swagger API Doc http://localhost:8080/v2/api-docs
