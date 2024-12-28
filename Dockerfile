# Step 1: Build the application in a GraalVM builder
FROM ghcr.io/graalvm/graalvm-ce:ol8-java17-22.3.0 AS builder
LABEL authors="oseitutuamoabin"
WORKDIR /workspace

# Install native-image if needed
RUN gu install native-image

# Copy application source code
COPY . .

# Build the application with Maven
RUN ./mvnw clean package -Pnative -DskipTests

# Step 2: Use Ubuntu as the base image for deployment
FROM ubuntu:20.04
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /workspace/target/genx-0.0.1-SNAPSHOT.jar /app/genx.jar

# Expose the default application port
EXPOSE 8080

# Define the entry point for the application
ENTRYPOINT ["java", "-jar", "genx.jar"]