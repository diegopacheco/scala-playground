import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.jdbc.H2Profile.api._
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import demo.Tables._

object SlickTransactionalApp extends App {

  val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile]("slick")
  val db = dbConfig.db

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