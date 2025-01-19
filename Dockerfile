# Use an official OpenJDK runtime for Java 21
FROM eclipse-temurin:21


WORKDIR /app

# Copy the JAR file to the container
COPY target/blogging-platform-api-0.0.1-SNAPSHOT.jar /app/blogging-platform-api-0.0.1-SNAPSHOT.jar

# Expose the port your application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "blogging-platform-api-0.0.1-SNAPSHOT.jar"]
