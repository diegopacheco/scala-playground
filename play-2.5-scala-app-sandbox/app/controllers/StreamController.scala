package controllers

import javax.inject._
import play.api.libs.iteratee.Enumerator
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.Result
import play.api.mvc.Controller
import play.api.mvc.ResponseHeader
import play.api.http.HttpEntity
import play.api._
import play.api.mvc._
import play.api.libs.streams.Streams
import akka.stream.scaladsl.Source
import akka.util.ByteString

@Singleton
class StreamController @Inject() extends Controller {

  def index = Action {
    
    val file = new java.io.File("/etc/hosts")
    val source = Source.fromPublisher(Streams.enumeratorToPublisher(Enumerator.fromFile(file))).map(ByteString.apply)
    
    Result(
      header = ResponseHeader(200, Map(CONTENT_LENGTH -> file.length.toString)),
      body = HttpEntity.Streamed(source, None, None)
    ).as(HTML)
    
  }
  
  def serving = Action {
     Ok.sendFile(new java.io.File("/home/diego/Dropbox/logos/photo.png"))
  }
  
  def chuncked = Action {
    Ok.chunked(
       Enumerator("kiki", "foo", "bar").andThen(Enumerator.eof)
    )
  }
    
}