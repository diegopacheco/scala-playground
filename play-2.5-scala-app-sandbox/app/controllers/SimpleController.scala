package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import akka.util.ByteString

@Singleton
class SimpleController  @Inject() extends Controller {
    
    def hello(name: String) = Action {
      Ok("Hello " + name)
    }  
    
    import play.api.http.HttpEntity
    def hey = Action {
      Result(
        header = ResponseHeader(200, Map.empty),
        body = HttpEntity.Strict(ByteString("Hello world!"), Some("text/plain"))
      )
    }
    
    def not =  Action { NotFound }
    
    def oops =  Action { InternalServerError("Oops") } 
    
    def any =  Action { Status(488)("Strange response type") }
    
    def redirect = Action {
      Redirect("/hello/You_have_been_redirected")
    }
    
    def todo = TODO
    
    def reverse = Action {
       Redirect(routes.SimpleController.hello("Bob you have been rverse routeed to: /hello/:name "))
    }
    
    /**
     *  http://localhost:9000/api/all?version=5
     * 
     * http://localhost:9000/api/all
     * 
     */
    def all(version: Option[String]) = Action {
         Ok("Got it -  version: " + version.getOrElse("1.0") )
    }
    
    def xmlResult = Action { Ok(<message>Hello World!</message>) }
    
    def html = Action { 
       Ok(<h1>Hello World!</h1>).as(HTML)
    }
    
    def headers = Action {
      val result = Ok("Hello World!").withHeaders(
        CACHE_CONTROL -> "max-age=3600",
        ETAG -> "xx"
      )
      result
    }
    
    def withcockies = Action {
       val result = Ok("Hello world").withCookies(
       Cookie("theme", "blue"))
       result
    }
    
    def withoutcockies = Action {
       val result = Ok("Hello world").discardingCookies(DiscardingCookie("theme"))
       result
    }
    
}