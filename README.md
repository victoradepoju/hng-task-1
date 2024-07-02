
# Spring Boot Application with IP Geolocation and Weather Integration

This project is a Spring Boot application that integrates with external APIs for IP geolocation and weather information. It provides a simple API endpoint to retrieve visitor's personal and weather information based on their IP address.

## Features

- **IP Geolocation**: Retrieves IP address and fetches geolocation information using the `ipgeolocation.io` API.
- **Weather Integration**: Integrates with OpenWeatherMap API to fetch real-time weather information based on user's location.

## Technologies Used

- Java
- Spring Boot
- RESTful API design
- External API integration (ipgeolocation.io, OpenWeatherMap)

## Setup Instructions

1. **Clone the repository**

   ```bash
   git clone https://github.com/victoradepoju/hng-task-1.git
   cd cd hng-task-1
   ```

2. **Configure application properties**

   Update `application.properties` or `application.yml` with your API keys for `ipgeolocation.io` and `OpenWeatherMap`.

3. **Build and run the application**

   ```bash
   mvn spring-boot:run
   ```

   Access the application at `http://localhost:8088`.

## API Endpoint

- **GET /api/hello?visitor_name={visitor}**: Retrieves IP address, location, and weather information for the visitor.

## Deployment

Deployed on [Railway](https://hng-task-1-production-8fea.up.railway.app/api/hello?visitor_name=%22Mark%22) for hosting. 

## Contributors

- [Victor Adepoju](https://github.com/victoradepoju)

## License

This project is licensed under the [MIT License](https://github.com/victoradepoju).
