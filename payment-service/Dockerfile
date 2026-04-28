###STAGE 1 build app
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline

copy src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /build/target/payment-service-0.0.1-SNAPSHOT.jar payment-service.jar

EXPOSE 9094

ENTRYPOINT ["java", "-jar", "payment-service.jar"]