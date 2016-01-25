import com.typesafe.sbt.SbtMultiJvm
import com.typesafe.sbt.SbtMultiJvm.MultiJvmKeys.MultiJvm

organization := "diegopacheco"
name := "scala-akka-multi-jvm-testing-fun"
version := "0.1.0-SNAPSHOT"
scalaVersion := "2.11.7"
crossScalaVersions := Seq("2.11.7", "2.10.4")
parallelExecution in Test := false
libraryDependencies ++= (akkaDependencies ++ testDependencies)
parallelExecution in Test := false 

val root = rootProject

def rootProject = Project("scala-akka-multi-jvm-testing-fun", file("."))
  .settings(Project.defaultSettings:_*)
  .settings(
    scalacOptions in Compile ++= Seq("-encoding", "UTF-8", "-target:jvm-1.6", "-deprecation", "-feature", "-unchecked", "-Xlog-reflective-calls", "-Xlint", "-language:postfixOps"),
    javacOptions in Compile ++= Seq("-source", "1.6", "-target", "1.6", "-Xlint:unchecked", "-Xlint:deprecation")
  )
  .settings(Defaults.itSettings:_*)
  .settings(SbtMultiJvm.multiJvmSettings:_*)
  .settings(compile in MultiJvm <<= (compile in MultiJvm) triggeredBy (compile in IntegrationTest))
  .settings(executeTests in IntegrationTest <<= (executeTests in Test, executeTests in MultiJvm) map {
  case (testResults, multiNodeResults)  =>
    val overall =
      if (testResults.overall.id < multiNodeResults.overall.id)
        multiNodeResults.overall
      else
        testResults.overall
    Tests.Output(overall,
      testResults.events ++ multiNodeResults.events,
      testResults.summaries ++ multiNodeResults.summaries)
 })
 .configs(IntegrationTest, MultiJvm)

val akkaVersion           = "2.4.0"

def akkaDependencies = Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion,
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
  "org.slf4j" % "log4j-over-slf4j" % "1.7.7",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)

def testDependencies = Seq(
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test,it,multi-jvm",
  "com.typesafe.akka" %% "akka-actor" % akkaVersion % "test,it,multi-jvm",
  "com.typesafe.akka" %% "akka-cluster" % akkaVersion % "test,it,multi-jvm",
  "org.scalatest" %% "scalatest" % "2.1.6" % "test,it,multi-jvm",
  "com.typesafe.akka" %% "akka-multi-node-testkit" % akkaVersion % "test,it,multi-jvm",
  "com.typesafe.akka" %% "akka-slf4j" % akkaVersion % "test,it,multi-jvm",
  "org.slf4j" % "log4j-over-slf4j" % "1.7.7" % "test,it,multi-jvm",
  "ch.qos.logback" % "logback-classic" % "1.1.2"  % "test,it,multi-jvm"
)
  
EclipseKeys.withSource := true
