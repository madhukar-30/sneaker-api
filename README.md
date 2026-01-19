# Sneaker API - Backend

A Spring Boot REST API for managing sneaker data including sizes, pricing, gender, and categories, built using **Java 17**, **Spring Boot**, **JPA**, and **MySQL**. The application is deployed on **Railway** with secure, environment-based configuration.

---

## 🚀 Live API Base URL
**Base URL:** https://sneaker-api-production.up.railway.app/

All endpoints are prefixed with: `/api/sneakers`

---

## ✨ Features
* **RESTful APIs:** CRUD operations for sneaker management.
* **ORM:** Spring Data JPA with Hibernate ORM.
* **Database:** Managed MySQL instance on Railway.
* **Secure Config:** Environment variable-based configuration.
* **Database Automation:** Automatic table creation using Hibernate.
* **Cloud Ready:** Production-ready deployment on the Railway platform.

---

## 🛠 Tech Stack
| Component | Technology |
| :--- | :--- |
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.5.9 |
| **ORM** | Spring Data JPA (Hibernate) |
| **Database** | MySQL |
| **Build Tool** | Maven |
| **Deployment** | Railway |

---

## 🛣 API Endpoints

| Action | Method | Endpoint |
| :--- | :--- | :--- |
| **Create Sneaker** | `POST` | `/api/sneakers` |
| **Get All Sneakers** | `GET` | `/api/sneakers` |
| **Get Sneaker by ID** | `GET` | `/api/sneakers/{id}` |
| **Update Sneaker** | `PUT` | `/api/sneakers/{id}` |
| **Delete Sneaker** | `DELETE` | `/api/sneakers/{id}` |

### Request Body Format (POST/PUT)
{
  "name": "Air Max",
  "pageUrl": "/air-max",
  "gender": "Men",
  "fresh": true,
  "category": "Running",
  "image": "image-url",
  "sizes": [
    {
      "size": 9,
      "price": 9999
    }
  ]
}

---

## ⚙️ Configuration

The application uses environment-based configuration to keep sensitive credentials secure.

### Spring Properties
In your `src/main/resources/application.properties`, the following properties are used:

* spring.datasource.url=${MYSQL_URL}
* spring.datasource.username=${MYSQL_USER}
* spring.datasource.password=${MYSQL_PASSWORD}
* spring.jpa.hibernate.ddl-auto=update
* spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
* server.port=${PORT:8080}

### Environment Variables
For the application to connect to the database, ensure these variables are set in your local environment or Railway dashboard:

* MYSQL_URL: jdbc:mysql://${MYSQLHOST}:${MYSQLPORT}/${MYSQLDATABASE}?useSSL=false&allowPublicKeyRetrieval=true
* MYSQL_USER: <database-username>
* MYSQL_PASSWORD: <database-password>

---

## 💻 Running Locally
Ensure you have Maven and Java 17 installed. To start the application, run:

mvn spring-boot:run

---

## ☁️ Deployment
The application is deployed on **Railway** as a Web Service and utilizes **Railway MySQL**. **Hibernate** is configured to automatically create and update database tables on startup.

---

## 🧠 Key Learnings
Through the development of this project, the following concepts were explored and implemented:

* MySQL integration using Spring Data JPA.
* Handling bidirectional entity relationships in Hibernate.
* Environment-based configuration for cloud deployment.
* Debugging JDBC, Hibernate dialect, and database connectivity issues.
* Deploying a production-ready Spring Boot application on Railway.

---

## 👤 Author
**Manas Madhukar**
