name := "scala-3x-sb-netty-prometheus-cardinality-test"
version := "1.0"
scalaVersion := "3.7.3"

fork := true
javaOptions ++= Seq(
  "--enable-native-access=ALL-UNNAMED",
  "--add-opens", "java.base/jdk.internal.misc=ALL-UNNAMED",
  "-Dio.netty.tryReflectionSetAccessible=true"
)

Test / testFrameworks += new TestFramework("com.github.sbt.junit.jupiter.api.JupiterFramework")
Test / testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")
Test / test := {
  (Test / runMain).toTask(" org.junit.platform.console.ConsoleLauncher --select-class com.app.PrometheusCardinalityJavaTest").value
}

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter-webflux" % "3.5.7",
  "org.springframework.boot" % "spring-boot-starter-actuator" % "3.5.7",
  "io.micrometer" % "micrometer-registry-prometheus" % "1.15.4",
  "io.netty" % "netty-transport-native-kqueue" % "4.1.116.Final" classifier "osx-x86_64",
  "io.projectreactor.netty" % "reactor-netty" % "1.2.9",
  "org.junit.jupiter" % "junit-jupiter-api" % "5.11.4" % Test,
  "org.junit.jupiter" % "junit-jupiter-engine" % "5.11.4" % Test,
  "org.junit.jupiter" % "junit-jupiter-params" % "5.11.4" % Test,
  "org.junit.platform" % "junit-platform-console" % "1.11.4" % Test,
  "org.junit.platform" % "junit-platform-launcher" % "1.11.4" % Test,
  "net.aichler" % "jupiter-interface" % "0.11.1" % Test
)

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "services", xs @ _*) => MergeStrategy.concat
  case PathList("META-INF", "spring", xs @ _*) => MergeStrategy.concat
  case PathList("META-INF", xs @ _*) => xs match {
    case "MANIFEST.MF" :: Nil => MergeStrategy.discard
    case "spring.handlers" :: Nil => MergeStrategy.concat
    case "spring.schemas" :: Nil => MergeStrategy.concat
    case "spring.tooling" :: Nil => MergeStrategy.concat
    case "spring.factories" :: Nil => MergeStrategy.concat
    case _ => MergeStrategy.discard
  }
  case "module-info.class" => MergeStrategy.discard
  case "application.properties" => MergeStrategy.concat
  case x => MergeStrategy.first
}

assembly / mainClass := Some("com.app.main")
