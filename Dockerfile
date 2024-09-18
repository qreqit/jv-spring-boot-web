# Builder stage
FROM openjdk:17-jdk-alpine AS builder
WORKDIR /application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

# Final stage
FROM openjdk:17-jdk-alpine
WORKDIR /application
COPY --from=builder /application/dependencies/ ./dependencies/
COPY --from=builder /application/spring-boot-loader/ ./spring-boot-loader/
COPY --from=builder /application/snapshot-dependencies/ ./snapshot-dependencies/
COPY --from=builder /application/application/ ./application/
COPY target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
EXPOSE 8080