FROM openjdk:17-jdk-slim

WORKDIR /app

COPY /target/*.jar /app/server.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "/app/server.jar"]
