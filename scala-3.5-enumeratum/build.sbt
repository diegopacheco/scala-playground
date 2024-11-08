import scala.collection.immutable.Seq

name := "scala-3.5-enumeratum"
version := "1.0"
scalaVersion := "3.5.2"
crossScalaVersions := Seq("3.5.2")

// https://mvnrepository.com/artifact/com.beachape/enumeratum
libraryDependencies += "com.beachape" %% "enumeratum" % "1.7.5"

scalacOptions ++= Seq(
  "-Xfatal-warnings",
  "-Wunused:all"
)