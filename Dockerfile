# Use OpenJDK 21 base image for the build stage
FROM openjdk:21-jdk AS build

# Install dependencies and Maven
RUN apt-get update && \
    apt-get install -y wget unzip && \
    wget https://downloads.apache.org/maven/maven-3/3.8.6/binaries/apache-maven-3.8.6-bin.zip && \
    unzip apache-maven-3.8.6-bin.zip && \
    mv apache-maven-3.8.6 /usr/local/maven && \
    ln -s /usr/local/maven/bin/mvn /usr/bin/mvn && \
    apt-get clean && \
    rm apache-maven-3.8.6-bin.zip

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
