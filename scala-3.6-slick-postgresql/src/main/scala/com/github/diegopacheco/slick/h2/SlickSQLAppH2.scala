package com.github.diegopacheco.slick.h2

import infra.DatabaseConfig
import slick.jdbc.H2Profile.api.*
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*

object SlickSQLAppH2 extends App {

  val (profile, dbConfig) = DatabaseConfig.getDbConfig("h2")
  val db = Database.forConfig("", dbConfig)

  val query = sql"SELECT ID, NAME FROM COMPANIES WHERE NAME = 'Apple Inc.'".as[(Int, String)]
  val resultFuture = db.run(query).map { result =>
    result.foreach { case (id, name) =>
      println(s"Company ID: $id, Company Name: $name")
    }
  }
  Await.result(resultFuture, 10.seconds)

}