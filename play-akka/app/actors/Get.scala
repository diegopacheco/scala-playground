package actors

import scala.concurrent.Future

object Get {
  
  import play.api.libs.concurrent.Execution.Implicits.defaultContext  
  import scala.concurrent.duration._
  import akka.pattern.ask
  import akka.actor.ActorRef
  import akka.util.Timeout

  def async(msg:Any,actor:ActorRef):Future[String] = {
    implicit val timeout = Timeout(5 seconds)
    (actor ? msg).mapTo[String].map { result => result.toString } 
  }
  
}