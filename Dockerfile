# Use an official OpenJDK runtime as a parent image
FROM openjdk:11-slim

# Set the working directory
WORKDIR /app

# Copy the Maven project files
COPY . .

# Build the application
RUN ./mvnw clean install

# Expose the port the app runs on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "target/tinder-1.0-SNAPSHOT.jar"]