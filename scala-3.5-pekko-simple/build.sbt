name := "scala-3.5-pekko-simple"
version := "1.0"
scalaVersion := "3.5.0"
val pekkoVersion = "1.0.3"

libraryDependencies ++= Seq(
  "org.apache.pekko" %% "pekko-actor-typed" % pekkoVersion,
  "org.apache.pekko" %% "pekko-cluster-typed" % pekkoVersion,
  "org.apache.pekko" %% "pekko-serialization-jackson" % pekkoVersion,
  "ch.qos.logback" % "logback-classic" % "1.5.7",
  "org.slf4j" % "slf4j-api" % "2.0.15",
  "org.apache.pekko" %% "pekko-multi-node-testkit" % pekkoVersion % Test,
  "org.apache.pekko" %% "pekko-actor-testkit-typed" % pekkoVersion % Test)
