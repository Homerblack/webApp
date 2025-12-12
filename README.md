WebApp – Spring Boot CRUD Application

A fully working Spring Boot CRUD web application that allows users to submit personal information through a Thymeleaf form and stores it in a PostgreSQL database using Spring Data JPA.

** Features**

Add/save users through an HTML form
Automatically persist data into PostgreSQL
Display all users on a list page
Thymeleaf templates for UI rendering
Clean MVC architecture
JPA/Hibernate for ORM
GitHub-ready project structure

**Tech Stack**

Backend: Spring Boot, Java
Frontend: Thymeleaf, HTML
Database: PostgreSQL
ORM: Hibernate (JPA)
Build Tool: Maven
Version Control: Git & GitHub

**Project Structure**
src/
 └── main/
     ├── java/crud/webApp
     │     ├── controller/
     │     ├── entity/
     │     ├── repository/
     └── resources/
           ├── templates/
           └── application.properties

** Database Setup (PostgreSQL)**

Create the database:
CREATE DATABASE webapp;


Add your database config in application.properties:
spring.datasource.url=jdbc:postgresql://localhost:5432/webapp
spring.datasource.username=postgres
spring.datasource.password=yourPassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

**Running the Application**
1. Clone the repo
git clone https://github.com/Homerblack/webApp.git
cd webApp

2. Run it
mvn spring-boot:run

3. Open in browser
http://localhost:8080/

**Screenshots (Optional but highly recommended!)**

 Upload screenshots of:
Your form page
User list page
Database table view (pgAdmin)
Screenshots make your repo much more appealing to recruiters.

** Future Improvements**

You can mention things you plan to add:
Add Update/Delete functionality
Add validation & error messages
Add Bootstrap for UI improvement
Add REST API version
Add authentication (Spring Security)
Deploy to Render/Railway/AWS
This shows ongoing development and looks very good to companies.

** License**
 Author
Homer Black
Spring Boot Developer
GitHub: https://github.com/Homerblack
