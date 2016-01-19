name := "scala-test-testing"

version := "1.0"

scalaVersion := "2.11.7"

scalaVersion in ThisBuild := "2.11.7"

resolvers += DefaultMavenRepository 
resolvers += JavaNet1Repository
resolvers += "OSSSonatype" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.6"
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.scalamock" % "scalamock-scalatest-support_2.11" % "3.2.2"
libraryDependencies += "org.scalacheck" % "scalacheck_2.11" % "1.12.5"
