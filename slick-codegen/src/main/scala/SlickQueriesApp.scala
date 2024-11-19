
import demo.Tables.*
import slick.basic.DatabaseConfig
import slick.jdbc.H2Profile.api.*
import slick.jdbc.JdbcProfile

import scala.concurrent.duration.*
import scala.concurrent.Await

object DBSettings {
  val dbConfig: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile]("slick")
  val db = dbConfig.db
}

object SlickQueriesApp extends App{
  import DBSettings._
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
