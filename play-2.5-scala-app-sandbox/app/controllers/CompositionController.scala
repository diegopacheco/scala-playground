package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import scala.concurrent.Future

object LoggingAction extends ActionBuilder[Request] {
  def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
    Logger.info("Calling action: " + request +  " - " + block)
    block(request)
  }
}

@Singleton
class CompositionController @Inject() extends Controller {
  
    def index = LoggingAction {
      Ok("Hello World. BTW Check server log... ")
    }
  
}