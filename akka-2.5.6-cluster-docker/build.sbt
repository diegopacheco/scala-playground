name := "akka-2.5.6-cluster-docker"
organization := "com.github.diegopacheco"
version := "1.0"
scalaVersion := "2.12.3"
scalacOptions ++= Seq(
  "-deprecation"
  ,"-unchecked"
  ,"-encoding", "UTF-8"
  ,"-Xlint"
  ,"-Xverify"
  ,"-feature"
  ,"-language:postfixOps"
)

val akka = "2.5.6"

libraryDependencies ++= Seq (
  "ch.qos.logback" % "logback-classic" % "1.1.1"
  ,"com.typesafe.akka" %% "akka-testkit" % akka % "test"
  ,"com.typesafe.akka" %% "akka-actor" % akka
  ,"com.typesafe.akka" %% "akka-slf4j" % akka
  ,"com.typesafe.akka" %% "akka-cluster" % akka
  ,"org.json4s" % "json4s-jackson_2.12" % "3.5.3"
  ,"com.typesafe" % "config" % "1.3.2"
)

maintainer := "Diego Pacheco <diego.pacheco.it@gmail.com>"
dockerRepository := Some("diegopacheco")
dockerBaseImage := "java"
enablePlugins(JavaAppPackaging)
enablePlugins(DockerPlugin)
