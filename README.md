📘 Patient Management – Microservices Project (Spring Boot, gRPC, Kafka, LocalStack, AWS CDK)

A production-grade microservices system built using Spring Boot, gRPC, Apache Kafka, PostgreSQL, and LocalStack for AWS service emulation. The project demonstrates domain-driven design, asynchronous event-driven communication, CI/CD readiness, containerization, and infrastructure-as-code using AWS CDK.

🚀 Project Overview

This system simulates a healthcare patient-management workflow using a microservices architecture.
Each key action (like creating a patient) triggers business workflows across other services using gRPC and Kafka events.

🧩 Microservices Included
Service	Tech	Purpose
Patient Service	Spring Boot, gRPC, Postgres	Handles patient registration, validation, persistence, and emits patient events.
Billing Service	Spring Boot, gRPC	Creates billing accounts on patient registration.
Analytics Service	Spring Boot, Kafka	Consumes patient events and performs analytics.
API Gateway (LocalStack ECS Task)	AWS CloudFormation (LocalStack)	Provides unified entry point to the system.
MSK (Kafka)	LocalStack MSK	Managed Kafka cluster running inside LocalStack.
Infrastructure Module	AWS CDK	Defines networking, MSK, IAM roles, ECS tasks, and deployment.
🏗 Architecture Diagram (Conceptual)
               API Gateway
                    │
     ┌──────────────┴───────────────┐
     │                              │
 Patient Service  <––gRPC––>  Billing Service
     │
     └──── emits Kafka Event ──────► Analytics Service


All AWS resources (VPC, MSK, CloudWatch Logs, ECS Tasks, IAM roles) are emulated using LocalStack.

🔧 Tech Stack

Java 21

Spring Boot 3.4

gRPC

Apache Kafka

Protocol Buffers

Docker

LocalStack Pro

AWS CDK v2

PostgreSQL

IntelliJ IDEA

📁 Repository Structure
patient-management/
│
├── patient-service/       # Spring Boot + gRPC + Postgres + Kafka producer
├── billing-service/       # Spring Boot + gRPC server
├── analytics-service/     # Spring Boot + Kafka consumer
├── infrastructure/        # AWS CDK: MSK, ECS, IAM, VPC, API Gateway
├── kafka/                 # Kafka UI docker-compose, topic setup
├── proto/                 # Shared protobuf definitions
└── README.md              # Documentation

🛠 How to Run the System
1. Start LocalStack
localstack start

2. Deploy AWS Infrastructure via CDK
cd infrastructure
./localstack-deploy.sh


This script:

Creates VPC

Creates MSK cluster

Deploys ECS tasks for the microservices

Creates IAM Roles

Creates CloudWatch Log groups

3. Build Each Microservice
mvn clean package -DskipTests

4. Run Kafka UI (optional)
cd kafka
docker compose up -d


Kafka UI will open at:
👉 http://localhost:8080

📡 API / gRPC Endpoints
Patient Service (gRPC)

CreatePatient

GetPatient

Emits Kafka event on successful creation:

topic: patient

Billing Service (gRPC)

CreateBillingAccount(patientId, name, email)

Analytics Service (Kafka)

Listens to:

topic: patient

Parses protobuf payload

Logs analytics event

🧪 Testing
Unit Tests

Run for each module:

mvn test

Integration Tests

LocalStack-based:

mvn verify -Pintegration-tests

🐳 Docker

Each service includes a Dockerfile.
Example:

cd patient-service
docker build -t patient-service .
docker run -p 4000:4000 patient-service

🌩 AWS Emulation with LocalStack

The infrastructure module deploys:

VPC

3 private subnets

MSK cluster

CloudWatch log groups

ECS Tasks for each microservice

Security groups

Load-balanced API Gateway

This replicates an actual AWS microservice architecture locally.

📊 Observability
Logging

All microservices log to StdOut

LocalStack redirects ECS/CloudWatch logs to:

http://localhost:4566/_localstack/logs

MSK Monitoring

Use Kafka UI:

http://localhost:8080

🤝 Contributing

Fork the repository

Create a feature branch

Commit your changes

Submit a PR

📄 License

This project is licensed under the MIT License.
