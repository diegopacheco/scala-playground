name := "scalatra-simple"

version := "1.0"

scalaVersion := "2.11.7"

val akkaVersion = "2.4.1"

scalaVersion in ThisBuild := "2.11.7"

resolvers += "Akka Repo" at "http://repo.akka.io/releases" 
resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Sonatype Repo" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
resolvers += "Twitter Repo" at "http://maven.twttr.com/"
resolvers += DefaultMavenRepository 
resolvers += JavaNet1Repository

libraryDependencies += "org.scalatra" % "scalatra_2.11" % "2.4.0"
libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "8.1.8.v20121106"

