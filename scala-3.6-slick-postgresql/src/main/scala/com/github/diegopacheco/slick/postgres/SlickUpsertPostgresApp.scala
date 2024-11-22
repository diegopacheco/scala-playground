package com.github.diegopacheco.slick.postgres

import infra.Tables.{Companies, CompaniesRow}
import infra.DatabaseConfig
import slick.jdbc.PostgresProfile.api.*
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*

object SlickUpsertPostgresApp extends App {

  val (profile, dbConfig) = DatabaseConfig.getDbConfig("postgres")
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