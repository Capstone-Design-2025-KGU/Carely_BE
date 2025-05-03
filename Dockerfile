FROM gradle:8.13-jdk17 as build
WORKDIR /app
COPY . .
RUN ["./gradlew","clean","build"]

# 명시적으로 x86 아키텍처로 이미지를 빌드
FROM --platform=linux/amd64 openjdk:17-jdk-slim
WORKDIR /app

# 애플리케이션 추가
COPY --from=build /app/build/libs/*SNAPSHOT.jar /app.jar
EXPOSE 8081

# ENTRYPOINT 설정
ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
