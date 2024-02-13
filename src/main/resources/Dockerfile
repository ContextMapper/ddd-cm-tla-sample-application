FROM openjdk:17-slim-buster

# add JAR file
ARG JAR_FILE
ADD target/${JAR_FILE} /srv/web/lib/tla-sample-app.jar

RUN mkdir /srv/web/tmp
RUN mkdir /srv/web/conf

ENV DEBUG_OPTS=""
ENV JAVA_OPTS=""

EXPOSE 8080
ENTRYPOINT exec java $DEBUG_OPTS $JAVA_OPTS -jar /srv/web/lib/tla-sample-app.jar --debug
