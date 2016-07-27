name := "scala-slick-fun"

version := "1.0"

scalaVersion := "2.11.8"

scalaVersion in ThisBuild := "2.11.8"

resolvers += DefaultMavenRepository 
resolvers += JavaNet1Repository
resolvers += "OSSSonatype" at "https://oss.sonatype.org/content/repositories/releases"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "amateras-repo" at "http://amateras.sourceforge.jp/mvn/"

libraryDependencies += "com.typesafe.slick" % "slick_2.11" % "3.1.1"
libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.6.4"
libraryDependencies += "com.typesafe.slick" % "slick-hikaricp_2.11" % "3.1.1"
libraryDependencies += "mysql" % "mysql-connector-java" % "6.0.3"
