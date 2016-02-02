name := "scala-camel-simple"

version := "1.0"

scalaVersion := "2.11.7"

scalaVersion in ThisBuild := "2.11.7"

val akkaVersion = "2.4.1"

resolvers += "Akka Repo" at "http://repo.akka.io/releases" 
resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Sonatype Repo" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += "Twitter Repo" at "http://maven.twttr.com/"
resolvers += DefaultMavenRepository 
resolvers += JavaNet1Repository


libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % akkaVersion
libraryDependencies += "com.typesafe.akka" % "akka-remote_2.11" % akkaVersion
libraryDependencies += "com.typesafe.akka" % "akka-cluster_2.11" % akkaVersion
libraryDependencies += "com.typesafe.akka" % "akka-camel_2.11" % akkaVersion

libraryDependencies += "org.apache.camel" % "camel-aws" % "2.13.4"
libraryDependencies += "org.apache.camel" % "camel-http4" % "2.13.4"

libraryDependencies += "org.slf4j" % "slf4j-log4j12" % "1.6.6"
libraryDependencies += "log4j" % "log4j" % "1.2.17"

libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.6"

EclipseKeys.withSource := true