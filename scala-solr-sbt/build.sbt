name := "scala-solr-sbt"

version := "1.0"

scalaVersion := "2.11.7"

scalaVersion in ThisBuild := "2.11.7"

resolvers += DefaultMavenRepository 
resolvers += JavaNet1Repository
resolvers += "OSSSonatype" at "https://oss.sonatype.org/content/repositories/releases"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
resolvers += "amateras-repo" at "http://amateras.sourceforge.jp/mvn/"

libraryDependencies += "jp.sf.amateras.solr.scala" %% "solr-scala-client" % "0.0.12"