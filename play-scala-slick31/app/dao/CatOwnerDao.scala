package dao

import scala.concurrent.Future
import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile
import models.Cat
import models.Owner
import models.OwnerCat
import models.OwnerCat

class CatOwnerDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider,od:OwnerDAO) extends HasDatabaseConfigProvider[JdbcProfile] {
    
   import driver.api._
 
   def catsWithOwners():Future[Seq[(String,String,String)]] = {
      db.run(sql"""select c.name, c.color, o.name from Cat c, Owner o, CatOwner co where c.id = co.cat_id and o.id = co.owner_id;""".as[(String,String,String)])
   }
   
   def catsWithOwnersSlick():Future[Seq[(Cat,Option[Owner])]] = {
     
     class CatOwnersTable(tag: Tag) extends Table[OwnerCat](tag, "CatOwner") {
       def ownerID = column[Int]("OWNER_ID")
       def catID   = column[Int]("CAT_ID")
       def * = (ownerID, catID) <> (OwnerCat.tupled, OwnerCat.unapply _)
     }
     
    val Cats = new CatDAO(dbConfigProvider).toTable
    val Owners = new OwnerDAO(dbConfigProvider).toTable
    val CatOwners = TableQuery[CatOwnersTable]
    
//    val leftOuterJoin = for {
//       (c, co) <- Cats joinLeft CatOwners on (_.id === _.catID)  
//    } yield (c, co)
    
    val leftOuterJoin = for {
      ((c, co), o) <- Cats joinLeft CatOwners on (_.id === _.catID) joinLeft Owners on (_._2.map(_.ownerID) === _.id)
    } yield (c, o)
    
    db.run(leftOuterJoin.result)
       
   }
  
}