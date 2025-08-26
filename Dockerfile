# syntax=docker/dockerfile:1
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /build
ARG APP_DIR="AuthMicroservice Backend"

COPY "${APP_DIR}/pom.xml" ./pom.xml
RUN mvn -B -DskipTests dependency:go-offline

COPY "${APP_DIR}/src" ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/target/*.jar app.jar
ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["sh","-c","java -Dserver.port=${PORT} -jar app.jar"]
