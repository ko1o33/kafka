FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /opt/app
COPY pom.xml .
COPY ./src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /opt/app
EXPOSE 8851
COPY --from=builder /opt/app/target/*.jar /opt/app.jar
COPY src/main/resources/config/ ./config/
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]