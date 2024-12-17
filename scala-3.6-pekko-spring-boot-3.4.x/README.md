### Build
```
sbt compile
```

### Run
```
sbt run
```

```
/home/diego/.sdkman/candidates/java/23-amzn/bin/java -javaagent:/home/diego/.local/share/JetBrains/Toolbox/apps/intellij-idea-community-edition/lib/idea_rt.jar=42149:/home/diego/.local/share/JetBrains/Toolbox/apps/intellij-idea-community-edition/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /mnt/e35d88d4-42b9-49ea-bf29-c4c3b018d429/diego/git/diegopacheco/scala-playground/scala-3.6-pekko-spring-boot-3.4.x/target/scala-3.6.2/classes:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.6.2/scala3-library_3-3.6.2.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/apache/pekko/pekko-connectors-spring-web_3/1.0.2/pekko-connectors-spring-web_3-1.0.2.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/apache/pekko/pekko-stream_3/1.1.2/pekko-stream_3-1.1.2.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/javax/annotation/javax.annotation-api/1.3.2/javax.annotation-api-1.3.2.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-web/3.4.0/spring-boot-starter-web-3.4.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.15/scala-library-2.13.15.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/spring-core/6.2.0/spring-core-6.2.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/spring-context/6.2.0/spring-context-6.2.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/boot/spring-boot-autoconfigure/3.4.0/spring-boot-autoconfigure-3.4.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/apache/pekko/pekko-actor_3/1.1.2/pekko-actor_3-1.1.2.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/apache/pekko/pekko-protobuf-v3_3/1.1.2/pekko-protobuf-v3_3-1.1.2.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/reactivestreams/reactive-streams/1.0.4/reactive-streams-1.0.4.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/com/typesafe/ssl-config-core_3/0.6.1/ssl-config-core_3-0.6.1.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter/3.4.0/spring-boot-starter-3.4.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-json/3.4.0/spring-boot-starter-json-3.4.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-tomcat/3.4.0/spring-boot-starter-tomcat-3.4.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/spring-web/6.2.0/spring-web-6.2.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/spring-webmvc/6.2.0/spring-webmvc-6.2.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/spring-jcl/6.2.0/spring-jcl-6.2.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/spring-aop/6.2.0/spring-aop-6.2.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/spring-beans/6.2.0/spring-beans-6.2.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/spring-expression/6.2.0/spring-expression-6.2.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/io/micrometer/micrometer-observation/1.14.0/micrometer-observation-1.14.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/boot/spring-boot/3.4.0/spring-boot-3.4.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/com/typesafe/config/1.4.3/config-1.4.3.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/springframework/boot/spring-boot-starter-logging/3.4.0/spring-boot-starter-logging-3.4.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/jakarta/annotation/jakarta.annotation-api/2.1.1/jakarta.annotation-api-2.1.1.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/yaml/snakeyaml/2.3/snakeyaml-2.3.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.18.1/jackson-databind-2.18.1.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.18.1/jackson-datatype-jdk8-2.18.1.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.18.1/jackson-datatype-jsr310-2.18.1.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/module/jackson-module-parameter-names/2.18.1/jackson-module-parameter-names-2.18.1.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/apache/tomcat/embed/tomcat-embed-core/10.1.33/tomcat-embed-core-10.1.33.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/apache/tomcat/embed/tomcat-embed-el/10.1.33/tomcat-embed-el-10.1.33.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/apache/tomcat/embed/tomcat-embed-websocket/10.1.33/tomcat-embed-websocket-10.1.33.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/io/micrometer/micrometer-commons/1.14.0/micrometer-commons-1.14.0.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.5.12/logback-classic-1.5.12.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/apache/logging/log4j/log4j-to-slf4j/2.24.1/log4j-to-slf4j-2.24.1.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/slf4j/jul-to-slf4j/2.0.16/jul-to-slf4j-2.0.16.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.18.1/jackson-annotations-2.18.1.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.18.1/jackson-core-2.18.1.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-core/1.5.12/logback-core-1.5.12.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.16/slf4j-api-2.0.16.jar:/home/diego/.cache/coursier/v1/https/repo1.maven.org/maven2/org/apache/logging/log4j/log4j-api/2.24.1/log4j-api-2.24.1.jar com.github.diegopacheco.scalaplayground.pekko.springboot.Application

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/

 :: Spring Boot ::                (v3.4.0)

2024-12-17T02:02:32.635-08:00  INFO 1116382 --- [           main] c.g.d.s.pekko.springboot.Application$    : Starting Application. using Java 23 with PID 1116382 (/mnt/e35d88d4-42b9-49ea-bf29-c4c3b018d429/diego/git/diegopacheco/scala-playground/scala-3.6-pekko-spring-boot-3.4.x/target/scala-3.6.2/classes started by diego in /mnt/e35d88d4-42b9-49ea-bf29-c4c3b018d429/diego/git/diegopacheco/scala-playground/scala-3.6-pekko-spring-boot-3.4.x)
2024-12-17T02:02:32.639-08:00  INFO 1116382 --- [           main] c.g.d.s.pekko.springboot.Application$    : No active profile set, falling back to 1 default profile: "default"
2024-12-17T02:02:33.565-08:00  INFO 1116382 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port 8080 (http)
2024-12-17T02:02:33.579-08:00  INFO 1116382 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2024-12-17T02:02:33.579-08:00  INFO 1116382 --- [           main] o.apache.catalina.core.StandardEngine    : Starting Servlet engine: [Apache Tomcat/10.1.33]
2024-12-17T02:02:33.615-08:00  INFO 1116382 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2024-12-17T02:02:33.616-08:00  INFO 1116382 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 909 ms
[INFO] [12/17/2024 02:02:34.207] [main] [org.apache.pekko.actor.ActorSystemImpl(PekkoSpringWebPekkoStreamsSystem)] Injected ActorSystem Name -> PekkoSpringWebPekkoStreamsSystem
[INFO] [12/17/2024 02:02:34.207] [main] [org.apache.pekko.actor.ActorSystemImpl(PekkoSpringWebPekkoStreamsSystem)] Property ActorSystemName -> PekkoSpringWebPekkoStreamsSystem
2024-12-17T02:02:34.473-08:00  INFO 1116382 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port 8080 (http) with context path '/'
2024-12-17T02:02:34.483-08:00  INFO 1116382 --- [           main] c.g.d.s.pekko.springboot.Application$    : Started Application. in 2.245 seconds (process running for 2.642)
2024-12-17T02:02:47.105-08:00  INFO 1116382 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2024-12-17T02:02:47.105-08:00  INFO 1116382 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2024-12-17T02:02:47.106-08:00  INFO 1116382 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms

```

### Call

http://localhost:8080/
```
// 20241217020247
// http://localhost:8080/

{
  "attributes": {
    "attributeList": [
      
    ],
    "async": false
  }
}
```

http://localhost:8080/actorSystemName
```
PekkoSpringWebPekkoStreamsSystem
```