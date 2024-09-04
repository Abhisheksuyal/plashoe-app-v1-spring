FROM eclipse-temurin:21-jdk
VOLUME /tmp
COPY target/PlashoeApp-0.0.1-SNAPSHOT.jar plashoe-app.jar
ENTRYPOINT ["java","-jar","/plashoe-app.jar"]