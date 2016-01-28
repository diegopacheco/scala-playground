package com.github.diegopacheco.scala.playground.reactive.streams

import akka.routing.RoundRobinRoutingLogic
import akka.stream.actor.ActorSubscriber
import akka.routing.ActorRefRoutee
import akka.stream.actor.ActorSubscriberMessage
import akka.routing.Router
import akka.stream.actor.MaxInFlightRequestStrategy
import akka.actor.Props
import akka.stream.actor.ActorSubscriberMessage.OnNext
import akka.actor.Actor
import akka.actor.ActorRef
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Sink
import akka.actor.ActorSystem
import akka.stream.scaladsl.Source

object WorkerPool {
  case class Msg(id: Int, replyTo: ActorRef)
  case class Work(id: Int)
  case class Reply(id: Int)
  case class Done(id: Int)

  def props: Props = Props(new WorkerPool)
}

class WorkerPool extends ActorSubscriber {
  import WorkerPool._
  import ActorSubscriberMessage._

  val MaxQueueSize = 10
  var queue = Map.empty[Int, ActorRef]

  val router = {
    val routees = Vector.fill(3) {
      ActorRefRoutee(context.actorOf(Props[Worker]))
    }
    Router(RoundRobinRoutingLogic(), routees)
  }

  override val requestStrategy = new MaxInFlightRequestStrategy(max = MaxQueueSize) {
    override def inFlightInternally: Int = queue.size
  }

  def receive = {
    case OnNext(Msg(id, replyTo)) =>
      queue += (id -> replyTo)
      assert(queue.size <= MaxQueueSize, s"queued too many: ${queue.size}")
      router.route(Work(id), self)
    case Reply(id) =>
      queue(id) ! Done(id)
      queue -= id
  }
}

class Worker extends Actor {
  import WorkerPool._
  def receive = {
    case Work(id) =>
      sender() ! Reply(id)
  }
}

object ReactiveSubs extends App {
  
  implicit val system = ActorSystem("reactive-actors")
  implicit val materializer = ActorMaterializer()
  
  class ReplyTo extends Actor {
    def receive = {
      case a:Any => println(a)
    }
  }
  
  val N = 117
  Source(1 to N).map(WorkerPool.Msg(_, system.actorOf(Props[ReplyTo])))
    .runWith(Sink.actorSubscriber(WorkerPool.props))

}