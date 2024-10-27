# Usar una imagen base de Maven para construir la aplicación
FROM maven:3.8.5-openjdk-17 AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo pom.xml y el resto del código fuente
COPY pom.xml .
COPY src ./src

# Construir el JAR
RUN mvn clean package -DskipTests

# Fase de ejecución: Usar una imagen más ligera para ejecutar el JAR
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copiar el JAR generado desde la fase de construcción
COPY --from=build /app/target/sapresis-1.0.0.jar app.jar

# Exponer el puerto 8080 para la aplicación
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
