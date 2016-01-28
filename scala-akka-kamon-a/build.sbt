name := "scala-akka-kamon-a"

version := "1.0"

scalaVersion := "2.11.7"

scalaVersion in ThisBuild := "2.11.7"

val akkaVersion = "2.3.14"
val kamonVersion = "0.5.2"

resolvers += "Akka Repo" at "http://repo.akka.io/releases" 
resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Sonatype Repo" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += "Twitter Repo" at "http://maven.twttr.com/"
resolvers += DefaultMavenRepository 
resolvers += JavaNet1Repository

libraryDependencies += "com.typesafe.akka" % "akka-http-experimental_2.11" % "2.0.3"
libraryDependencies += "com.typesafe.akka" % "akka-stream-experimental_2.11" % "2.0.3"

libraryDependencies += "com.typesafe.akka" % "akka-actor_2.11" % akkaVersion
libraryDependencies += "com.typesafe.akka" % "akka-remote_2.11" % akkaVersion
libraryDependencies += "com.typesafe.akka" % "akka-cluster_2.11" % akkaVersion

libraryDependencies += "com.typesafe.akka" % "akka-contrib_2.11" % akkaVersion

libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.6"
 
libraryDependencies += "io.kamon" %% "kamon-core" % kamonVersion
libraryDependencies += "io.kamon" %% "kamon-akka" % kamonVersion
libraryDependencies += "io.kamon" %% "kamon-statsd" % kamonVersion
libraryDependencies += "io.kamon" %% "kamon-log-reporter" % kamonVersion
libraryDependencies += "io.kamon" %% "kamon-system-metrics" % kamonVersion
libraryDependencies += "io.kamon" %% "kamon-akka-remote" % kamonVersion
libraryDependencies += "org.aspectj" % "aspectjweaver" % "1.8.2"


EclipseKeys.withSource := true