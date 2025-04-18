# Use an official Maven image with JDK 21 for building the app
FROM maven:3.9.4-eclipse-temurin-21 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy your app source code into the container
COPY . .

# Build the app (this creates a jar file in target/)
RUN mvn clean package -DskipTests

# Use JDK 21 for running the app
FROM eclipse-temurin:21-jdk

# Set working directory for the runtime image
WORKDIR /app

# Copy the jar file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Run the jar file
CMD ["java", "-jar", "app.jar"]
