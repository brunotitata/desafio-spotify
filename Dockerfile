FROM openjdk:8-jre-alpine
MAINTAINER br.com.beblue.desafio
RUN mkdir /app
COPY build/libs/desafio2-0.0.1-SNAPSHOT.jar /app
WORKDIR /app
ENTRYPOINT exec java -jar desafio2-0.0.1-SNAPSHOT.jar