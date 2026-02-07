🚀 Job Portal – Full Stack Web Application

A full-stack job portal application that allows recruiters to post jobs and applicants to search and apply for them. Built using Spring Boot, React, and MySQL (AWS RDS) and deployed on Render.

🛠 Tech Stack

Backend: Java, Spring Boot, Spring Data JPA, Hibernate
Frontend: React, Axios
Database: MySQL (AWS RDS)
Deployment: Render
Authentication: Role-based access control

✨ Features

User registration & login

Role-based access (Recruiter / Applicant)

Recruiters can post and manage jobs

Applicants can browse and apply for jobs

Prevents duplicate applications

Secure environment-based configuration

⚙️ Local Setup
git clone https://github.com/yourusername/job-portal.git
cd job-portal
mvn spring-boot:run


Configure database in application.properties before running.

📌 Key Learnings

REST API development with Spring Boot

Database integration with AWS RDS

Cloud deployment & debugging (Render + RDS)

Secure credential management using environment variables
