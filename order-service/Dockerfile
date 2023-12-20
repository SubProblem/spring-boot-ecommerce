FROM openjdk:17-alpine

WORKDIR /app

COPY target/order-service-0.0.1-SNAPSHOT.jar app.jar

COPY /src/main/resources/application.yaml application.yaml

ENTRYPOINT ["java", "-jar", "app.jar"]

