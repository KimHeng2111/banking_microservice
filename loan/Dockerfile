FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

COPY target/*.jar loan-service.jar

ENTRYPOINT ["java", "-jar", "loan-service.jar"]