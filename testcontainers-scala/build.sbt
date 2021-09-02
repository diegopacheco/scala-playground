name := "testcontainers-scala"
version := "1.0"
scalaVersion := "2.13.6"

Test / fork := true
Test / logBuffered := false

val scalaTestVersion = "3.2.9";
resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"
libraryDependencies += "org.scalactic" %% "scalactic" % scalaTestVersion
libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % Test
libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % scalaTestVersion % Test
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.26" % Test

val testcontainersScalaVersion = "0.39.7"
libraryDependencies += "com.dimafeng" %% "testcontainers-scala-scalatest" % testcontainersScalaVersion % "test"
libraryDependencies += "com.dimafeng" %% "testcontainers-scala-mysql" % testcontainersScalaVersion % "test"


