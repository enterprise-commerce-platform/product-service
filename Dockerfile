FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY target/product-service-0.0.1-SNAPSHOT.jar product-service.jar

USER 10001

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "product-service.jar"]