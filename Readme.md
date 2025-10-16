# 🧩 Fleet Telemetry Platform

This project is a small end-to-end **Fleet Telemetry** system I built to experiment with **Spring Boot microservices**, **Kafka**, **PostgreSQL**, and **GraphQL**, all running together in **Docker**.

The idea is straightforward:
- `telemetry-ingest` receives raw JSON telemetry (VIN, GPS, coolant temp, fuel level, etc.) and sends it to Kafka.
- `event-processor` consumes those telemetry messages, applies a few business rules (like “overheat”), and writes data into Postgres.
- `fleet-api` exposes a GraphQL endpoint to query vehicle status and incidents in real time.

Even though it’s a simple setup, it mimics a realistic event-driven architecture with clear service boundaries.

---

## 🧱 Project structure

├── telemetry-ingest/ → Kafka producer (HTTP → Kafka)
├── event-processor/ → Kafka consumer + Postgres writer
├── fleet-api/ → GraphQL API (reads from Postgres)
├── docker-compose.yml → brings up the full stack
└── README.md


---

## 🛠️ Tech stack

| Component | Purpose | Notes |
|------------|----------|-------|
| **Spring Boot 3.3 / Java 17** | Core framework | built with Maven |
| **Kafka + Zookeeper** | Event streaming backbone | uses Confluent images, 2 listeners (9092 internal / 9093 host) |
| **PostgreSQL 16** | Primary database | holds `vehicle_status` and `incident` tables |
| **Spring GraphQL** | API layer | `/graphiql` enabled for quick testing |
| **pgAdmin 4** | DB admin tool | http://localhost:8086 |
| **Kafka UI** | Kafka dashboard | http://localhost:8085 |

---

## ⚙️ Local setup (Docker)

### 1. Build the services
From each project folder:

```bash
cd telemetry-ingest  && mvn -q -DskipTests package
cd ../event-processor && mvn -q -DskipTests package
cd ../fleet-api       && mvn -q -DskipTests package


2. Start the full stack
Run from the folder containing docker-compose.yml:
docker compose up -d --build


3. Verify everything is up
docker compose ps
docker logs -f event-processor

4. Access UIs

Kafka UI: http://localhost:8085

pgAdmin: http://localhost:8086

(User: admin@example.com, Pass: admin123)

GraphiQL: http://localhost:8080/graphiql