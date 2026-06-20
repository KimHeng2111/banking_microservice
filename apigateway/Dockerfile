FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

COPY target/*.jar apigateway.jar

ENTRYPOINT ["java", "-jar", "apigateway.jar"]