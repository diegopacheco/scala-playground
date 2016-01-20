name := "scala-some-fp"

version := "1.0"

scalaVersion := "2.11.7"

scalaVersion in ThisBuild := "2.11.7"

resolvers += DefaultMavenRepository 
resolvers += JavaNet1Repository
resolvers += "OSSSonatype" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.6"
libraryDependencies += "junit" % "junit" % "4.12"
