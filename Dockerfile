FROM openjdk:14-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=build/libs/node-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]