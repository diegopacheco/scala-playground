package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.mvc.Results._

import play.api.http.HttpErrorHandler
import scala.concurrent._

class ErrorHandler extends HttpErrorHandler {

  def onClientError(request: RequestHeader, statusCode: Int, message: String) = {
    Future.successful(
      Status(statusCode)("CUSTON - A client error occurred: " + message)
    )
  }

  def onServerError(request: RequestHeader, exception: Throwable) = {
    Future.successful(
      InternalServerError("CUSTON - A server error occurred: " + exception.getMessage)
    )
  }
}

@Singleton
class ErrorHandlerController @Inject() extends Controller {
    
    def index = Action {
        throw new RuntimeException("It's all worng!")
        Ok("Its okay? NO.")
    }
  
}