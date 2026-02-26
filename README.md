# AUI football app

> A full-stack web application designed for managing football teams.

## About the Project

This project was developed as part of the AUI curriculum. It serves as a practical implementation of a web ecosystem, managing relations between players and teams.

**Key highlights:**
* **Full-stack Integration:** Seamless communication between a Spring Boot REST API and an Angular-based frontend.
* **Microservices Architecture:** Designed as a suite of independently deployable services, ensuring high scalability and fault tolerance.
* **Modular Frontend:** Component-based architecture with Angular for a responsive user experience.

## Tech Stack

* **Backend:** Java 21 / Spring Boot 3.5.7 / Spring Data JPA
* **Frontend:** Angular
* **Database:** PostgreSQL
* **Build Tools:** Maven / npm

## Installation & Deployment

1. Clone the repository:
   ```bash
   git clone https://github.com/JHebel1/AUI.git
   ```
2. Create .env file
3. Copy content from .env.example to .env
4. Ensure JAVA_HOME is set to your local JDK 21+ path to allow the build script to compile the source code.
5. Run ```./build.sh ``` in main directory
6. Run
   ```bash
   docker compose -f docker-compose.yml up --build
   ```
   in main directory
7. Open [http://localhost:8084](http://localhost:8084) in your browser

## License 

Distributed under the MIT License

## Contact

Email: jakubhebelll@gmail.com
