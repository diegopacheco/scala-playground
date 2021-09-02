name := "testcontainers-scala"
version := "1.0"
scalaVersion := "2.13.6"

Test / fork := true

resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"
libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.9"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.9" % Test

val testcontainersScalaVersion = "0.39.7"
libraryDependencies += "com.dimafeng" %% "testcontainers-scala-scalatest" % testcontainersScalaVersion % "test"
libraryDependencies += "com.dimafeng" %% "testcontainers-scala-mysql" % testcontainersScalaVersion % "test"


