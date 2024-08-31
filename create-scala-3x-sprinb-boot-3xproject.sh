#!/bin/sh

projectName=${PWD##*/}

touch README.md
echo "### Build
\`\`\`
sbt compile
\`\`\`

### Run
\`\`\`
sbt run
\`\`\`
" > README.md

mkdir -p src/test/scala
mkdir -p src/test/resources
mkdir -p src/test/java
mkdir -p src/main/java 
mkdir -p src/main/resources 
mkdir -p src/main/scala
mkdir -p src/main/scala/com/github/diegopacheco/scala3/sb3/
mkdir -p src/main/scala/com/github/diegopacheco/scala3/sb3/controller/
mkdir -p src/main/scala/com/github/diegopacheco/scala3/sb3/service/
mkdir -p src/main/scala/com/github/diegopacheco/scala3/sb3/config/
mkdir -p src/test/scala/com/github/diegopacheco/scala3/sb3/controller/
mkdir -p src/test/scala/com/github/diegopacheco/scala3/sb3/controller/config/
mkdir project target
touch project/build.properties
touch project/plugins.sbt 

echo 'addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "1.1.0")' > project/plugins.sbt

touch src/test/scala/com/github/diegopacheco/scala3/sb3/controller/config/ClientConfig.scala
echo 'package com.github.diegopacheco.scala3.sb3.controller.config

import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.web.client.RestTemplate

@Configuration
class ClientConfig {

  @Bean
  def restTemplate(): RestTemplate = new RestTemplate()

}' > src/test/scala/com/github/diegopacheco/scala3/sb3/controller/config/ClientConfig.scala

touch src/test/scala/com/github/diegopacheco/scala3/sb3/controller/MainControllerClientTest.scala
echo 'package com.github.diegopacheco.scala3.sb3.controller

import com.github.diegopacheco.scala3.sb3.controller.config.ClientConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Import
import org.springframework.web.client.RestTemplate

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Import(Array(classOf[ClientConfig]))
class MainControllerClientTest {

  @LocalServerPort
  private val port = 0

  @Autowired
  private val restTemplate:RestTemplate = null

  @Test
  def testIndex(): Unit = {
    val result = this.restTemplate.getForObject("http://localhost:" + port + "/",
      classOf[String])
    assertThat(result).contains("name")
    assertThat(result).contains("message")
  }

}
' > src/test/scala/com/github/diegopacheco/scala3/sb3/controller/MainControllerClientTest.scala

touch src/main/scala/com/github/diegopacheco/scala3/sb3/Application.scala
echo 'package com.github.diegopacheco.scala3.sb3

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Application

object Application {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application])
  }
}' > src/main/scala/com/github/diegopacheco/scala3/sb3/Application.scala

touch src/main/scala/com/github/diegopacheco/scala3/sb3/controller/MainController.scala
echo 'package com.github.diegopacheco.scala3.sb3.controller

import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.web.bind.annotation.{RequestMapping, RestController}
import org.springframework.web.bind.annotation.RequestMethod.GET

import scala.jdk.CollectionConverters.MapHasAsJava

@RestController
class MainController {

  @Value("${application.name}")
  val appName: String = "spring-app"

  @RequestMapping(path = Array("/"), method = Array(GET))
  def index(): java.util.Map[String, Any] = Map("name" -> appName,
    "message" -> "It works on my machine!"
  ).asJava

}' > src/main/scala/com/github/diegopacheco/scala3/sb3/controller/MainController.scala

touch src/main/resources/application.properties
echo 'application.name='"$projectName"'-app
application.description=Spring Boot 3x app using Scala 3x App
server.port=8080
management.endpoints.enabled-by-default=true
management.endpoints.web.exposure.include=*
management.endpoint.metrics.enabled=true
management.metrics.export.prometheus.enabled=true
management.metrics.enable.all=true
management.endpoint.health.enabled=true
management.endpoint.health.show-details=always
logging.file.name=logs/scala3x-spring-boot-3x-app.log' > src/main/resources/application.properties

echo 'name :="'"$projectName"'-app"
version := "1.0"
scalaVersion := "3.5.0"

val springBootVersion = "3.3.0"

libraryDependencies += "org.springframework.boot" % "spring-boot-starter-webflux" % springBootVersion
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-actuator" % springBootVersion

libraryDependencies += "org.scalatest" %% "scalatest" % "3.3.0-alpha.1" % Test
libraryDependencies += "org.junit.jupiter" % "junit-jupiter-engine" % "5.10.1" % Test
libraryDependencies += "org.springframework.boot" % "spring-boot-starter-test" % springBootVersion % Test

libraryDependencies ++= Seq(
  "io.micrometer" % "micrometer-core" % "1.10.0",
  "io.micrometer" % "micrometer-registry-prometheus" % "1.10.0",
  "org.aspectj" % "aspectjweaver" % "1.9.19"
)

javacOptions ++= Seq("--release", "21")
scalacOptions ++= Seq("-release", "21")

fork := true
javaOptions ++= Seq(
  "-XX:+EnableDynamicAgentLoading",
  "-Djdk.instrument.traceUsage"
)

enablePlugins(AssemblyPlugin)
Compile / assembly / mainClass := Some("com.github.diegopacheco.scala3.sb3.Application")

Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oD")

ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.concat
  case PathList(ps@_*) if ps.contains("module-info.class") => MergeStrategy.concat
  case PathList("META-INF", "spring-configuration-metadata.json") => MergeStrategy.concat
  case PathList("META-INF", "additional-spring-configuration-metadata.json") => MergeStrategy.concat
  case PathList("META-INF", "spring.handlers") => MergeStrategy.concat
  case PathList("META-INF", "spring.schemas") => MergeStrategy.concat
  case PathList("META-INF", "spring.factories") => MergeStrategy.concat
  case PathList("META-INF", "web-fragment.xml") => MergeStrategy.concat
  case PathList("META-INF", "spring-autoconfigure-metadata.properties") => MergeStrategy.concat
  case PathList("META-INF", "spring", "aot.factories") => MergeStrategy.concat
  case PathList("META-INF", "spring", "org.springframework.boot.autoconfigure.AutoConfiguration.imports") => MergeStrategy.concat
  case x => MergeStrategy.defaultMergeStrategy(x)
}' > build.sbt

echo "sbt.version=1.10.1" >  project/build.properties 

sbt compile
rm -rf .bsp/
rm -rf .idea/
