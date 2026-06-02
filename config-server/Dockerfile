FROM eclipse-temurin:25-jre-ubi10-minimal

WORKDIR /app

COPY target/*.jar config-server.jar

ENTRYPOINT ["java", "-jar", "config-server.jar"]