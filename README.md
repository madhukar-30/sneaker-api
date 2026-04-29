# Sneaker API - Backend

A Spring Boot REST API for managing sneaker data including sizes, pricing, gender, and categories, built using **Java 17**, **Spring Boot**, **JPA**, and **MySQL**. The application is deployed on **Render** with secure, environment-based configuration. It also includes an **AI-powered recommendation system** using Gemini API.

---

## 🚀 Live API Base URL
**Base URL:** https://sneaker-api-dhf1.onrender.com/

All endpoints are prefixed with: `/api/sneakers`

---

## ✨ Features
* **RESTful APIs:** CRUD operations for sneaker management.
* **ORM:** Spring Data JPA with Hibernate ORM.
* **Database:** Managed MySQL instance.
* **Secure Config:** Environment variable-based configuration.
* **Database Automation:** Automatic table creation using Hibernate.
* **AI Recommendation:** Suggests sneakers based on user query using Gemini API.
* **Cloud Deployment:** Application deployed on the Render platform.

---

## 🛠 Tech Stack
| Component | Technology |
| :--- | :--- |
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.5.9 |
| **ORM** | Spring Data JPA (Hibernate) |
| **Database** | MySQL |
| **Build Tool** | Maven |
| **Deployment** | Render |
| **AI Integration** | Gemini API |

---

## 🛣 API Endpoints

| Action | Method | Endpoint |
| :--- | :--- | :--- |
| **Create Sneaker** | `POST` | `/api/sneakers` |
| **Get All Sneakers** | `GET` | `/api/sneakers` |
| **Get Sneaker by ID** | `GET` | `/api/sneakers/{id}` |
| **Update Sneaker** | `PUT` | `/api/sneakers/{id}` |
| **Delete Sneaker** | `DELETE` | `/api/sneakers/{id}` |
| **AI Suggestion** | `POST` | `/api/sneakers/ai/suggest` |

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

### Request Body Format (AI Suggestion)
"running shoes for men"

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
For the application to connect to the database and AI service, ensure these variables are set in your local environment or Render dashboard:

* MYSQL_URL: jdbc:mysql://${MYSQLHOST}:${MYSQLPORT}/${MYSQLDATABASE}?useSSL=false&allowPublicKeyRetrieval=true
* MYSQL_USER: <database-username>
* MYSQL_PASSWORD: <database-password>
* GEMINI_API_KEY: <your-api-key>

---

## 💻 Running Locally
Ensure you have Maven and Java 17 installed. To start the application, run:

mvn spring-boot:run

---

## ☁️ Deployment
The application is deployed on **Render** as a Web Service and connects to a managed **MySQL database**. Hibernate automatically creates and updates database tables on startup.

---

## 🧠 Key Learnings
Through the development of this project, the following concepts were explored and implemented:

* MySQL integration using Spring Data JPA.
* Handling bidirectional entity relationships in Hibernate.
* Environment-based configuration for cloud deployment.
* Debugging JDBC, Hibernate dialect, and database connectivity issues.
* Integrating AI APIs and designing prompt-based recommendation systems.
* Implementing retry handling and structured JSON responses for AI outputs.
* Understanding connection pooling with HikariCP and resolving database connection issues.

---

## 👤 Author
**Manas Madhukar**
