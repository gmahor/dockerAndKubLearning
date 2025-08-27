FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} userauth.jar
ENTRYPOINT ["java","-jar","/userauth.jar"]