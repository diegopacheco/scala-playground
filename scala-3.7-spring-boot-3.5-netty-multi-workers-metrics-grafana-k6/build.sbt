ThisBuild / version := "1.0"
ThisBuild / scalaVersion := "3.7.3"
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
  case PathList("META-INF", "spring", "org.springframework.boot.autoconfigure.AutoConfiguration.replacements") => MergeStrategy.concat
  case x => MergeStrategy.defaultMergeStrategy(x)
}

val springBootVersion = "3.5.6"

val commonDependencies = Seq(
  "org.springframework.boot" % "spring-boot-starter-webflux" % springBootVersion,
  "org.springframework.boot" % "spring-boot-starter-actuator" % springBootVersion,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.18.2",
  "io.micrometer" % "micrometer-registry-prometheus" % "1.15.5",
  "io.projectreactor.netty" % "reactor-netty-http" % "1.2.11",
  "org.aspectj" % "aspectjweaver" % "1.9.22.1",
  "io.netty.incubator" % "netty-incubator-transport-native-io_uring" % "0.0.21.Final" classifier "linux-x86_64"
)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.3.0-alpha.1" % Test,
  "org.junit.jupiter" % "junit-jupiter-engine" % "5.10.1" % Test,
  "org.springframework.boot" % "spring-boot-starter-test" % springBootVersion % Test
)

lazy val commonSettings = Seq(
  javacOptions ++= Seq("--release", "25"),
  scalacOptions ++= Seq("-release", "25"),
  fork := true,
  javaOptions ++= Seq(
    "-XX:+EnableDynamicAgentLoading",
    "-Djdk.instrument.traceUsage"
  ),
  Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oD")
)

lazy val core = (project in file("core"))
  .settings(commonSettings)
  .settings(
    name := "core",
    libraryDependencies ++= commonDependencies,
    libraryDependencies ++= Seq(
      "org.springframework.boot" % "spring-boot-starter-data-jdbc" % springBootVersion,
      "org.postgresql" % "postgresql" % "42.7.4",
      "com.zaxxer" % "HikariCP" % "6.2.1"
    ),
    libraryDependencies ++= testDependencies
  )

lazy val fastEndpoints = (project in file("fast-endpoints"))
  .dependsOn(core)
  .enablePlugins(AssemblyPlugin)
  .settings(commonSettings)
  .settings(
    name := "fast-endpoints",
    libraryDependencies ++= commonDependencies,
    libraryDependencies ++= testDependencies,
    Compile / assembly / mainClass := Some("com.github.diegopacheco.scala3.sb3.fast.FastEndpointsApplication"),
    assembly / assemblyJarName := "fast-endpoints.jar"
  )

lazy val slowEndpoints = (project in file("slow-endpoints"))
  .dependsOn(core)
  .enablePlugins(AssemblyPlugin)
  .settings(commonSettings)
  .settings(
    name := "slow-endpoints",
    libraryDependencies ++= commonDependencies,
    libraryDependencies ++= testDependencies,
    Compile / assembly / mainClass := Some("com.github.diegopacheco.scala3.sb3.slow.SlowEndpointsApplication"),
    assembly / assemblyJarName := "slow-endpoints.jar"
  )

lazy val healthChecker = (project in file("health-checker"))
  .dependsOn(core)
  .enablePlugins(AssemblyPlugin)
  .settings(commonSettings)
  .settings(
    name := "health-checker",
    libraryDependencies ++= commonDependencies,
    libraryDependencies ++= testDependencies,
    Compile / assembly / mainClass := Some("com.github.diegopacheco.scala3.sb3.health.HealthCheckerApplication"),
    assembly / assemblyJarName := "health-checker.jar"
  )

lazy val root = (project in file("."))
  .aggregate(core, fastEndpoints, slowEndpoints, healthChecker)
  .settings(
    name := "scala-3.7-spring-boot-3.5-netty-multi-workers-metrics-grafana-k6"
  )
