name := "fatjar-assembly-sbt-1.5.0"
version := "1.0"
scalaVersion := "2.13.6"

// https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.12.0"

Compile / mainClass  := Some("SimpleApp")
assembly / mainClass := Some("SimpleApp")

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
