package controllers

import javax.inject._
import play.api._
import play.api.mvc._

import akka.stream.Materializer
import akka.stream.scaladsl.Source
import play.api.http.ContentTypes
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.libs.Comet
import play.api.libs.iteratee.Enumerator
import play.api.libs.json._
import play.api.libs.streams.Streams

@Singleton
class CometController @Inject() (materializer:Materializer)  extends Controller {
  
  def index = Action {
     Ok(views.html.comet("Your new application is ready."))
  }
  
  def index2 = Action {
     Ok(views.html.comet_json("Your new application is ready."))
  }
  
  def cometString = Action {
    implicit val m = materializer
    def stringSource: Source[String, _] = Source(List("kiki", "foo", "bar"))
    Ok.chunked(stringSource via Comet.string("parent.cometMessage")).as(ContentTypes.HTML)
  }
  
  def cometJson = Action {
    implicit val m = materializer
    def jsonSource: Source[JsValue, _] = Source(List(JsString("jsonString")))
    Ok.chunked(jsonSource via Comet.json("parent.cometMessage")).as(ContentTypes.HTML)
  }

}