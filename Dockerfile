FROM openjdk:17.0.1-jdk-slim
COPY build/libs/payService-1.0-SNAPSHOT.jar /app/payService.jar
EXPOSE 8000
CMD ["java", "-jar", "/app/payService.jar"]