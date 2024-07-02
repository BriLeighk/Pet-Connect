# Pet-Connect

Steps to set up project:

1. Make sure you have JDK (java version: 21), Spring Boot, and Maven installed on your machines
2. Clone repository: ```git clone https://github.com/BriLeighk/Pet-Connect.git```
5. Install the dependencies (I’ve only added Spring Web, Thymeleaf, and Spring Boot DevTools for now but left space in the pom.xml file with comments to add MySQL Driver, Spring Boot Security, and Spring Boot Data JPA  for later). Once we implement the user authentication feature and the database, we can add most of those additional dependencies.

You can use this command to add dependencies: ```mvn clean install```

These are the commands to run the application:
-	If on linux (or using WSL on windows): ```./mvnw spring-boot:run```
-	If on Windows: ```mvnw.cmd spring-boot:run```
You can access the application (once it’s running) at this URL for the time being: ```http://localhost:8080```
