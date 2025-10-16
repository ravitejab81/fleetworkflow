FROM eclipse-temurin:17-jre
WORKDIR /app
ARG JAR=target/telemetry-ingest-0.0.1-SNAPSHOT.jar
COPY ${JAR} app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app/app.jar"]
