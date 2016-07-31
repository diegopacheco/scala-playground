package dao

import scala.concurrent.Future
import javax.inject.Inject
import models.Cat
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile
import slick.jdbc.GetResult

class CatDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  
  import driver.api._

  private val Cats = TableQuery[CatsTable]

  def all(): Future[Seq[Cat]] = db.run(Cats.result)
  
  def search(name:String): Future[Seq[Cat]] = db.run(Cats.filter( _.name === name).result)
  
  def update(name:String,color:String) = Future[Unit] { 
    db.run(
       Cats.filter(_.name === name)
         .map(p => (p.name,p.color))
         .update((name,color))
    )
  }
  
  def delete(name:String): Future[Int] = db.run(Cats.filter( _.name === name).delete)

  def insert(cat: Cat): Future[Unit] = db.run(Cats += cat).map { _ => () }

  def fromSQL(name:String): Future[Seq[Cat]]= {
    implicit val getCatResult = GetResult(r => Cat(r.<<, r.<<)) 
    db.run(sql"""SELECT * FROM SLICK.Cat WHERE NAME = $name """.as[(Cat)])
  }
  
  private class CatsTable(tag: Tag) extends Table[Cat](tag, "Cat") {
    def name = column[String]("NAME", O.PrimaryKey)
    def color = column[String]("COLOR")
    def * = (name, color) <> (Cat.tupled, Cat.unapply _)
  }
  
}