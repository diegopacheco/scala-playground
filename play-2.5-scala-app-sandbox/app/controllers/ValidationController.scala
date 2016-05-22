package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.mvc.Results._
import scala.concurrent.Future

class UserRequest[A](val username: Option[String], request: Request[A]) extends WrappedRequest[A](request)

object UserAction extends ActionBuilder[UserRequest] with ActionTransformer[Request, UserRequest] {
  def transform[A](request: Request[A]) = Future.successful {
    new UserRequest(request.session.get("username").orElse(null), request)
  }
}

object PermissionCheckAction extends ActionFilter[UserRequest] {
  def filter[A](input: UserRequest[A]) = Future.successful {
    if (input.username == null || ("".equals(input.username)))
      Some(Forbidden)
    else
      None
  }
}

@Singleton
class ValidationController @Inject() extends Controller {
  
  def login = Action {
     Ok("<h3>Welcome!</h3>").as(HTML).withSession("username" -> "diegopacheco")    
  }
  
  def save(itemId:String) = (UserAction andThen PermissionCheckAction) { request =>
      Ok("User " + request.username + " is logged and we did stuff for you! Host: " + request.host + " for Item: "+ itemId)
  }

}