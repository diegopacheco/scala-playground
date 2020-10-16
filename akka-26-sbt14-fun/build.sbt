name := "akka-26-sbt14-fun"
version := "1.0"
scalaVersion := "2.13.3"

libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.6.10"
libraryDependencies += "com.typesafe.akka" %% "akka-remote" % "2.6.10"
libraryDependencies += "com.typesafe.akka" %% "akka-cluster-typed" % "2.6.10"
libraryDependencies += "com.typesafe.akka" %% "akka-cluster-sharding-typed" % "2.6.10"
libraryDependencies += "com.typesafe.akka" %% "akka-stream-typed" % "2.6.10"