name := "monadless-fun"

version := "1.0"

scalaVersion := "2.12.2"

resolvers += "Akka Repo" at "http://repo.akka.io/releases"
resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
resolvers += "Sonatype Repo" at "https://oss.sonatype.org/content/repositories/releases/"
resolvers += "Sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

libraryDependencies += "io.monadless" %% "monadless-stdlib" % "0.0.13"
libraryDependencies += "io.monadless" %% "monadless-algebird" % "0.0.13"
libraryDependencies += "io.monadless" %% "monadless-core" % "0.0.13"
