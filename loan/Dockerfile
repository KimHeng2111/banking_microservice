FROM eclipse-temurin:25-jre-ubi10-minimal

WORKDIR /app

COPY target/*.jar loan-service.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "loan-service.jar"]