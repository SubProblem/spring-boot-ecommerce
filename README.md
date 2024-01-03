# Ecommerce Application with Spring Boot

This comprehensive Ecommerce application is built using Spring Boot, Spring Cloud, and related technologies. The system is organized into microservices handling user authentication, product management, order processing, and more.

## Microservices Overview

1. **Security Service**
    - Manages user authentication and registration using Spring Security and JWT-based authentication.

2. **Product Service**
    - Responsible for managing the product catalog, including CRUD operations related to products.

3. **Order Service**
    - Stores and manages user purchase information, tracking ordered products, quantities, and user details.

4. **Notification Service** 
    - Utilizes Kafka to receive order details from the Order Service and sends email notifications to users about their orders.

## Technologies Used

- **Spring Boot**: Primary framework for developing microservices.
- **Spring Cloud**: Facilitates the creation of a robust microservices architecture.
- **Spring Security**: Implemented for user authentication and authorization.
- **Spring Data JPA**: Simplifies and maintains consistent data access.
- **PostgreSQL**: Relational database for storing application data.
- **JWT Authentication**: Enables secure and stateless user authentication.
- **Apache Kafka**: Employed for asynchronous communication between microservices, facilitating real-time messaging and data streaming.

## Kafka Integration

The implementation of Kafka within the system involves:
- **Producer in Order Service**: Sends messages with product IDs and quantities to the Kafka topic.
- **Consumer in Notification Service**: Listens to the Kafka topic for incoming messages, extracts order details, and triggers email notifications to users.

## Additional Services

1. **Discovery Service**: Handles service discovery, enabling microservices to locate and communicate with each other.
2. **Config Service**: Manages centralized configuration properties for the entire application.
3. **Spring Cloud Gateway**: Functions as the API gateway, managing routing and serving as a unified entry point for all microservices.

## Docker Integration

Each microservice includes a Dockerfile for containerization. A Docker Compose file allows starting all microservices together using a single command:

```bash
docker-compose up
