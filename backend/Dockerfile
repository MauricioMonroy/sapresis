FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk
WORKDIR /app

COPY --from=build /app/target/sapresis-0.0.1-SNAPSHOT.jar app.jar
COPY src/main/resources/application.properties /app/config/application.properties

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
