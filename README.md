# Employee Management System

This is a microservice application built with Spring Boot and Spring Cloud to manage the employees and departments of an organization.

## Technologies used

- Java 17+
- Spring Boot 3.x
- Spring Cloud
- Spring Data JPA
- Hibernate
- PostgreSQL
- Lombok
- Swagger
- Docker

## Architecture

The application is built using Spring Boot and consists of several layers:

- `Controller`: Handles incoming HTTP requests and maps them to the appropriate service methods
- `Services`: Contains the business logic for managing records and interacts with the repository layer
- `Repository`: Provides an abstraction for interacting with the database
- `Exceptions`: Handles custom exceptions used all over the application
- `Entity`: Contains the base objects that perform the various functions

## Getting started

To run this project on your machine, make sure you have Java installed. Then, follow these steps:

1. Clone the repository

```
git clone  https://github.com/chikodiann/softAlliance-microservices.git
```

2. Run each microservice

```
run each of the SpringBoot projects inside: authentication; employee-mgmt; service-registry; api-gateway
```

3. Access the Swagger UI

```
http://localhost:8080/swagger-ui.html
```

4. Access the Eureka UI

```
http://localhost:8761
```

### Docker Integration:
This project is also Dockerized for easy deployment. You can run the services inside Docker containers for a seamless development experience.

#### To run the project in Docker:
1. **Clone the repository**:
    ```bash
    git clone https://github.com/chikodiann/softAlliance-microservices.git
    ```
2. **Build and start the Docker containers**:
    - Ensure Docker is installed and running on your machine.
    - Navigate to the project directory and run:
      ```bash
      docker-compose up --build
      ```
3. **Access the services**:
    - **Swagger UI**: Access it at `http://localhost:8080/swagger-ui.html` to explore the available endpoints.
    - **Eureka UI**: Access the service registry at `http://localhost:8761`

### Additional Information:
- **PostgreSQL Database**: Ensure your database is up and running in the Docker container.
- **Swagger**: For testing and documentation, use the Swagger UI provided by the application at `http://localhost:8080/swagger-ui.html`.
- **Eureka**: Use Eureka to see all the microservices registered and running at `http://localhost:8761`.


## Endpoints

| Method | URL                                         | Description                                 |
|--------|---------------------------------------------|---------------------------------------------|
| POST   | /api/auth/sign-up                           | Sign up employee                            |
| POST   | /api/auth/sign-up/admin                     | Sign up admin                               |
| POST   | /api/auth/sign-up/login                     | login employee                              |
| GET    | /api/auth/generate-user                     | Auth fetches user details                   |
| POST   | /api/auth/update-user/{id}                  | Auth updates user details                   |
| DELETE | /api/auth/delete-user/{id}                  | Auth deletes user details                   |
| PUT    | /api/employee/update-employee/{id}          | Admin updates employee user details         |
| DELETE | /api/employee/delete-employee/{id}          | Admin deletes employee user details         |
| GET    | /api/employee/get-employee/{id}             | Admin views employee user details           |
| GET    | /api/employee/get-department-employees/{id} | Admin fetches employees in department       |
| GET    | /api/department/get-details/{departmentId}  | Retrieve department details                 |
| POST   | /departments/add                            | Add department                              |
| PUT    | /api/department/update/{departmentId}       | Update department details                   |
| DELETE | /api/department/delete/{departmentId}       | Delete department details                   |
