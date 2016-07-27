package com.github.diegopacheco.scala.slick.fun.mysql

import slick.jdbc.JdbcBackend.Database
import slick.driver.MySQLDriver.api._
import com.typesafe.config.ConfigFactory
import scala.concurrent.Await
import java.util.concurrent.Executors
import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.concurrent.Future

object HelloSlickMySQL extends App {
  
  val pool = Executors.newCachedThreadPool()
  implicit val ec = ExecutionContext.fromExecutorService(pool)
  
  val dbConfig = ConfigFactory.load().getConfig("db")
  Class.forName(dbConfig.getString("driver"))
  val db = Database.forConfig("db")
  println(db)

  case class People(id: Int, name: String)

  class Peoples(tag: Tag) extends Table[(Int, String)](tag, "People") {
    def id = column[Int]("ID", O.PrimaryKey)
    def name = column[String]("NAME")
    def * = (id, name)
  }

  val peoples = TableQuery[Peoples]
  println(peoples)

  val schema = peoples.schema
  println(schema)
  
  try{
    Await.result(db.run(DBIO.seq(
      schema.create
    )),10 seconds)  
  }catch{
    case e:Exception => Unit
  }
  
  try{
    Await.result(db.run(DBIO.seq(
      peoples += (1, "Diego Pacheco"),
      peoples += (2, "Sam"),
      peoples += (3, "John")
    )),10 seconds)
  }catch{
    case e:Exception => Unit
  }
  
  val q = for (c <- peoples) yield c.name
  val a = q.result
  val f: Future[Seq[String]] = db.run(a)
  f.onSuccess { case s => println(s"Result: $s") }
  
  db.close()
  println("DONE")

}