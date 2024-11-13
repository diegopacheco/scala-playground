name := "scala-2.13-benchs"
version := "1.0"
scalaVersion := "2.13.15"

libraryDependencies ++= Seq(
  "org.openjdk.jmh" % "jmh-core" % "1.37",
  "org.openjdk.jmh" % "jmh-generator-annprocess" % "1.37"
)

scalacOptions += "-Ymacro-annotations"

//import sbtassembly.AssemblyPlugin.autoImport.*
//import scala.collection.immutable.Seq
//assembly / assemblyMergeStrategy := {
//  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
//  case PathList("META-INF", xs @ _*) => MergeStrategy.concat
//  case x => MergeStrategy.first
//}

//Compile / mainClass := Some("com.github.diegopacheco.scala36.benchs.BenchmarkRunner")
//assembly / assemblyOption := (assembly / assemblyOption).value.withIncludeScala(true)
//assembly / assemblyJarName := s"${name.value}-${version.value}.jar"
//assembly / packageOptions += Package.ManifestAttributes(
//  "Main-Class" -> "com.github.diegopacheco.scala36.benchs.BenchmarkRunner"
//)

import pl.project13.scala.sbt.JmhPlugin
enablePlugins(JmhPlugin)
