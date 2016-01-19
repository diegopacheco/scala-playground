name := "scala-csv-to-json"

version := "1.0"

scalaVersion := "2.11.7"

scalaVersion in ThisBuild := "2.11.7"

resolvers += DefaultMavenRepository 
resolvers += JavaNet1Repository
resolvers += "OSSSonatype" at "https://oss.sonatype.org/content/repositories/releases"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies += "com.github.tototoshi" %% "scala-csv" % "1.3.0-SNAPSHOT"
libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.6.3"