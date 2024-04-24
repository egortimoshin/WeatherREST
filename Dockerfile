#FROM openjdk:21
#ARG JAR_FILE=*.jar
#COPY ${JAR_FILE} application.jar
#ENTRYPOINT ["java", "-jar", "application.jar"]

FROM openjdk:21
WORKDIR /app
COPY target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]