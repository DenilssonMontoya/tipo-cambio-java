FROM openjdk:8-jdk-alpine
MAINTAINER dmontoyane@gmail.com
EXPOSE 9095
ARG JAR_FILE=target/tipocambio-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]