package com.github.diegopacheco.slick.h2.codegen

import slick.codegen.SourceCodeGenerator

object SlickCodegenH2App extends App {
  val slickDriver = "slick.jdbc.H2Profile"
  val jdbcDriver = "org.h2.Driver"
  val url = "jdbc:h2:mem:test;DATABASE_TO_UPPER=false;INIT=runscript from 'src/main/sql/h2-create.sql'"
  val outputDir = "target/scala-3.6.1/src_managed/main/h2/slick"
  val pkg = "h2demo"
  val db = slick.jdbc.H2Profile.api.Database.forURL(url, driver = jdbcDriver)

  println(s"Codegen starting:... ")
  private val codegenFuture: Unit = SourceCodeGenerator.run(
    slickDriver, jdbcDriver, url, outputDir, pkg, None, None, false, false
  )

  println(s"Codegen done: ${outputDir}/${pkg}/Tables.scala DONE!")
}