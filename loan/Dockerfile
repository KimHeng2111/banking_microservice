FROM eclipse-temurin:25-jre-ubi10-minimal

WORKDIR /app

COPY target/*.jar loan-service.jar

ENTRYPOINT ["java", "-jar", "loan-service.jar"]