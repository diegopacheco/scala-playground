name := "tapir-http4s"
version := "1.0"
scalaVersion := "3.5.0"

libraryDependencies ++= Seq(
  "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.11.3",
  "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % "1.11.3",
  "com.softwaremill.sttp.client3" %% "core" % "3.9.7",
  "org.http4s" %% "http4s-blaze-server" % "0.23.16",
)