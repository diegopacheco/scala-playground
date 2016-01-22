name := "scala-akka-cluster-frontend-backend"

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
