# Pet Food Sales Platform

## Description
A microservices-based platform for selling pet food, built with Spring Boot and MongoDB.

## Structure
- **catalog-service**: Manages pet food products (name, type, description, weight, price, availability).
- **user-service**: Handles customer registration, login, and management with JWT authentication.
- **order-service**: Manages orders (create, list, retrieve).
- **payment-service**:
- **frontend**:

## Prerequisites
- Java 21+
- Maven 3.9.9+
- MongoDB 6.0+
- Docker Desktop (for containerized execution

## Setup
1. Start MongoDB:
   ```bash
   mongod
   ```
2. For each service:
   ```bash
   cd petfood-app/<service>
   mvn clean install
   mvn spring-boot:run
   

 ### Docker Execution
1 - Ensure Docker Desktop is running.
2  - Build and start all services:
  ```bash
  cd petfood-app
  docker-compose up --build
  ```
3 - Stop services:
  ```bash
  docker-compose down
  ```

## Databases
- **catalog-service**: Uses `catalog_db` (created automatically upon inserting products).
- **user-service**: Uses `user_db` (created automatically upon registering users).
- **order-service**: Uses `order_db` (created automatically upon creating orders).

## Endpoints
- **Catalog Service** (`http://localhost:8081`):
    - `GET /api/products`
    - `POST /api/products`
    - `GET /api/products/{id}`
    - Swagger: `http://localhost:8081/swagger-ui.html`
- **User Service** (`http://localhost:8082`):
    - `POST /api/users/register`
    - `POST /api/users/login`
    - `GET /api/users/{id}` (authenticated)
    - `PUT /api/users/{id}` (authenticated)
    - Swagger: `http://localhost:8082/swagger-ui.html`
- **Order Service** (`http://localhost:8083`):
    - `POST /api/orders` (authenticated)
    - `GET /api/orders` (authenticated)
    - `GET /api/orders/{id}` (authenticated)
    - Swagger: `http://localhost:8083/swagger-ui.html`

## Notes
- **UTF-8 Encoding**: Use Postman or `curl --data @file.json` to avoid encoding errors in Git Bash (e.g., `Invalid UTF-8 middle byte`).
- **MongoDB Compass**: Manually refresh (F5) to view new databases/collections.
- **Execution**: Use `mvn spring-boot:run` or "Run <Service>Application" in IntelliJ to start the server. Avoid `mvn test`, which does not start the web server.
- **Debugging**: Add `logging.level.org.springframework=DEBUG` to `application.properties` for detailed logs.

## Evidence
- Screenshots of MongoDB Compass and Swagger UI for `all project` are stored in `docs/screenshots`.

## Commit Log
- **2025-06-05**: Initialized catalog-service with basic endpoints and Swagger.
- **2025-06-05**: Added user-service with registration, login, and customer management.
- **2025-06-05**: Added order-service with endpoints for order management.
- **2025-06-06**: Added Docker configuration for catalog-service and user-service.

## Next Steps
Implement order-service with Docker support.
Add payment-service (simulated).