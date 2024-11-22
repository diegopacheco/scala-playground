package com.github.diegopacheco.slick.postgres

import slick.jdbc.PostgresProfile.api.*
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*
import infra.DatabaseConfig

object SlickSQLPostgresApp extends App {

  val (profile, dbConfig) = DatabaseConfig.getDbConfig("postgres")
  val db = Database.forConfig("", dbConfig)

  val query = sql"SELECT ID, NAME FROM COMPANIES WHERE NAME = 'Apple Inc.'".as[(Int, String)]
  val resultFuture = db.run(query).map { result =>
    result.foreach { case (id, name) =>
      println(s"Company ID: $id, Company Name: $name")
    }
  }
  Await.result(resultFuture, 10.seconds)

}