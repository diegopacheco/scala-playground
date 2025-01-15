package com.github.diegopacheco.scala.ids.data

type Event = String

trait Bus {
  def publish(event:Event): Boolean
  def subscribe(event:Event, callback: Event => Unit): Boolean
}

object InMemoryBus extends Bus {
  private var subscribers = Map[Event, List[Event => Unit]]()

  def publish(event:Event): Boolean = {
    subscribers.get(event) match {
      case Some(callbacks) => {
        callbacks.foreach(callback => callback(event))
        true
      }
      case None => false
    }
  }

  def subscribe(event:Event, callback: Event => Unit): Boolean = {
    subscribers.get(event) match {
      case Some(callbacks) => {
        subscribers = subscribers + (event -> (callback :: callbacks))
        true
      }
      case None => {
        subscribers = subscribers + (event -> List(callback))
        true
      }
    }
  }
}
