# JobHook — Find Your Job 🚀

A full-stack job portal built with **Java, Spring Boot, React, and MySQL**, designed to connect candidates with employers through secure authentication, job listings, and application management.

<p align="center">
  <a href="https://job-hook-find-your-job.vercel.app/">
    <strong>🌐 Live Demo</strong>
  </a>
  &nbsp; | &nbsp;
  <a href="https://github.com/Likith-Reddy25/JobHook-Find-your-Job">
    <strong>💻 Source Code</strong>
  </a>
</p>

## ✨ Features

### 🔐 Authentication & Security
- JWT-based authentication.
- Spring Security integration.
- Role-Based Access Control (RBAC) for Candidates and Employers.
- Protected routes and role-specific dashboards.

### 💼 Job Management
- Create and manage job postings.
- Browse and search available opportunities.
- Dynamic filtering using Spring Data JPA queries.
- Pagination for efficient job listing retrieval.

### 📄 Application Management
- REST APIs for candidate applications.
- Backend services for job and application workflows.
- Role-specific access to relevant functionality.

### ⚡ Performance & User Experience
- Responsive React frontend.
- Search optimization and real-time filtering.
- Structured REST API integration between frontend and backend.

## 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| Java | Backend development |
| Spring Boot | REST API development |
| Spring Security | Authentication and authorization |
| JWT | Stateless authentication |
| Spring Data JPA | ORM and database operations |
| MySQL | Relational database |
| React | Frontend development |
| Git & GitHub | Version control |
| Vercel | Frontend deployment |

## 🏗️ Architecture

```text
                 Candidate / Employer
                          |
                          v
                   React Frontend
                          |
                     REST APIs
                          |
                          v
                 Spring Boot Backend
                          |
                   Spring Security
                          |
                     JWT + RBAC
                          |
                          v
                   Service Layer
                          |
                          v
                Spring Data JPA
                          |
                          v
                     MySQL DB
```

## 📂 Project Structure

```text
JobHook-Find-your-Job/
├── backend/       # Java and Spring Boot application
└── frontend/      # React application
```

## 🚀 Getting Started

### Prerequisites

- Java JDK
- Maven
- Node.js and npm
- MySQL
- Git

### 1. Clone the repository

```bash
git clone https://github.com/Likith-Reddy25/JobHook-Find-your-Job.git
cd JobHook-Find-your-Job
```

### 2. Configure the backend

Navigate to the backend directory:

```bash
cd backend
```

Configure your MySQL database connection and any required application secrets in the backend configuration.

Example `application.properties` configuration:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Set these environment variables according to your local database configuration. Configure JWT secrets using your application's expected property names.

### 3. Run the backend

Use the Maven wrapper if the repository includes it:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

Otherwise, run `mvn spring-boot:run` if Maven is installed.

### 4. Run the frontend

Open a new terminal:

```bash
cd frontend
npm install
npm run dev
```

Open the local URL displayed in the terminal.

> Ensure that the frontend API configuration points to your running backend and that the database is accessible before testing the application.

## 🔑 Security Highlights

- Token-based authentication using JWT.
- Role-based authorization for protected resources.
- Backend-controlled access to role-specific operations.
- Separation of frontend presentation and backend business logic.

## 🎯 Key Technical Highlights

- Designed and implemented **20+ REST APIs**.
- Built secure authentication and authorization with Spring Security and JWT.
- Integrated Spring Boot with MySQL using Spring Data JPA.
- Implemented dynamic query-based filtering and pagination.
- Developed and integrated a responsive React frontend.

## 👨‍💻 Author

**Likith Reddy Goddilla**

- GitHub: [Likith-Reddy25](https://github.com/Likith-Reddy25)
- Project: [JobHook — Find Your Job](https://github.com/Likith-Reddy25/JobHook-Find-your-Job)
- Live Application: [Open JobHook](https://job-hook-find-your-job.vercel.app/)

---

*JobHook — Connecting talent with opportunities.*
