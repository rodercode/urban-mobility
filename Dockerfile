FROM openjdk:17-jdk-alpine
EXPOSE 8080
ADD target/urban-mobility-0.0.1-SNAPSHOT.jar urban-mobility.jar
ENTRYPOINT ["java", "-jar", "/urban-mobility.jar"]