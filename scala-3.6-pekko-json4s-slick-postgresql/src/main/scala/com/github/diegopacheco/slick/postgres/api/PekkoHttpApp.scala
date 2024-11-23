package com.github.diegopacheco.slick.postgres.api

import com.github.diegopacheco.slick.postgres.infra.DatabaseConfig
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.http.scaladsl.Http
import org.slf4j.LoggerFactory
import slick.jdbc.PostgresProfile.api.*

import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn

object PekkoHttpApp extends App {
  val logger = LoggerFactory.getLogger(this.getClass)
  implicit val system: ActorSystem = ActorSystem("my-system")
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val (profile, dbConfig) = DatabaseConfig.getDbConfig("postgres")
  val db = Database.forConfig("", dbConfig)
  val computerRepository = new ComputerRepository(db)
  val computerRoutes = new ComputerRoutes(computerRepository)

  logger.info("Starting server at http://localhost:8080/")
  logger.info("Try http://localhost:8080/computers")
  val bindingFuture = Http().newServerAt("localhost", 8080).bindFlow(computerRoutes.route)

  println("Press RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())

}