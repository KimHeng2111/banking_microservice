FROM eclipse-temurin:25-jre-ubi10-minimal

WORKDIR /app

COPY target/*.jar account-service.jar

ENTRYPOINT ["java", "-jar", "account-service.jar"]