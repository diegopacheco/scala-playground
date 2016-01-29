package com.github.diegopacheco.scala.playground.akka.camel.complex

import org.codehaus.jackson.annotate.JsonIgnoreProperties
import org.apache.camel.builder.RouteBuilder
import akka.camel.CamelExtension
import org.apache.camel.model.dataformat.JsonLibrary
import org.codehaus.jackson.annotate.JsonProperty
import org.apache.camel.Exchange
import java.util
import akka.actor.ActorSystem
import org.slf4j.LoggerFactory
import akka.actor.Props
import akka.stream.scaladsl._
import akka.camel.CamelMessage
import scala.annotation.tailrec
import akka.stream.actor.ActorPublisherMessage.Request
import akka.camel.Consumer
import akka.actor.PoisonPill
import akka.event.Logging
import akka.stream.actor.ActorPublisherMessage.Cancel
import scala.collection.JavaConversions._
import akka.actor.Actor
import akka.stream.actor.ActorPublisher
import akka.stream.scaladsl.Source
import akka.camel.Producer
import akka.stream.ActorMaterializer
import akka.stream.OverflowStrategy
import scala.concurrent.Await
import akka.actor.ActorRef

@JsonIgnoreProperties(ignoreUnknown = true)
case class SearchResult(@JsonProperty("items") items: util.HashSet[SearchItem])

@JsonIgnoreProperties(ignoreUnknown = true)
case class SearchItem(@JsonProperty("link") link: String)

class ConsumingPublisher(private val url: String) extends ActorPublisher[SearchItem] with Consumer {

  private val logger = Logging.getLogger(context.system, this)
  private var items = List.empty[SearchItem]

  override def endpointUri: String = url

  override def receive: Receive = {
    case CamelMessage(body: SearchResult, headers) =>
      logger.info("New chunk of items arrived.")
      items = items ++ body.items
      logger.info("Current pending item list {}.", items)

      sender() ! CamelMessage("Queued!", headers)

      if (totalDemand > 0) {
        items = passOnNext(items, totalDemand)
        logger.info("Demand handled. Items still pending {}.", items)
      }
    case Request(demand: Long) =>
      logger.info("Demand request of size {}. Items pending {}.", demand, items)
      items = passOnNext(items, demand)
      logger.info("Demand handled. Items still pending {}.", items)
    case Cancel =>
      logger.error("Subscriber cancelled processing remaining {} elements will not be processed.", items.size)
      self ! PoisonPill
    case _ =>
      logger.error("Unknown message. Don't know what to do - PANIC PANIC!")
  }

  @tailrec private def passOnNext(items: List[SearchItem], remainingDemand: Long): List[SearchItem] = {
    if (remainingDemand > 0L && items.nonEmpty) {
      onNext(items.head)
      passOnNext(items.tail, remainingDemand - 1)
    } else {
      items
    }
  }
}

class ProducingWorker2(endpoint: String) extends Actor with Producer {
  override def endpointUri:String = endpoint
}

object ComplexCamelApp extends App {

  implicit val system = ActorSystem.create("CamelSystem")
  implicit val log = LoggerFactory.getLogger(classOf[App])
  implicit val materializer = ActorMaterializer.create(system)

  CamelExtension(system).context.addRoutes(new RouteBuilder() {
    override def configure() {
      from("direct-vm://googler")
        .enrich("direct-vm://google-call")
        .choice()
        .when(header(Exchange.HTTP_RESPONSE_CODE).isEqualTo(200))
        .unmarshal().json(JsonLibrary.Jackson, classOf[SearchResult])
        .to("direct-vm://googled")
        .otherwise()
        .setBody(constant("Fail - boom boom!"))
        .to("direct-vm://googled")

      from("direct-vm://google-call")
        .setHeader(
          Exchange.HTTP_QUERY,
          simple("key=" + System.getProperty("google.api.key", "YOU_DID_NOT_PASS_-Dgoogle.api.key_to_the_jvm") + "&cx=001733240814555448082:yqsjy6oesoq&q=${body}"))
        .to("https4://www.googleapis.com/customsearch/v1?throwExceptionOnFailure=false")
    }
  })
  
  val actorPublisherSource = Source.actorPublisher[SearchItem](Props(classOf[ConsumingPublisher], "direct-vm://googled"))
  
  val ref = Flow[SearchItem]
            .to(Sink.foreach(log.info("Sank item {}.", _)))
            .runWith(actorPublisherSource)
            
  val googler = system.actorOf(Props(classOf[ProducingWorker2], "direct-vm://googler"))
  
  googler ! "Akka"
  ref ! "Scala"

}