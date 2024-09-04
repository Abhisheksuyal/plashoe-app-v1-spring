# Use the official Maven image with OpenJDK 21
FROM maven:3.8.6-openjdk-21 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven pom.xml and source code
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package

# Use the official OpenJDK 21 image for running the application
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/your-application.jar /app/your-application.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/your-application.jar"]
