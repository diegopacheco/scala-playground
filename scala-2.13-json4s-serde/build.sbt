name := "scala-3.6-json4s-serde"
version := "1.0"
scalaVersion := "2.13.15"

val json4sVersion = "4.0.7"

libraryDependencies += "org.json4s" %% "json4s-native" % json4sVersion excludeAll (
  ExclusionRule("org.scala-lang", "scala-library")
)

dependencyOverrides += "org.scala-lang" % "scala-library" % scalaVersion.value
dependencyOverrides += "org.scala-lang" % "scala-reflect" % scalaVersion.value
dependencyOverrides += "org.scala-lang" % "scala-compiler" % scalaVersion.value

scalacOptions ++= Seq("-deprecation")