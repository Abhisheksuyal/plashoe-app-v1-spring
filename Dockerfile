# Use OpenJDK 21 base image for the build stage
FROM openjdk:21-jdk AS build

# Install Maven by downloading it directly
RUN apt-get update && \
    apt-get install -y wget tar && \
    wget https://downloads.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.tar.gz && \
    tar -xzf apache-maven-3.8.6-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.8.6/bin/mvn /usr/bin/mvn && \
    apt-get clean && \
    rm apache-maven-3.8.6-bin.tar.gz

# Set the working directory
WORKDIR /app

# Copy the Maven pom.xml and source code
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package

# Use a smaller OpenJDK image to run the application
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/your-application.jar /app/your-application.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/your-application.jar"]
