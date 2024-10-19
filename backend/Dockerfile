FROM openjdk:17-jdk-alpine

WORKDIR /app

ARG JAR_FILE=target/sapresis-1.0.0.jar

COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh .

RUN chmod +x wait-for-it.sh

EXPOSE 8080

ENTRYPOINT ["./wait-for-it.sh"]