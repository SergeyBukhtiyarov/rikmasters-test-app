FROM openjdk:17-jdk-slim
ADD ./target/vehicle-service-1.0.0.jar vehicle-service.jar
ENTRYPOINT ["java", "-jar", "vehicle-service.jar"]