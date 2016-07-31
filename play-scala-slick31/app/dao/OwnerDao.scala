package dao

import scala.concurrent.Future
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile
import models.Owner

class OwnerDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  
  import driver.api._
  
  val Owners = TableQuery[OwnersTable]
  
  class OwnersTable(tag: Tag) extends Table[Owner](tag, "Owner") {
    def id   = column[Int]("ID", O.PrimaryKey)
    def name = column[String]("NAME")
    def * = (id, name) <> (Owner.tupled, Owner.unapply _)
  }
  
  def all(): Future[Seq[Owner]] = db.run(Owners.result)
  
  def search(name:String): Future[Seq[Owner]] = db.run(Owners.filter( _.name === name).result)
  
  def update(id:Int,name:String) = Future[Unit] { 
    db.run(
       Owners.filter(_.id === id)
         .map(p => (p.name))
         .update((name))
    )
  }
  
  def delete(name:String): Future[Int] = db.run(Owners.filter( _.name === name).delete)

  def insert(owner:Owner): Future[Unit] = db.run(Owners += owner).map { _ => () }
  
  def toTable = TableQuery[OwnersTable]
  
}