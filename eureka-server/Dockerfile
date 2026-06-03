FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

COPY target/*.jar eureka-server.jar

ENTRYPOINT ["java", "-jar", "eureka-server.jar"]