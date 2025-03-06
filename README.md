# Citizen

_Manage citizen data._

## Getting Started

### Prerequisites

- **Java 21 or higher**
- **Maven**
- **MariaDB**
- **Git**
- **[Dependent Microservices](#dependencies)**

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/Public-Service-as-a-Service/api-service-citizen.git
   cd api-service-citizen
   ```
2. **Configure the application:**

   Before running the application, you need to set up configuration settings.
   See [Configuration](#Configuration)

   **Note:** Ensure all required configurations are set; otherwise, the application may fail to start.

3. **Ensure dependent services are running:**

   If this microservice depends on other services, make sure they are up and accessible. See [Dependencies](#dependencies) for more details.

4. **Build and run the application:**

    - Using Maven:

      ```bash
      mvn spring-boot:run
      ```
    - Using Gradle:

      ```bash
      gradle bootRun
      ```

## Dependencies

This microservice depends on the following services:

Ensure that these services are running and properly configured before starting this microservice.

## API Documentation

Access the API documentation via Swagger UI:

- **Swagger UI:** [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

Alternatively, see the `openapi.yaml` file located in `src/test/resources/api` for the OpenAPI specification.

## Usage

### API Endpoints

See [API Documentation](#api-documentation) for detailed information on available endpoints.

### Example Request

```bash
curl -X POST "http://localhost:8080/api/v2/citizen/guid" -H "Content-Type: application/json" -d "{\"personalNumber\": \"1\"}"
```

## Configuration

Configuration is crucial for the application to run successfully. Ensure all necessary settings are configured in `application.yml`.

### Key Configuration Parameters

- **Server Port:**

  ```yaml
  server:
    port: 8080
  ```
- **Database Settings:**

  ```yaml
  spring:
    datasource:
      url: jdbc:mysql://localhost:3306/your_database
      username: your_db_username
      password: your_db_password
  ```
- **External Service URLs:**

  ```yaml
  integration:
    service:
      url: http://dependency_service_url

  ```

### Database Initialization

The project is set up with [Flyway](https://github.com/flyway/flyway) for database migrations. Flyway is disabled by default so you will have to enable it to automatically populate the database schema upon application startup.

```yaml
spring:
  flyway:
    enabled: true
```

- **No additional setup is required** for database initialization, as long as the database connection settings are correctly configured.

### Additional Notes

- **Application Profiles:**

  Use Spring profiles (`dev`, `prod`, etc.) to manage different configurations for different environments.

- **Logging Configuration:**

  Adjust logging levels if necessary.

## Contributing


## License

This project is licensed under the [MIT License](LICENSE).

## Status

## 

Copyright (c) 2023 Sundsvalls kommun