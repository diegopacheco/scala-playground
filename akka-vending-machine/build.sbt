name := "akka-vending-machine"
version := "1.0"
scalaVersion := "2.13.3"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.6.10"
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.6.10"
libraryDependencies += "com.typesafe.akka" %% "akka-cluster-typed" % "2.6.10"

val AkkaVersion = "2.6.10"
libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-slf4j" % AkkaVersion,
  "ch.qos.logback" % "logback-classic" % "1.2.3"
)