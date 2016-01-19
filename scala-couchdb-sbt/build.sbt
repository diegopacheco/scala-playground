name := "scala-couchdb-sbt"

version := "1.0"

scalaVersion := "2.11.7"

scalaVersion in ThisBuild := "2.11.7"

resolvers += DefaultMavenRepository 
resolvers += JavaNet1Repository
resolvers += "OSSSonatype" at "https://oss.sonatype.org/content/repositories/releases"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

libraryDependencies += "com.ibm" %% "couchdb-scala" % "0.5.1"
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.5"
libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.7.5"