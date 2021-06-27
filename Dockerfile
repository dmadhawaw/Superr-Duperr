FROM openjdk:8-jdk-alpine
MAINTAINER Madhawa Wijekoon <email>

ADD ./target/todoapp-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/todoapp-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080