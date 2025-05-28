FROM --platform=linux/amd64 openjdk:17-jdk-slim
WORKDIR /app

COPY /build/libs/*SNAPSHOT.jar /app.jar
EXPOSE 8081

ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.activate=prod", "-jar", "/app.jar"]
