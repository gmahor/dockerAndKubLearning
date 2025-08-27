FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} mdmservice.jar
ENTRYPOINT ["java","-jar","/mdmservice.jar"]