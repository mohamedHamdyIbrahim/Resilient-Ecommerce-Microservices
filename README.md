# Resilient-Ecommerce-Microservice

This project is a resilient Order Service application built with Spring Boot. It allows customers to place orders, integrates with other services such as Payment and Notification, and utilizes RabbitMQ for messaging and distributed tracing with Zipkin. This README provides a guide on setting up, running, and accessing key services and tools integrated within this application.

## Table of Contents
- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [Running the Application](#running-the-application)
- [Service Links](#service-links)
- [API Documentation](#api-documentation)

## Project Overview
The Order Service application is designed to handle e-commerce order placements. It features:
- **Order Placement**: Customers can place orders through this service.
- **Payment Integration**: Orders trigger payment requests to a Payment Service.
- **Notification**: Upon successful payment, a notification is sent via a Notification Service.
- **Resilience**: Uses Circuit Breaker, Retry, and Timeout patterns to ensure reliability.
- **Observability**: Distributed tracing and metrics are enabled for tracking and monitoring.

## Architecture
This microservices-based application uses RabbitMQ for messaging, Zipkin for tracing, and Swagger for API documentation. Each service communicates via Feign clients, and the application is designed to handle large volumes of requests.

## Technologies Used
- **Spring Boot** (version 3.3.4)
- **Spring Cloud** (2023.0.3)
- **RabbitMQ**: For messaging between services.
- **Zipkin**: For distributed tracing.
- **Swagger**: For interactive API documentation.
- **Circuit Breaker & Retry Mechanisms**: To ensure resilience.
- **Docker**: For containerized deployment.

## Setup and Installation
1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   ```
2. **Navigate to the project directory**:
   ```bash
   cd order-service
   ```
3. **Build the project**:
   ```bash
   ./mvnw clean install
   ```
4. **Run Docker containers** (for RabbitMQ and Zipkin):

run Docker compose inside Order Service


## Service Links
Here are the direct links to access the various services:

- **RabbitMQ Management Console**: [http://localhost:15672](http://localhost:15672)  
  Use this link to monitor and manage message queues for the Order Service.

- **Zipkin Tracing**: [http://localhost:9411](http://localhost:9411)  
  Access this link to view distributed traces and analyze request flows within the microservices architecture.

## API Documentation
The Order Service API documentation is available via Swagger. Use this link to access and test API endpoints directly:

- **Swagger UI**: http://localhost:8080/swagger-ui/index.html

## Additional Notes
- Ensure that RabbitMQ and Zipkin are running before starting the Order Service to allow proper messaging and tracing.
- Authentication might be required for accessing RabbitMQ (default username/password is `guest`/`guest`).
- Swagger can be accessed after the Order Service has fully started.

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details.
