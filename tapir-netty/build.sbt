name := "tapir-netty"
version := "1.0"
scalaVersion := "3.5.0"

libraryDependencies ++= Seq(
  "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.11.3",
  "com.softwaremill.sttp.tapir" %% "tapir-netty-server-sync" % "1.11.3"
)