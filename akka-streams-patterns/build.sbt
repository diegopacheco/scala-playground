import Dependencies._
//
// From: http://www.beyondthelines.net/computing/akka-streams-patterns/
//
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.2",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies += "com.typesafe.akka" % "akka-stream_2.12" % "2.5.3",
    libraryDependencies += scalaTest % Test
  )
