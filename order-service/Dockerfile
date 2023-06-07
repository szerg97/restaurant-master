FROM openjdk:20

WORKDIR /app
COPY target/order-service-0.0.1-SNAPSHOT.jar /app
COPY src/main/resources/menu.json /app/menu.json

EXPOSE 8080

CMD ["java", "-jar", "order-service-0.0.1-SNAPSHOT.jar"]