name := """play-jasper"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  
  "net.sf.jasperreports" % "jasperreports" % "6.2.2"  withSources() ,
  "net.sf.jasperreports" % "jasperreports-functions" % "6.2.2",
  "net.sf.jasperreports" % "jasperreports-chart-themes" % "6.2.2",
  
  "mysql" % "mysql-connector-java" % "6.0.3" withSources(),
  "org.olap4j" % "olap4j" % "1.2.0"  withSources() ,
  
  "com.adobe.xmp" % "xmpcore" % "5.1.1",
  "org.apache.poi" % "poi" % "3.10.1",
  "org.codehaus.groovy" % "groovy-all" % "2.4.5",
  "org.mozilla" % "rhino" % "1.7.6",
  "net.sourceforge.jexcelapi" % "jxl" % "2.6.10",
  "commons-javaflow" % "commons-javaflow" % "20060411", 
  "mondrian" % "mondrian" % "3.1.1.12687",
  "jaxen" % "jaxen" % "1.1.1",
  "org.apache.xmlgraphics" % "batik-bridge" % "1.8",
  "org.apache.xmlgraphics" % "batik-svggen" % "1.8",
  
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)
 
resolvers += "Jasper" at "https://jaspersoft.artifactoryonline.com/jaspersoft/repo/"
resolvers += "JasperSoft" at "https://jaspersoft.artifactoryonline.com/jaspersoft/jaspersoft-repo/"
resolvers += "Jasper3rd" at "https://jaspersoft.artifactoryonline.com/jaspersoft/jaspersoft-3rd-party/"
resolvers += "mondrian-repo-cache" at "https://jaspersoft.artifactoryonline.com/jaspersoft/mondrian-repo-cache/"
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

EclipseKeys.withSource := true
transitiveClassifiers := Seq("sources")