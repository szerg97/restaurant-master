FROM amazoncorretto:17

WORKDIR /app
COPY target/warehouse-0.0.1-SNAPSHOT.jar /app

EXPOSE 8081

CMD ["java", "-jar", "warehouse-0.0.1-SNAPSHOT.jar"]