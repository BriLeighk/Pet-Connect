# Pet-Connect

 Pet Connect is a web-based pet adoption platform designed to streamline the adoption process for rescuers and potential adopters.

## Project Description
Pet Connect is a comprehensive platform that allows users to browse pet profiles, save favorites, and initiate the adoption process through a build-in messaging system. Rescuers can manage pet profiles, respond to inquiries, and more.

## Features
- User authentication and authorization
- Browse and filter pet profiles
- Save favorite pets
- Contact rescuers
- Add, update, and manage pet profiles as a rescuer
- View map of pet locations through Google Maps integration

## Technology Stack

### Frontend
- HTML, CSS
- Thymeleaf

### Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security

### Database
- MySQL
- Hibernate

### Other Tools
- Maven
- BCrypt
- Google Maps API

## Setup and Installation

### Prerequisites
- JDK (Java Development Kit) 21
- Spring Boot
- Maven
- MySQL
- Git

### Installation Steps
1. Clone repository: ```git clone https://github.com/BriLeighk/Pet-Connect.git```
2. Install the dependencies: ```mvn clean install```
3. Change the MySQL information in the application.properties file
4. Run the application:
    -	If on linux (or using WSL on windows): ```./mvnw spring-boot:run```
    -	If on Windows: ```mvn spring-boot:run```
You can access the application (once it’s running) at this URL: ```http://localhost:8080```
