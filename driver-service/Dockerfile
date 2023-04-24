FROM openjdk:17-jdk-slim
ADD ./target/driver-service-1.0.0.jar driver-service.jar
ENTRYPOINT ["java", "-jar", "driver-service.jar"]

#FROM openjdk:17-jdk-slim
#WORKDIR /app
#
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:resolve
#
#COPY src ./src
#
#CMD ["./mvnw", "spring-boot:run"]
