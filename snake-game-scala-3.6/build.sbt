name := "snake-game-scala-3.6"
version := "1.0"
scalaVersion := "3.6.1"

libraryDependencies += "org.jline" % "jline" % "3.27.1"

import sbtassembly.AssemblyPlugin.autoImport._
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
mainClass in assembly := Some("com.github.diegopacheco.snakegame.SnakeGame")