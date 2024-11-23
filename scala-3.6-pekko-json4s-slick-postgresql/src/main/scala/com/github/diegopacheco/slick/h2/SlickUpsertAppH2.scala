package com.github.diegopacheco.slick.h2

import infra.Tables.{Companies, CompaniesRow}
import infra.DatabaseConfig
import slick.jdbc.H2Profile.api.*
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*

object SlickUpsertAppH2 extends App {

  val (profile, dbConfig) = DatabaseConfig.getDbConfig("h2")
  val db = Database.forConfig("", dbConfig)

  val insertAction = Companies += CompaniesRow(0, "Tec Toy")
  val insertFuture = db.run(insertAction)
  Await.result(insertFuture, 10.seconds)

  val q = Companies.filter(_.name === "Tec Toy")
  val resultFuture = db.run(q.result).map { result =>
    println(result)
  }
  Await.result(resultFuture, 10.seconds)
}