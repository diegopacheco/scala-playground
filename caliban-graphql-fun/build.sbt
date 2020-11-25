name := "caliban-graphql-fun"
version := "1.0"
scalaVersion := "2.13.3"

libraryDependencies += "com.github.ghostdogpr" %% "caliban" % "0.9.3"
libraryDependencies += "com.github.ghostdogpr" %% "caliban-http4s"     % "0.9.3" // routes for http4s
libraryDependencies += "com.github.ghostdogpr" %% "caliban-cats"       % "0.9.3" // interop with cats effect
libraryDependencies += "com.github.ghostdogpr" %% "caliban-monix"      % "0.9.3" // interop with monix
libraryDependencies += "com.github.ghostdogpr" %% "caliban-federation" % "0.9.3" // interop with apollo federation
