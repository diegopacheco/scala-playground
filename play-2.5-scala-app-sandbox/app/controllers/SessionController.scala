package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class SessionController @Inject() extends Controller {
  
    def index = Action {
        Ok("<h3>Welcome!</h3>").as(HTML).withSession("mail" -> "user@gmail.com")
    }
    
    def ha = Action { request =>
      request.session.get("mail").map { user => 
          Ok("Hello: " + user) 
      }.getOrElse { 
          Unauthorized("Oops, you are not connected") 
      }
    }
    
    def logoff = Action { Ok("Bye").withNewSession }
  
}