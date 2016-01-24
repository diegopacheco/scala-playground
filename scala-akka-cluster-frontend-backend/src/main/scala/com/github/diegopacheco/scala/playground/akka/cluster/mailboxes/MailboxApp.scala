package com.github.diegopacheco.scala.playground.akka.cluster.mailboxes

import com.typesafe.config.ConfigFactory
import akka.actor.Actor
import akka.actor.ActorSystem
import akka.actor.PoisonPill
import akka.actor.Props
import akka.actor.actorRef2Scala
import akka.event.Logging
import akka.event.LoggingAdapter
import akka.dispatch.UnboundedStablePriorityMailbox
import com.typesafe.config.Config
import akka.dispatch.PriorityGenerator

class MyPrioMailbox(settings: ActorSystem.Settings, config: Config)
  extends UnboundedStablePriorityMailbox(
    PriorityGenerator {
      case 'highpriority => 0
      case 'lowpriority => 2
      case PoisonPill => 3
      case otherwise => 1
    })

class Logger extends Actor {
  val log: LoggingAdapter = Logging(context.system, this)

  self ! 'lowpriority
  self ! 'lowpriority
  self ! 'highpriority
  self ! 'pigdog
  self ! 'pigdog2
  self ! 'pigdog3
  self ! 'highpriority
  self ! PoisonPill

  def receive = {
    case x => log.info(x.toString)
  }
}

object MailboxApp extends App {

  def bootup(port: String, dispatcher: String): ActorSystem = {
    val config = ConfigFactory.parseString(s"akka.remote.netty.tcp.port=$port")
      .withFallback(ConfigFactory.parseString(s"""
                        prio-dispatcher {
                            mailbox-type = "com.github.diegopacheco.scala.playground.akka.cluster.mailboxes.MyPrioMailbox"
                        }
                    """)).withFallback(ConfigFactory.load())
    val system = ActorSystem("ClusterSystem", config)
    system
  }

  val system = bootup("2551", "compute")
  system.actorOf(Props[Logger].withDispatcher("prio-dispatcher"))

}