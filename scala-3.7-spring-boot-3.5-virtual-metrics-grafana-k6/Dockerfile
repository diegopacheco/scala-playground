FROM eclipse-temurin:25-jre-jammy
WORKDIR /app
COPY target/scala-3.7.3/*-assembly-*.jar app.jar
EXPOSE 8081
EXPOSE 8182
ENTRYPOINT ["java", "-jar", "app.jar"]
