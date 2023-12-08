FROM openjdk:17-jdk-slim-buster

WORKDIR /app/

COPY target/order-publisher-service-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

ENTRYPOINT ["java","-jar","order-publisher-service-0.0.1-SNAPSHOT.jar"]