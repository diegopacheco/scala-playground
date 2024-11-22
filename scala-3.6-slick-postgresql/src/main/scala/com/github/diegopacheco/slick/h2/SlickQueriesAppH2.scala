package com.github.diegopacheco.slick.h2

import infra.Tables.*
import infra.DatabaseConfig
import slick.jdbc.H2Profile.api.*
import scala.concurrent.Await
import scala.concurrent.duration.*

object SlickQueriesAppH2 extends App{
  val (profile, dbConfig) = DatabaseConfig.getDbConfig("h2")
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
