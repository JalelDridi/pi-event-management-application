# Stage 1: Build the application with Maven
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

# Stage 2: Use OpenJDK 11 to run the application
FROM eclipse-temurin:17.0.11_9-jre-ubi9-minimal
WORKDIR /app
COPY --from=build /home/app/target/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]