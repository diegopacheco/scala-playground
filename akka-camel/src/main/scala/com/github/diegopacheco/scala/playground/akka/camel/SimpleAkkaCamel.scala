package com.github.diegopacheco.scala.playground.akka.camel

object SimpleAkkaCamel extends App {
  
    import akka.camel.{ CamelMessage, Consumer }
     
    class MyEndpoint extends Consumer {
      def endpointUri = "mina2:tcp://localhost:6200?textline=true"
     
      def receive = {
        case msg: CamelMessage => { println(msg) }
        case _                 => { println("Something else") }
      }
    }
     
    // start and expose actor via tcp
    import akka.actor.{ ActorSystem, Props }
     
    val system = ActorSystem("some-system")
    val mina = system.actorOf(Props[MyEndpoint])

}