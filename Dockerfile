# Stage 1: Build the application using Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy the pom.xml and source code
COPY pom.xml .
COPY src ./src

# Build the WAR file
RUN mvn clean package -DskipTests

# Stage 2: Deploy the WAR to Tomcat
FROM tomcat:10.1-jdk17
# Remove default Tomcat webapps to avoid conflicts (optional but good practice)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the built WAR file from the build stage to Tomcat's webapps directory
# Tomcat will automatically extract it. 
# Naming it ROOT.war means it will serve at the root path (/)
COPY --from=build /app/target/food-ordering-system-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
