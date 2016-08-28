package actors

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.event.LoggingReceive
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import akka.actor.ActorRef
import akka.actor.Props
import scala.xml.Utility
import akka.actor.ActorSystem

class UserActor(room:ActorRef, out:ActorRef) extends Actor with ActorLogging {
  
  override def preStart() = {
    room ! Enter
  }

  def receive = LoggingReceive {
    case Message(name, text) if sender == room =>
      val result:String = name + ":" + text
      out ! result
    case (text:String) =>
      room ! Message(text.split(":")(0), text.split(":")(1))
    case other =>
      log.error("issue - not expected: " + other)
  }
}

object UserActor {
  def props(system:ActorSystem)(out:ActorRef) = Props(new UserActor(RoomActor(system), out))
}