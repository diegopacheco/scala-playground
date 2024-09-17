name := "zstream-zsink"
version := "1.0"
scalaVersion := "3.5.0"

libraryDependencies += "dev.zio" %% "zio" % "2.1.9"
libraryDependencies += "dev.zio" %% "zio-streams" % "2.1.9"

resolvers += "Maven Central" at "https://repo1.maven.org/maven2/"
resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"