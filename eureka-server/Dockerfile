FROM eclipse-temurin:25-jre-ubi10-minimal

WORKDIR /app

COPY target/*.jar eureka-server.jar

ENTRYPOINT ["java", "-jar", "eureka-server.jar"]