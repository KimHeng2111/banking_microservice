FROM eclipse-temurin:25-jre-ubi10-minimal

WORKDIR /app

COPY target/*.jar account-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "account-service.jar"]