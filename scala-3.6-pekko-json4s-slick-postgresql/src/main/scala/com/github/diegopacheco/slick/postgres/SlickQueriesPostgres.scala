package com.github.diegopacheco.slick.postgres

import infra.Tables.*
import infra.DatabaseConfig
import slick.jdbc.PostgresProfile.api.*
import scala.concurrent.Await
import scala.concurrent.duration.*

object SlickQueriesPostgres extends App{

  val (profile, dbConfig) = DatabaseConfig.getDbConfig("postgres")
  val db = Database.forConfig("", dbConfig)

  import scala.concurrent.ExecutionContext.Implicits.global

  val q = for {
    co <- Companies
    cp <- Computers if co.id === cp.manufacturerId && cp.name.like("A%")
  } yield (co.name, cp.name)

  val resultFuture = db.run(q.result).map { result =>
    println(result.groupMap(_._1)(_._2).mkString("\n"))
  }

  Await.result(resultFuture, 10.seconds)
}
