# MagMutual User API

Spring Boot microservice for retrieving and searching user data from a CSV file.

## Tech Stack

- Java 21
- Spring Boot 4.1.1
- Spring Data JPA
- H2
- Apache Commons CSV
- Swagger / OpenAPI
- JUnit 5
- Mockito
- Maven

## Features

- Get a user by ID
- Get users created within a date range
- Search users by text across:
    - first name
    - last name
    - email
    - profession
    - country
    - city
- Swagger UI
- Centralized exception handling
- Unit tests

## Project Questions

### What did you think of the project?
I really enjoyed the project and i enjoyed designing and think about how to create the architecture.
I thought it was a good small microservice exercise that covered API design, persistence, search, testing, exception handling, and documentation.

### What didn’t you like about the project?

I didd not have any issue maybe the full search requirement could have had more details

### How would you change the project or approach?

For a larger production system, use a persistent database such as PostgreSQL, and done token search for the full search api 

### Anything else you would like to share?

I kept the design simple and light. I used a Spring Boot architecture with controller, service, repository, centralized exception handling, and Swagger documentation.

## Run

```bash
./mvnw spring-boot:run

