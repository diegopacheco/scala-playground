name := "clip-args-fun"
version := "1.0"
scalaVersion := "2.13.4"

assemblyJarName in assembly := "app.jar"
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

libraryDependencies += "io.github.vigoo" %% "clipp" % "0.2.0"
