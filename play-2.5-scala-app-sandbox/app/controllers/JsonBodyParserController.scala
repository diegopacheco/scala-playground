package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.libs.json.JsValue

@Singleton
class JsonBodyParserController @Inject() extends Controller {

  def index = Action { request =>
    val body:AnyContent = request.body
    val jsonBody: Option[JsValue] = body.asJson

    // Expecting json body
    jsonBody.map { json =>
      Ok("Got: " + (json \ "name").as[String])
    }.getOrElse {
      BadRequest("Expecting application/json request body")
    }
  }
  
  def explicit = Action(parse.json) { request =>
    Ok("Got: " + (request.body \ "name").as[String])
  }
  
  def tolerant = Action(parse.tolerantJson) { request =>
    Ok("Got: " + (request.body \ "name").as[String])
  }
  
  // Accept only 10KB of data.
  def limit = Action(parse.text(maxLength = 1024 * 10)) { request =>
    Ok("Got: " + request.body)
  }
  

}