import scala.collection.immutable.Seq

name := "scala-3.5-newgivens-context"
version := "1.0"
scalaVersion := "3.5.0"

scalacOptions ++= Seq(
  "-source:future",
  "-language:experimental.modularity"
)