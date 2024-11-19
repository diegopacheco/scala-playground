import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import slick.jdbc.H2Profile.api._
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object SlickSQLApp extends App {

  // Database configuration
  val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile]("slick")
  val db = dbConfig.db

  val query = sql"SELECT ID, NAME FROM COMPANIES WHERE NAME = 'Apple Inc.'".as[(Int, String)]
  val resultFuture = db.run(query).map { result =>
    result.foreach { case (id, name) =>
      println(s"Company ID: $id, Company Name: $name")
    }
  }
  Await.result(resultFuture, 10.seconds)

}