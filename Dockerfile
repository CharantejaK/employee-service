FROM openjdk:14-jdk-alpine
VOLUME /tmp
ADD build/libs/employee*.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 9090
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

