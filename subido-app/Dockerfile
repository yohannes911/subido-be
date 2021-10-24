FROM amazoncorretto:11

VOLUME /tmp

EXPOSE 8080 8081

ENV JAVA_OPTS=""

ARG JAR_FILE=build/libs/subido-app-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} subido.jar

ENTRYPOINT java ${JAVA_OPTS} -jar /subido.jar
