package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data._
import models.Contact

@Singleton
class ContactController @Inject() extends Controller {
  
  val contactForm: Form[Contact] = Form(
    mapping(
      "firstname" -> nonEmptyText,
      "lastname" -> nonEmptyText,
      "company" -> optional(text)
    )(models.Contact.apply)(models.Contact.unapply)
 )
 
  def form = Action { implicit request =>
    val product = contactForm.bindFromRequest.get
    Ok("product = " + product.toString)
  }
 
}