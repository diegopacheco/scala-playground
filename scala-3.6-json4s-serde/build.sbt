ThisBuild / scalaVersion := "3.3.4" // 3.6.1
ThisBuild / organization := "com.github.diegopacheco"

//
// Cross conversion = using scala 2.13 lib in scala 3x
// https://www.scala-lang.org/blog/2021/04/08/scala-3-in-sbt.html
//
lazy val hello = project
  .in(file("."))
  .settings(
    name := "scala-3.6-json4s-serde",
    version := "1.0",
    scalaVersion := "3.3.4", // 3.6.1
    libraryDependencies ++= Seq(
      ("org.json4s" %% "json4s-native" % "4.0.7")
        .cross(CrossVersion.for3Use2_13),
    ),
    scalacOptions ++= Seq("-deprecation")
  )