name := "cats-scala-fp"
version := "1.0"
scalaVersion := "2.12.6"

libraryDependencies += "org.typelevel" %% "cats" % "0.9.0"
libraryDependencies += "org.typelevel" %% "cats-free" % "1.1.0"

scalacOptions += "-Ypartial-unification"
