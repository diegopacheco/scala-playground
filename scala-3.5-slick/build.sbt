name := "scala-3.5-slick"
version := "1.0"
scalaVersion := "3.5.0"

// https://mvnrepository.com/artifact/com.typesafe.slick/slick
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.5.1"

libraryDependencies ++= List(
  "org.slf4j" % "slf4j-simple" % "2.0.16",
  "com.h2database" % "h2" % "2.2.224",
  "org.scalatest" %% "scalatest" % "3.2.19" % Test
)