import scala.collection.immutable.Seq

name := "scala-3-6-ids-poc"
version := "1.0"
scalaVersion := "3.6.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % "test"

val json4sVersion = "4.0.7"
libraryDependencies += "org.json4s" %% "json4s-native" % json4sVersion excludeAll (
  ExclusionRule("org.scala-lang", "scala-library")
)

javaOptions ++= Seq(
  "--add-opens", "java.base/java.util=ALL-UNNAMED"
)