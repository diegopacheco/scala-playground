import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.2",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    resolvers += "TM" at "http://maven.twttr.com",
    resolvers ++= Seq(
      Resolver.sonatypeRepo("releases"),
      Resolver.sonatypeRepo("snapshots")
    ),
    libraryDependencies += scalaTest % Test,
    libraryDependencies ++= Seq("com.github.finagle" %% "finch-core" % "0.16.0-RC1")
  )
