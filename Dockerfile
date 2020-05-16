FROM openjdk:8-alpine

ARG DIR=/opt/ebuy/mail-service

ENV DIR $DIR

WORKDIR $DIR

COPY target/*.jar $DIR/

ENTRYPOINT java  $JAVA_OPTS $DEBUG -cp ebuy-mail-service-0.0.1-SNAPSHOT.jar MailServiceApplication


