name := "scala-3.6-spring-boot-3.4-data-jdbc"
version := "1.0"
scalaVersion := "3.6.2"
val springVersion = "3.4.0"

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter-webflux" % springVersion,
  "org.springframework.boot" % "spring-boot-starter-data-jdbc" % springVersion,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.18.2",
  "com.zaxxer" % "HikariCP" % "6.2.1",
  "mysql" % "mysql-connector-java" % "8.0.33",
)

Compile / mainClass :=
  Some("com.github.diegopacheco.scalaplayground.springboot.Application")

assembly / assemblyJarName := "scala3-springboot-3.jar"

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "spring.factories") => MergeStrategy.concat
  case PathList("META-INF", "spring", "org.springframework.boot.autoconfigure.AutoConfiguration.imports") => MergeStrategy.concat
  case PathList("META-INF", "services", "org.slf4j.spi.SLF4JServiceProvider") => MergeStrategy.concat
  case PathList("META-INF", "services", "java.sql.Driver") => MergeStrategy.concat
  case PathList("META-INF", _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

assembly / packageOptions += Package.ManifestAttributes(
  "Main-Class" -> "com.github.diegopacheco.scalaplayground.springboot.Application",
  "Implementation-Version" -> version.value,
  "Implementation-Vendor-Id" -> "com.github.diegopacheco"
)