name := "testcontainers-scala"
version := "1.0"
scalaVersion := "2.13.6"

Test / fork := true
Test / logBuffered := false

val scalaTestVersion = "3.2.9"
resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"
libraryDependencies += "org.scalactic" %% "scalactic" % scalaTestVersion
libraryDependencies += "org.scalatest" %% "scalatest" % scalaTestVersion % Test
libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % scalaTestVersion % Test
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.26" % Test

val testcontainersScalaVersion = "0.39.9"
libraryDependencies += "com.dimafeng" %% "testcontainers-scala-scalatest" % testcontainersScalaVersion % "test"
libraryDependencies += "com.dimafeng" %% "testcontainers-scala-mysql" % testcontainersScalaVersion % "test"

resolvers += ("Typesafe" at "http://repo.typesafe.com/typesafe/releases/").withAllowInsecureProtocol(true)
resolvers += ("Java.net Maven2 Repository" at "http://download.java.net/maven/2/").withAllowInsecureProtocol(true)
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.31"
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.14.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.14.1"
