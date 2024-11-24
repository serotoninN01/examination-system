FROM openjdk:8-jdk-slim

WORKDIR /app

COPY target/xzs-3.9.0.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]