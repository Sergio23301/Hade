# Etapa 1: Compilar
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
# Forzamos a que Maven genere el JAR ejecutable
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# CAMBIO CLAVE: Usamos un wildcard más específico para agarrar el jar de Spring Boot
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
# Agregamos un comando más robusto para ejecutarlo
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]