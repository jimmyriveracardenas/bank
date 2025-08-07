FROM eclipse-temurin:17-jre-alpine AS builder
WORKDIR /app
COPY target/ms-cuenta-0.0.1-SNAPSHOT.jar app.jar

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/app.jar .
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]