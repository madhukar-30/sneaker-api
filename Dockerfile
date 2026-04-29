# ---- Build Stage ----
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

# copy everything
COPY . .

# build the jar
RUN mvn clean package -DskipTests


# ---- Run Stage ----
FROM eclipse-temurin:17-jdk

WORKDIR /app

# copy jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Render provides PORT env variable
ENV PORT=8080

EXPOSE 8080

# run app
ENTRYPOINT ["sh", "-c", "java -jar app.jar --server.port=$PORT"]