FROM eclipse-temurin:21.0.4_7-jdk-ubi9-minimal
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]