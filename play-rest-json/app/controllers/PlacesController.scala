package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import models.Place
import play.api.libs.json._

//
// List
//
// jcurl http://localhost:9000/places
//
// Insert
//
// jcurl --include --request POST --header "Content-type: application/json" --data '{"name":"Nuthanger Farm","location":{"lat" : 51.244031,"long" : -1.263224}}' http://localhost:9000/places
//

@Singleton
class PlacesController @Inject() extends Controller {

  def listPlaces = Action {
    val json = Json.toJson(Place.list)
    Ok(json)
  }

  def savePlace = Action(BodyParsers.parse.json) { request =>
    val placeResult = request.body.validate[Place]
    placeResult.fold(
      errors => {
         BadRequest(Json.obj("status" -> "KO", "message" -> JsError.toJson(errors)))
      },
      place => {
        Place.save(place)
        Ok(Json.obj("status" -> "OK", "message" -> ("Place '" + place.name + "' saved.")))
      })
  }

}
