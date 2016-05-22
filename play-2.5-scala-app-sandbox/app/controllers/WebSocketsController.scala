package controllers

import akka.actor.Actor
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.stream.Materializer
import javax.inject.Inject
import javax.inject.Singleton
import play.api.libs.streams.ActorFlow
import play.api.mvc.Controller
import play.api.mvc.WebSocket
import play.api._
import play.api.mvc._

object MyWebSocketActor {
  def props(out: ActorRef) = Props(new MyWebSocketActor(out))
}

class MyWebSocketActor(out: ActorRef) extends Actor {
  def receive = {
    case msg: String =>
      out ! ("I received your message: " + msg)
  }
}

@Singleton
class WebSocketsController @Inject() (implicit system:ActorSystem, materializer:Materializer) extends Controller {
   
   def index = Action {
      Ok(views.html.websockets("Websockets feature"))
   }
  
   def socket = WebSocket.accept[String, String] { request =>
      ActorFlow.actorRef(out => MyWebSocketActor.props(out))
   }
  
}