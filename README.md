# Ecommerce Application with Spring Boot

This is a full-fledged Ecommerce application developed using Spring Boot, Spring Cloud, and related technologies. The application is microservices-based and includes modules for user authentication, product management, order processing, and more.

## Microservices Overview

### 1. Security Service

The Security Service is responsible for user authentication and registration. It utilizes Spring Security and JWT-based authentication to secure the application.

### 2. Product Service

The Product Service focuses on managing products within the Ecommerce platform. It handles operations related to the product catalog, such as adding, updating, and retrieving products.

### 3. Order Service

The Order Service is dedicated to storing information about user purchases. It maintains a record of ordered products, quantities, and associated user details.

## Technologies Used

- **Spring Boot**: The primary framework for building the microservices.
- **Spring Cloud**: Utilized for creating a robust microservices architecture.
- **Spring Security**: Implemented for securing user authentication and authorization.
- **Spring Data JPA**: Used for easy and consistent data access.
- **PostgreSQL**: Chosen as the relational database to store application data.
- **JWT Authentication**: Implemented for secure and stateless user authentication.

## Additional Services

### 1. Discovery Service

The Discovery Service is used for service discovery, enabling microservices to find and communicate with each other.

### 2. Config Service

The Config Service manages configuration properties for the entire Ecommerce application. It allows centralized configuration management.

### 3. Spring Cloud Gateway

Spring Cloud Gateway is employed as an API gateway to handle routing and provide a unified entry point for all microservices.

## Docker Integration

Each microservice includes a Dockerfile for containerization. A Docker Compose file has been added, allowing all microservices to be started together using a single command:

```bash
docker-compose up
