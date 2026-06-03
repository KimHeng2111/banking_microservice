FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

COPY target/*.jar account-service.jar

ENTRYPOINT ["java", "-jar", "account-service.jar"]