FROM openjdk:17-jdk-slim
ADD ./target/gateway-server-1.0.0.jar gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]