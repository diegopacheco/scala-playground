package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import actors.HelloActor
import akka.actor.ActorSystem
import actors.Get
import actors.HelloActor.SayHello
import akka.actor.ActorRef
import actors.ConfiguredActor.GetConfig
import akka.stream.Materializer
import play.api.libs.streams._
import actors.MyWebSocketActor
import play.api.libs.json._
import actors.UserActor
import scala.concurrent.Future

@Singleton
class HomeController @Inject() (implicit val system: ActorSystem,
                                materializer: Materializer, 
                                @Named("configured-actor") configuredActor: ActorRef
) extends Controller {
  
  import play.api.libs.concurrent.Execution.Implicits.defaultContext
  val helloActor = system.actorOf(HelloActor.props, "hello-actor")
  
  def index = Action.async {
    Get.async(SayHello("Hello play ator"), helloActor).map { message =>
      Ok("Actor: " + helloActor + " - " + message)
    }
  }
  
  def config = Action.async {
    Get.async(GetConfig, configuredActor).map { message =>
      Ok("ConfiguredActor: " + configuredActor + " - " + message)
    }
  }
  
  def index_socket = Action { request =>
    Ok(views.html.index("ws and akka"))
  }
  
  def socket = WebSocket.accept[String, String] { request =>
    ActorFlow.actorRef(out => MyWebSocketActor.props(out))
  }
  
  def ws = WebSocket.accept[String, String] { request =>
    ActorFlow.actorRef(out => UserActor.props(system)(out))
  }

}
