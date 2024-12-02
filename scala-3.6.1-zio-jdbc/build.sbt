name := "scala-3.6.1-zio-jdbc"
version := "1.0"
scalaVersion := "3.6.1"

val zioVersion = "2.1.13"
libraryDependencies := Seq(
  "org.postgresql" % "postgresql" % "42.7.4",
  "dev.zio" %% "zio" % zioVersion,
  "dev.zio" %% "zio-streams" % zioVersion,
  "dev.zio" %% "zio-jdbc" % "0.1.2"
)
