package com.github.diegopacheco.slick.postgres

import infra.Tables.{Companies, CompaniesRow, Computers, ComputersRow}
import infra.DatabaseConfig
import slick.jdbc.PostgresProfile.api.*
import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.*

object SlickChainPostgresApp extends App{
  val (profile, dbConfig) = DatabaseConfig.getDbConfig("postgres")
  val db = Database.forConfig("", dbConfig)

  val txAction = (for {
    companyOpt <- Companies.filter(_.name === "Tabajara").result.headOption
    companyId <- companyOpt match {
      case Some(company) => DBIO.successful(company.id)
      case None => (Companies returning Companies.map(_.id)) += CompaniesRow(0, "Tabajara")
    }
    _ <- Computers += ComputersRow(0, "Creisons PC XL", companyId)
  } yield ()).transactionally
  Await.result(db.run(txAction), 10.seconds)

  val resultFuture = db.run(
    (for {
      companies <- Companies.result
      computers <- Computers.result
    } yield (companies, computers)).map { case (companies, computers) =>
      println("Companies:")
      companies.foreach(println)
      println("Computers:")
      computers.foreach(println)
    }
  )
  Await.result(resultFuture, 10.seconds)
}
