package com.github.diegopacheco.scala.playground.akka.camel

object SimpleAkkaCamelConsumer extends App {
   
   import akka.camel.{ CamelMessage, Consumer }
     
   class Consumer1 extends Consumer {
      def endpointUri = "file://D:/tmp/camel"
     
      def receive = {
        case msg: CamelMessage => println("received %s" format msg.bodyAs[String])
      }
   }

    // start and expose actor via tcp
    import akka.actor.{ ActorSystem, Props }
     
    val system = ActorSystem("some-system")
    val mina = system.actorOf(Props[Consumer1])
  
}