name := "scala-3.5-pekko-http"
version := "1.0"
scalaVersion := "3.5.0"

val PekkoVersion = "1.0.2"
val PekkoHttpVersion = "1.0.1"
libraryDependencies ++= Seq(
  "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
  "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
  "org.apache.pekko" %% "pekko-http" % PekkoHttpVersion
)

libraryDependencies += "org.slf4j" % "slf4j-simple" % "2.0.13"