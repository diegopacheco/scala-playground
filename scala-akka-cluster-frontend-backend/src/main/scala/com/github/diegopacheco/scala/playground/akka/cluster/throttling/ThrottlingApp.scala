package com.github.diegopacheco.scala.playground.akka.cluster.throttling

import akka.actor.Actor
import akka.contrib.throttle.TimerBasedThrottler
import akka.actor.Props
import akka.actor.ActorSystem
import scala.concurrent.duration._
import java.util.concurrent.TimeUnit._
import akka.contrib.throttle.Throttler.Rate
import akka.contrib.throttle.Throttler.SetTarget

class PrintActor extends Actor {
  def receive = {
    case x => println(x)
  }
}

object ThrottlingApp extends App {

  val system = ActorSystem("MySystem")
  val printer = system.actorOf(Props[PrintActor])
  val rate =  Rate(3,1 second)
  val throttler = system.actorOf(Props(classOf[TimerBasedThrottler],rate))

  throttler ! SetTarget(Some(printer))
  
  // These three messages will be sent to the target immediately
  throttler ! "1"
  throttler ! "2"
  throttler ! "3"
  // These two will wait until a second has passed
  throttler ! "4"
  throttler ! "5"

}