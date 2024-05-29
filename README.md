# Event-Orchestrator

Event-Orchestrator is a scalable event management SaaS platform designed to help organizations create, manage, and share events with potential attendees. The platform ensures high availability and scalability through a microservices architecture, containerization, and deployment on Kubernetes or Docker.

## Features

- **Event Management**: Create and manage events with ease.
- **User Management**: Manage user accounts and roles efficiently.
- **Resource Management**: Track and manage event resources.
- **Real-Time Notifications**: Notify users instantly with Apache Kafka.
- **Claims and Reviews**: Handle user feedback and claims.
- **Centralized Configuration**: Spring Config Server for streamlined configuration management.
- **Service Discovery**: Spring Eureka for dynamic service discovery.
- **API Gateway**: Centralized routing with Spring Cloud API Gateway.
- **Responsive Frontend**: User-friendly Angular interface.

---

## Technologies Used

### Backend:
- **Spring Boot**
- **Spring Cloud** (API Gateway, Eureka, Config Server)

### Frontend:
- **Angular**

### Databases:
- **MySQL** (event data, user information)
- **MongoDB** (non-relational data)

### Messaging System:
- **Apache Kafka**:
    - Producer generates real-time notifications.
    - Consumer processes and sends notifications.

### Containers & Orchestration:
- **Docker**
- **Kubernetes**

---

## Application Structure

```plaintext
event-orchestrator/
├── Event-module/                # Microservice for event management
├── Kubernetes manifest files/   # Kubernetes YAML files for deployment
├── Resource-Module/             # Microservice for resource management
├── Review_Module/               # Microservice for claims and reviews
├── admin-microservice/          # Microservice for admin tasks
├── api-gateway/                 # Spring Cloud API Gateway
├── config-server/               # Spring Config Server for centralized configuration
├── event-orchestrator-frontend/ # Angular frontend application
├── notification-module/         # Microservice for real-time notifications
├── service-registry/            # Spring Cloud Eureka Service Registry
├── user-microservice/           # Microservice for user management
├── docker-compose.yml           # Docker Compose file for local deployment
```


## Deployment Options

### 1. Kubernetes
You can deploy the application using the Kubernetes manifest files provided in the `Kubernetes manifest files` directory.

#### Steps:
1. Deploy **Config Server**, **Service Registry**, and **API Gateway** first to ensure core services are functioning.
2. Deploy individual microservices (Event, Resource, Review, etc.) using the manifest files.
3. Deploy the **Frontend** application as the final step.

#### Notes:
- Persistent Volumes are required for MySQL and MongoDB. Use the provided `PV.yaml` file or configure your own storage solution.
- The manifest files use pre-built public Docker images. Feel free to build your own images if needed.

---

### 2. Docker-Compose
Use the `docker-compose.yml` file to deploy the application locally with Docker Compose:
```bash
docker-compose up
```
---

### 3. Local Deployment (Not Recommended for Microservices)

Run each microservice locally:
```bash
java -jar <microservice>.jar
```

---
## Getting Started

### Clone the Repository:
```bash
git clone https://github.com/your-repo/event-orchestrator.git
cd event-orchestrator
```

### Choose a Deployment Option:
- Kubernetes
- Docker-Compose
- Local

### Access the Frontend:
After deployment, navigate to the frontend UI to start managing events.
