FROM java:8
VOLUME /tmp
ARG JAR_FILE
ADD Emailing-1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]