# Stage 1: Build the application
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn clean package -DskipTests


FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /build/target/service-registry-0.0.1-SNAPSHOT.jar service-registry.jar

EXPOSE 8761

ENTRYPOINT ["java", "-jar", "service-registry.jar"]