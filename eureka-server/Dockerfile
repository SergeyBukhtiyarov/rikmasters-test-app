FROM openjdk:17-jdk-slim
ADD ./target/eureka-server-1.0.0.jar eureka-server.jar
ENTRYPOINT ["java", "-jar", "eureka-server.jar"]