FROM maven:3.9.9-eclipse-temurin-24-alpine AS build

WORKDIR /build
COPY . .
WORKDIR /app

RUN mvn dependency:go-offline
RUN mvn clean package -DskipTests

FROM eclipse-temurin:24-alpine AS deploy
WORKDIR /app
COPY --from=build /app/target/*.jar /app/cliente.jar

ENTRYPOINT ["java", "-jar", "cliente.jar"]
