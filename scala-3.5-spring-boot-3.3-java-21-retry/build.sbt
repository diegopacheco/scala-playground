name :="scala-3.5-spring-boot-3.3-java-21-retry-app"
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
}
