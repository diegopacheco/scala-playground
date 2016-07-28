package controllers

import dao.CatDAO
import javax.inject._
import play.api._
import play.api.mvc._
import models.Cat

@Singleton
class HomeController @Inject() (catDao: CatDAO)  extends Controller {
  
  import play.api.libs.concurrent.Execution.Implicits.defaultContext
  
  def list =  Action.async { implicit request =>
    catDao.all().map { x => Ok(x.toString()) }
  }
  
  def search(searchName:String) =  Action.async { implicit request =>
     catDao.search(searchName).map { c => Ok(c.toString()) }
  }
  
  def update(name:String,color:String) =  Action.async { implicit request =>
     catDao.update(name,color).map { c => Ok(c.toString()) }
  }
  
  def delete(searchName:String) =  Action.async { implicit request =>
     catDao.delete(searchName).map { c => Ok(c.toString()) }
  }
  
  def add(key:String) = Action.async {
    val c:Cat = new Cat(key,"white")
    catDao.insert(c).map { x => Ok(x.toString()) }
  }
  
  def index = Action {
    Ok("/keys to list. /set/id to create a new one.")
  }
  
}
