package com.github.diegopacheco.scala.ids.data

import org.scalatest.funsuite.AnyFunSuite

class BusTest extends AnyFunSuite {

  test("publish should return false if no subscribers for the event") {
    val bus = InMemoryBus
    assert(!bus.publish("event1","data"))
  }

  test("subscribe should add a subscriber for the event") {
    val bus = InMemoryBus
    val callback: Event => Unit = _ => ()
    assert(bus.subscribe("event1", callback))
  }

  test("publish should return true if there are subscribers for the event") {
    val bus = InMemoryBus
    val callback: Event => Unit = _ => ()
    bus.subscribe("event1", callback)
    assert(bus.publish("event1","data"))
  }

  test("publish should call the subscriber's callback") {
    val bus = InMemoryBus
    var called = false
    val callback: Event => Unit = _ => called = true
    bus.subscribe("event1", callback)
    bus.publish("event1","data")
    assert(called)
  }

  test("subscribe should allow multiple subscribers for the same event") {
    val bus = InMemoryBus
    val callback1: Event => Unit = _ => ()
    val callback2: Event => Unit = _ => ()
    assert(bus.subscribe("event1", callback1))
    assert(bus.subscribe("event1", callback2))
  }

  test("publish should call all subscribers' callbacks for the event") {
    val bus = InMemoryBus
    var callCount = 0
    val callback: Event => Unit = _ => callCount += 1
    bus.subscribe("event1", callback)
    bus.subscribe("event1", callback)
    bus.publish("event1","data")
    assert(callCount == 2)
  }
}
