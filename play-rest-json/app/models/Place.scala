package models

import play.api.libs.json._
import play.api.libs.json.Reads._
import play.api.libs.functional.syntax._

case class Location(lat: Double, long: Double)
case class Place(name: String, location: Location)

object Place {

  //  implicit val locationWrites = new Writes[Location] {
  //    def writes(location: Location) = Json.obj(
  //      "lat" -> location.lat,
  //      "long" -> location.long)
  //  }
  //
  //  implicit val placeWrites = new Writes[Place] {
  //    def writes(place: Place) = Json.obj(
  //      "name" -> place.name,
  //      "location" -> place.location)
  //  }

  implicit val locationWrites: Writes[Location] = (
    (JsPath \ "lat").write[Double] and
    (JsPath \ "long").write[Double])(unlift(Location.unapply))

  implicit val placeWrites: Writes[Place] = (
    (JsPath \ "name").write[String] and
    (JsPath \ "location").write[Location])(unlift(Place.unapply))

  implicit val locationReads: Reads[Location] = (
    (JsPath \ "lat").read[Double] and
    (JsPath \ "long").read[Double])(Location.apply _)

  implicit val placeReads: Reads[Place] = (
    (JsPath \ "name").read[String] (minLength[String](2)) and
    (JsPath \ "location").read[Location])(Place.apply _)

  var list: List[Place] = {
    List(
      Place(
        "Sandleford",
        Location(51.377797, -1.318965)),
      Place(
        "Watership Down",
        Location(51.235685, -1.309197)))
  }

  def save(place: Place) = {
    list = list ::: List(place)
  }
}