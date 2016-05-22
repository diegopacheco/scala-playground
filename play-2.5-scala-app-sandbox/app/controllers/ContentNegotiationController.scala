package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import play.api.libs.json.Json

@Singleton
class ContentNegotiationController @Inject() extends Controller {
  
  def list = Action { implicit request =>
    
    val items = List(1,2,3,4,5,6,7,8,9,10)
    
    render {
       case Accepts.Html() => Ok(toHtml(items)).as(HTML)
       case Accepts.Json() => Ok(Json.toJson(items))
    }
    
  }
  
  def toHtml(i:List[Int]):String = {
      var html = ""
      for( o <- i){
        html = html + "<li>" + o + "</li>" 
      }
      html
  }

}