###STAGE 1 build app
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline

copy src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /build/target/notification-service-0.0.1-SNAPSHOT.jar notification-service.jar

EXPOSE 9095

ENTRYPOINT ["java", "-jar", "notification-service.jar"]