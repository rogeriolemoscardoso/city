FROM openjdk:8-jdk-alpine

RUN apk update && apk upgrade

LABEL maintainer="rogerio.lemos.cardosoo@gmail.com"

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=/target/city-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} city-0.0.1-SNAPSHOT.jar


ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/city-0.0.1-SNAPSHOT.jar"]
