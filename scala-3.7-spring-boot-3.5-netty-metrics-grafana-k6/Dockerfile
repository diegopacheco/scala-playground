FROM sbtscala/scala-sbt:eclipse-temurin-jammy-25_37_1.11.6_3.7.3 AS builder
WORKDIR /app
COPY . .
RUN sbt clean assembly
FROM eclipse-temurin:25-jre-jammy
WORKDIR /app
COPY --from=builder /app/target/scala-3.7.3/*-assembly-*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
