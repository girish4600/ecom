###STAGE 1 build app
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /build

COPY pom.xml .

RUN mvn dependency:go-offline

copy src ./src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY --from=builder /build/target/order-service-0.0.1-SNAPSHOT.jar order-service.jar

EXPOSE 9093

ENTRYPOINT ["java", "-jar", "order-service.jar"]