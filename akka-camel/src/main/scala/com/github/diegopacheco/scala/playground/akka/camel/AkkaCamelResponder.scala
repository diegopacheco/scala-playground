package com.github.diegopacheco.scala.playground.akka.camel

object AkkaCamelResponder extends App{
    
    import akka.actor.{ Props, ActorSystem, Actor, ActorRef }
    import akka.camel.{ CamelMessage, CamelExtension }
    import org.apache.camel.builder.RouteBuilder
    import akka.camel._
    
    class Responder extends Actor {
      def receive = {
        case msg: CamelMessage =>
          sender() ! (msg.mapBody {
            body: Object =>
              println(body)
              "received"
          })
      }
    }
     
    class CustomRouteBuilder(system: ActorSystem, responder: ActorRef)
      extends RouteBuilder {
      def configure {
        from("jetty:http://localhost:8877/camel/custom").to(responder)
      }
    }
    val system = ActorSystem("some-system")
    val camel = CamelExtension(system)
    val responder = system.actorOf(Props[Responder], name = "TestResponder")
    camel.context.addRoutes(new CustomRouteBuilder(system, responder))


  
}