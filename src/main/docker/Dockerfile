FROM openjdk:16-alpine3.13
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT [ "java", "-jar", "application.jar" ]