package actors

import akka.actor.Props
import akka.actor.Terminated
import akka.actor.ActorLogging
import akka.event.LoggingReceive
import akka.actor.Actor
import akka.actor.ActorRef
import play.libs.Akka
import akka.actor.ActorSystem

class RoomActor extends Actor with ActorLogging {

  var users = Set[ActorRef]()

  def receive = LoggingReceive {
    case msg: Message =>
      println("users in the room: " + users)
      println("users counter: " + users.size)
      users foreach { _ ! msg }
    case Enter =>
      users += sender
      println("user: " + sender + " enters the room")
      context watch sender
    case Terminated(user) =>
      users -= user
  }
}

object RoomActor {
  var room:ActorRef = null
  
  def apply(system:ActorSystem) = {
    this.synchronized {
      if (room==null) room = system.actorOf(Props[RoomActor])
      room
    }
  }
  
}

case class Message(name:String,text: String)
object Enter