FROM eclipse-temurin:17-jre
WORKDIR /app
ARG JAR=target/fleet-api-0.0.1-SNAPSHOT.jar
COPY ${JAR} app.jar
EXPOSE 8080
# Postgres inside Docker
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/fllets
ENV SPRING_DATASOURCE_USERNAME=fleet_user
ENV SPRING_DATASOURCE_PASSWORD=fleet_pass
# Keep GraphiQL on
ENV SPRING_GRAPHQL_GRAPHIQL_ENABLED=true
ENTRYPOINT ["java","-jar","/app/app.jar"]
