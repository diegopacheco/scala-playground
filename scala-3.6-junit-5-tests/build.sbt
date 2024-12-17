name := "scala-3.6-junit-5-tests"
version := "1.0"
scalaVersion := "3.6.2"

val javaVersion = "21"
Test / fork := true

libraryDependencies ++= Seq(
  "org.junit.jupiter" % "junit-jupiter-api" % "5.11.4" % Test,
  "org.junit.jupiter" % "junit-jupiter-engine" % "5.11.4" % Test,
  "org.junit.platform" % "junit-platform-launcher"  % "1.11.4" % Test,
  "org.junit.platform" % "junit-platform-engine" % "1.11.4" % Test,
  //"net.aichler" % "jupiter-interface" % "0.9.0" % Test,
  //"com.github.sbt" % "junit-interface" % "0.13.3" % Test,
)

testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")
testFrameworks += new TestFramework("org.junit.platform.sbt.JUnitPlatformFramework")

javaOptions ++= Seq(
  "--release", javaVersion,
  "--enable-preview",
  "--add-opens", "java.base/java.lang=ALL-UNNAMED",
  "--add-opens", "java.base/java.util=ALL-UNNAMED",
)