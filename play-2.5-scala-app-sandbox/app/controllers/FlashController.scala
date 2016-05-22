package controllers

import javax.inject._
import play.api._
import play.api.mvc._

@Singleton
class FlashController @Inject() extends Controller {

  def index = Action { implicit request =>
    Ok {
       request.flash.get("success").getOrElse("Welcome!")
    }
  }

  def save = Action {
    Redirect("/flash/index").flashing("success" -> "The item has been created")
  }

}