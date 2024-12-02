name := "scala-3.6.1-zio-redis"
version := "1.0"
scalaVersion := "3.6.1"

val zioVersion = "2.1.13"
libraryDependencies := Seq(
  "dev.zio" %% "zio" % zioVersion,
  "dev.zio" %% "zio-streams" % zioVersion,
  "dev.zio" %% "zio-redis" % "1.0.0",
  "dev.zio" %% "zio-schema-protobuf" % "1.5.0"
)
