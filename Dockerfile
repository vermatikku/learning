# Use Amazon Corretto 21 as the base image for Java 21
FROM amazoncorretto:21.0.6

# ARG to define the location of the JAR file to be copied into the container
ARG JAR_FILE=target/*.jar

# Copy the JAR file from the build to the container's working directory
COPY ${JAR_FILE} app.jar

# Set the entrypoint to run the JAR file with Java
ENTRYPOINT ["java", "-jar", "/app.jar"]
