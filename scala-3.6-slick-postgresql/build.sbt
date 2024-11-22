name := "scala-3.6-slick-postgresql"
version := "1.0"
scalaVersion := "3.6.1"

import _root_.io.github.nafg.mergify.dsl.*

inThisBuild(
  List(
    scalaVersion                        := "3.6.1",
    githubWorkflowPublishTargetBranches := Seq(),
    githubWorkflowBuild += WorkflowStep.Sbt(List("run")),
    githubWorkflowJavaVersions          := Seq(JavaSpec.temurin("21"))
  )
)

lazy val root = (project in file("."))
  .settings(
    scalaVersion := "3.6.1",
    unmanagedSourceDirectories in Compile += baseDirectory.value / "target/scala-3.6.1/src_managed/main",
    unmanagedResourceDirectories in Compile += baseDirectory.value / "target/scala-3.6.1/src_managed/main",
    unmanagedSourceDirectories in Test += baseDirectory.value / "target/scala-3.6.1/src_managed/test",
    unmanagedResourceDirectories in Test += baseDirectory.value / "target/scala-3.6.1/src_managed/test"
  )

mergifyExtraConditions := Seq(
    (Attr.Author :== "diego") ||
    (Attr.Author :== "diego") ||
    (Attr.Author :== "diego")
)

scalacOptions += "-deprecation"

val slickVersion = "3.5.2"
libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick"         % slickVersion,
  "com.typesafe.slick" %% "slick-codegen" % slickVersion,
  "com.h2database"      % "h2"            % "2.3.232",
  "org.postgresql"      % "postgresql"    % "42.7.4",
  "org.slf4j"           % "slf4j-api"     % "2.0.16",
  "ch.qos.logback"      % "logback-classic" % "1.4.11"
)

(Compile / sourceGenerators) += slick.taskValue // Automatic code generation on build

lazy val slick = taskKey[Seq[File]]("Generate Tables.scala")
slick := {
  val dir         = (Compile / sourceManaged).value
  val outputDir   = dir / "slick"
  //val url         = "jdbc:h2:mem:test;INIT=runscript from 'src/main/sql/h2-create.sql'"
  val url         = "jdbc:postgresql://localhost:5433/computers?user=postgres&password=pass"
  val jdbcDriver  = "org.postgresql.Driver"      // "org.h2.Driver"
  val slickDriver = "slick.jdbc.PostgresProfile" // "slick.jdbc.H2Profile"
  val pkg         = "demo"

  val cp = (Compile / dependencyClasspath).value
  val s  = streams.value

  runner.value
    .run(
      "slick.codegen.SourceCodeGenerator",
      cp.files,
      Array(slickDriver, jdbcDriver, url, outputDir.getPath, pkg),
      s.log
    )
    .failed foreach (sys error _.getMessage)

  val file = outputDir / pkg / "Tables.scala"
  Seq(file)
}