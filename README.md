# Filter Application

This repository contains a demo application called "Filter" built with Spring Boot and Docker.

## Description

The "Filter" application demonstrates how to use filters and criteria with a Spring Boot backend and a React frontend. It allows users to define filters based on criteria types and apply them to filter data.

## Technologies Used

- Java 17
- Spring Boot 3.3.1
- PostgreSQL 15
- React (for the frontend)

## Modules

- **filter-api**: Backend API built with Spring Boot.
- **filter-ui**: Frontend UI built with React.

## Prerequisites

Before running the application, make sure you have the following installed:

- Docker
- Docker Compose

## Getting Started

To run the "Filter" application locally, follow these steps:

1. Build and start the application using Docker Compose:
- docker-compose up --build
   
This command will build the Docker images if they don't exist and start the application containers.

2. Access the application:
   Once the application is up and running, you can access it at:
- Frontend: http://localhost:80
- Backend: http://localhost:8080