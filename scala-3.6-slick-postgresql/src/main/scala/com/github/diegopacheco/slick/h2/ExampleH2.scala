package com.github.diegopacheco.slick.h2

import infra.DatabaseConfig
import infra.Tables.*
import slick.jdbc.H2Profile.api.*
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*
import scala.language.postfixOps

object ExampleH2 extends App {
  val (profile, dbConfig) = DatabaseConfig.getDbConfig("h2")
  val db = Database.forConfig("", dbConfig)

  // Using generated code. Our Build.sbt makes sure they are generated before compilation.
  val q = Companies
    .join(Computers)
    .on(_.id === _.manufacturerId)
    .map { case (co, cp) => (co.name, cp.name) }

  Await.result(
    db.run(q.result).map { result =>
      println(result.groupMap(_._1)(_._2).mkString("\n"))
    },
    60 seconds
  )
}