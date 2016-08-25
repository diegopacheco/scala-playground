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

@Singleton
class HomeController @Inject() (system: ActorSystem, @Named("configured-actor") configuredActor: ActorRef)  extends Controller {
  
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

}
