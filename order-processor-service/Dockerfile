FROM openjdk:17-jdk-slim-buster

WORKDIR /app/

COPY target/order-processing-service-0.0.1-SNAPSHOT.jar /app

EXPOSE 8081

ENTRYPOINT ["java","-jar","order-processing-service-0.0.1-SNAPSHOT.jar"]