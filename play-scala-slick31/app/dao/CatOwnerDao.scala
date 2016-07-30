package dao

import scala.concurrent.Future

import javax.inject.Inject
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import slick.driver.JdbcProfile


class CatOwnerDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
    
   import driver.api._
 
  
    def catsWithOwners():Future[Seq[(String,String,String)]] = {
      db.run(sql"""select c.name, c.color, o.name from Cat c, Owner o, CatOwner co where c.id = co.cat_id and o.id = co.owner_id;""".as[(String,String,String)])
    }
  
}