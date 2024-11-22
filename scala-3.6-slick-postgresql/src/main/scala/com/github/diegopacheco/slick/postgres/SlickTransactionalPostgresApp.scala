package com.github.diegopacheco.slick.postgres

import infra.Tables.*
import infra.DatabaseConfig
import slick.jdbc.PostgresProfile.api.*
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*

object SlickTransactionalPostgresApp extends App {

  val (profile, dbConfig) = DatabaseConfig.getDbConfig("postgres")
  val db = Database.forConfig("", dbConfig)

  // Define a case class to map the result
  case class Company(id: Int, name: String)

  val updateAction = (for {
    companyOpt <- Companies.filter(_.name === "Apple Inc.").result.headOption
    result <- companyOpt match {
      case Some(company) =>
        Companies.filter(_.id === company.id).map(_.name).update("Apple Inc. Updated")
      case None =>
        DBIO.successful(0)
    }
  } yield result).transactionally

  val resultFuture = db.run(updateAction).map { result =>
    println(s"Number of rows updated: $result")
  }

  Await.result(resultFuture, 10.seconds)
}