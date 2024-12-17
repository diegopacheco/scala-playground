name := "scala-3.6-junit-5-tests"
version := "1.0"
scalaVersion := "3.6.2"

val javaVersion = "21"
Test / fork := true

libraryDependencies ++= Seq(
  "org.junit.jupiter" % "junit-jupiter-api" % "5.11.4" % Test,
  "com.github.sbt.junit" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test
)

//ThisBuild / libraryDependencies += "com.github.sbt.junit" % "jupiter-interface" % JupiterKeys.jupiterVersion.value % Test
//testOptions += Tests.Argument(TestFrameworks.JUnit, "-q", "-v")
//testFrameworks += new TestFramework("org.junit.platform.sbt.JUnitPlatformFramework")

javaOptions ++= Seq(
  "--enable-preview",
  "--add-opens", "java.base/java.lang=ALL-UNNAMED",
  "--add-opens", "java.base/java.util=ALL-UNNAMED",
)