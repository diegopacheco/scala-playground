FROM openjdk:21-slim AS builder
WORKDIR /app
COPY build.sbt /app/
COPY project /app/project
COPY src /app/src
RUN apt-get update && \
    apt-get install -y curl && \
    curl -L https://github.com/sbt/sbt/releases/download/v1.10.1/sbt-1.10.1.tgz --output sbt.tgz && \
    tar -xzf sbt.tgz && \
    mv sbt/ /usr/local/sbt && \
    ln -s /usr/local/sbt/bin/sbt /usr/local/bin/sbt && \
    sbt assembly

FROM openjdk:21-slim
WORKDIR /app
COPY --from=builder /app/target/scala-3.5.0/scala-3.5-spring-boot-3.3-java-21-assembly-1.0.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]