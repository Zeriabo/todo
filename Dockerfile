FROM openjdk:20-jdk-slim-buster AS BUILD_IMAGE
ADD target/*.jar todoApplication-0.0.1.jar
ENTRYPOINT ["java", "-jar","todoApplication-0.0.1.jar"]
EXPOSE 8080