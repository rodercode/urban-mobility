FROM openjdk:17-jdk-alpine as base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# Use a shell to combine commands
RUN ./mvnw dependency:resolve
COPY src ./src

FROM base as test
# Use a shell to combine commands
RUN ./mvnw test

FROM base as development
# Use a shell to combine commands
RUN ./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000"

FROM base as build
# Use a shell to combine commands
RUN ./mvnw package

FROM openjdk:17-jdk-alpine as production
EXPOSE 8080
COPY --from=build /app/target/urban-mobility.jar /app/urban-mobility.jar
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/urban-mobility.jar"]