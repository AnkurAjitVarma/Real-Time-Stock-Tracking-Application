# Stage 1: Build Stage
FROM sbtscala/scala-sbt:amazoncorretto-al2023-21.0.5_1.10.5_2.13.15 as builder

# Set the working directory
WORKDIR /app

# Copy the application files
COPY . .

# Build the application and package it as a fat JAR
RUN sbt clean assembly

# Stage 2: Runtime Stage
FROM amazoncorretto:21-al2023

# Set the working directory for the runtime
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=builder /app/target/scala-2.13/*.jar /app/app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "/app/app.jar"]