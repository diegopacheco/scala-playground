package com.github.diegopacheco.scala.ids.data

import org.json4s.native.Json

type EventType = String
type Event = Any

/**
 * Ideally this a proper message bus like Kafka, RabbitMQ, SQS/SNS, nsq, etc.
 * Since is a POC - I will do in memory like a simple java Observer pattern.
 */
trait Bus {
  def publish(eventType:EventType,event:Any): Boolean
  def subscribe(eventType:EventType, callback: Event => Unit): Boolean
}

object InMemoryBus extends Bus with JsonSupport {
  private var subscribers = Map[EventType, List[Event => Unit]]()

  def publish(eventType:EventType,event:Any): Boolean = {
    val json = serialization.write(event)
    subscribers.get(eventType) match {
      case Some(callbacks) => {
        callbacks.foreach(callback => callback(event))
        true
      }
      case None => false
    }
  }

  def subscribe(eventType:EventType, callback: Event => Unit): Boolean = {
    subscribers.get(eventType) match {
      case Some(callbacks) => {
        subscribers = subscribers + (eventType -> (callback :: callbacks))
        true
      }
      case None => {
        subscribers = subscribers + (eventType -> List(callback))
        true
      }
    }
  }
}

object BusService extends Bus {
  def publish(eventType:EventType,event:Any): Boolean = InMemoryBus.publish(eventType,event)
  def subscribe(eventType:EventType, callback: Event => Unit): Boolean = InMemoryBus.subscribe(eventType,callback)
}
