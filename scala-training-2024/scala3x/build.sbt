name := "scala3x-training-exercises"
version := "1.0"
scalaVersion := "3.6.1"
val slickVersion = "3.5.2"

// https://mvnrepository.com/artifact/org.scalatest/scalatest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % Test

libraryDependencies += "com.typesafe.slick" %% "slick"         % slickVersion
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % slickVersion
libraryDependencies += "org.apache.pekko"   %% "pekko-actor"   % "1.1.2"
libraryDependencies += "org.apache.pekko"   %% "pekko-stream"  % "1.1.2"
libraryDependencies += "org.apache.pekko"   %% "pekko-http"    % "1.1.0"
libraryDependencies += "org.json4s"         %% "json4s-native" % "4.0.7"
libraryDependencies += "org.postgresql"      % "postgresql"    % "42.7.4"
libraryDependencies += "org.slf4j"           % "slf4j-api"     % "2.0.16"
libraryDependencies += "ch.qos.logback"      % "logback-classic" % "1.4.11"