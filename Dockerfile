FROM openjdk:12-alpine

WORKDIR /opt/app

EXPOSE 8080

ENTRYPOINT ["java"]