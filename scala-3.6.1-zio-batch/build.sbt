name := "scala-3.6.1-zio-batch"
version := "1.0"
scalaVersion := "3.6.1"

val zioVersion = "2.1.13"
libraryDependencies := Seq(
  "dev.zio" %% "zio" % zioVersion,
  "dev.zio" %% "zio-streams" % zioVersion
)