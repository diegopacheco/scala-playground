name := """play-jasper"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  
  "net.sf.jasperreports" % "jasperreports" % "6.3.0",
  "net.sf.jasperreports" % "jasperreports-fonts" % "6.0.0",
  "com.lowagie" % "itext" % "2.1.7",
  "org.olap4j" % "olap4j" % "1.2.0",
  "mysql" % "mysql-connector-java" % "6.0.3",
  
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)
 
resolvers += JavaNet1Repository
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
