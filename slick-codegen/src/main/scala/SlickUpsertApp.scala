import demo.Tables.{Companies, CompaniesRow}
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile
import scala.concurrent.duration.*
import scala.concurrent.ExecutionContext.Implicits.global
import slick.jdbc.H2Profile.api.*
import scala.concurrent.Await

object SlickUpsertApp extends App {

  object DBSettings {
    val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile]("slick")
    val db = dbConfig.db
  }
  import DBSettings._

  val insertAction = Companies += CompaniesRow(0, "Tec Toy")
  val insertFuture = db.run(insertAction)
  Await.result(insertFuture, 10.seconds)

  val q = Companies.filter(_.name === "Tec Toy")
  val resultFuture = db.run(q.result).map { result =>
    println(result)
  }
  Await.result(resultFuture, 10.seconds)
}