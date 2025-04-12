FROM openjdk:17-jdk-slim as build

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8060

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
