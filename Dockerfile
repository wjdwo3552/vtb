FROM openjdk:23-jdk-slim as build
COPY build/libs/tvb-0.0.1-SNAPSHOT.jar /app/tvb-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "tvb-0.0.1-SNAPSHOT.jar"]