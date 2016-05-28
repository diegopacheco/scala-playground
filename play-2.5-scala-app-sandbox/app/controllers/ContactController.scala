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
import services.ContactService
import services.IContactService

@Singleton
class ContactController @Inject() (val messagesApi:MessagesApi, val service:IContactService) extends Controller with I18nSupport{

  val contactForm: Form[Contact] = Form(
    mapping(
      "firstname" -> nonEmptyText,
      "lastname"  -> nonEmptyText,
      "company"   -> optional(text),
      "id"        -> optional(longNumber)  
    )(models.Contact.apply)(models.Contact.unapply))

  def index = Action { implicit request =>
    val contacts = service.getAll().getOrElse(Seq(Contact("","",None)))
    Logger.info("index called. Contacts: " + contacts)
    Ok(views.html.contact_index(contacts))
  }

  def blank = Action { implicit request =>
    Logger.info("blank called. ")
    Ok(views.html.contact_details(None, contactForm))
  }

  def details(id: Long) = Action { implicit request =>
    Logger.info("details called. id: " + id)
    val contact = service.get(id).get
    Ok(views.html.contact_details(Some(id), contactForm.fill(contact)))
  }

  def insert()= Action { implicit request =>
    Logger.info("insert called.")
    contactForm.bindFromRequest.fold(
      form => {
        BadRequest(views.html.contact_details(None, form))
      },
      contact => {
        val id = service.insert(contact)
        Redirect(routes.ContactController.index).flashing("success" -> Messages("success.insert", id))
      })
  }

  def update(id: Long) = Action { implicit request =>
    Logger.info("updated called. id: " + id)
    contactForm.bindFromRequest.fold(
      form => {
        Ok(views.html.contact_details(Some(id), form)).flashing("error" -> "Fix the errors!")
      },
      contact => {
        service.update(id,contact)
        Redirect(routes.ContactController.index).flashing("success" -> Messages("success.update", contact.firstname))
      })
  }

  def delete(id: Long)= Action {
      service.get(id).map { contact =>
        service.delete(id)
        Redirect(routes.ContactController.index).flashing("success" -> Messages("success.delete", contact.firstname))
      }.getOrElse(NotFound)
  }

}