name := "scala-3.6-pekko-spring-boot-3.4.x"
version := "1.0"
scalaVersion := "3.6.2"

val pekkoVersion = "1.1.2"
libraryDependencies ++= Seq(
  "org.apache.pekko" %% "pekko-connectors-spring-web" % "1.0.2",
  "org.apache.pekko" %% "pekko-stream" % pekkoVersion,
  "javax.annotation" % "javax.annotation-api" % "1.3.2",
  "org.springframework.boot" % "spring-boot-starter-web" % "3.4.0"
)
