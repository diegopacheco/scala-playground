package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data.Form
import play.api.data.Forms._
import play.api.data._
import models.Contact
import play.api.i18n.Messages
import play.api.i18n.I18nSupport
import play.api.i18n.MessagesApi

@Singleton
class ContactController @Inject() (val messagesApi:MessagesApi) extends Controller with I18nSupport{

  val contactForm: Form[Contact] = Form(
    mapping(
      "firstname" -> nonEmptyText,
      "lastname" -> nonEmptyText,
      "company" -> optional(text))(models.Contact.apply)(models.Contact.unapply))

  def index = Action { implicit request =>
    Logger.info("index called. ")
    Ok(views.html.contact_details(None, null))
  }

  def blank = Action { implicit request =>
    Ok(views.html.contact_details(None, contactForm))
  }

  def details(id: Long) = Action { implicit request =>
    Logger.info("details called. id: " + id)
    val contact = Contact("", "", None)
    Ok(views.html.contact_details(Some(id), contactForm.fill(contact)))
  }

  def insert()= Action { implicit request =>
    Logger.info("insert called.")
    contactForm.bindFromRequest.fold(
      form => {
        BadRequest(views.html.contact_details(None, form))
      },
      contact => {
        Redirect(routes.ContactController.details(0)).flashing("success" -> Messages("success.insert", "PRODUCT_HARD_CODED"))
      })
  }

  def update(id: Long) = Action { implicit request =>
    Logger.info("updated called. id: " + id)
    contactForm.bindFromRequest.fold(
      form => {
        Ok(views.html.contact_details(Some(id), form)).flashing("error" -> "Fix the errors!")
      },
      contact => {
        //contact.update(id, product)
        Redirect(routes.ContactController.details(0)).flashing("success" -> Messages("success.update", contact.firstname))
      })
  }

  def delete(id: Long)= Action {
    Redirect(routes.ContactController.details(0)).flashing("success" -> Messages("success.delete", id))
//    Contacts.find(id).map { product =>
//      Products.delete(id)
//      Redirect(routes.Application.index).flashing("success" -> Messages("success.delete", product.name))
//    }.getOrElse(NotFound)
  }

}