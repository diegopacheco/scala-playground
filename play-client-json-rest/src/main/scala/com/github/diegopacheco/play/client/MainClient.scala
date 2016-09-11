package com.github.diegopacheco.play.client

import scala.concurrent.Future
import scala.concurrent.duration._
import play.api.libs.ws._
import play.api.libs.ws.ning._
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import scala.concurrent.ExecutionContext
import akka.stream.ActorMaterializer
import java.io.File
import scala.math.BigInt
import scala.math.BigDecimal

case class Place(name:String,location:Location)
case class Location(lat:Double,long:Double)

object MainClient extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val context = play.api.libs.concurrent.Execution.Implicits.defaultContext
  
  lazy val ws = {
      import com.typesafe.config.ConfigFactory
      import play.api._
      import play.api.libs.ws._
      import play.api.libs.ws.ahc.{AhcWSClient, AhcWSClientConfig}
      import play.api.libs.ws.ahc.AhcConfigBuilder
      import org.asynchttpclient.AsyncHttpClientConfig
    
      val configuration = Configuration.reference ++ Configuration(ConfigFactory.parseString(
        """
          |ws.followRedirects = true
        """.stripMargin))
    
      val parser = new WSConfigParser(configuration, play.api.Environment.simple(new File("/tmp/"), null))
      val config = new AhcWSClientConfig(wsClientConfig = parser.parse())
      val builder = new AhcConfigBuilder(config)
      val logging = new AsyncHttpClientConfig.AdditionalChannelInitializer() {
        override def initChannel(channel: io.netty.channel.Channel): Unit = {
          channel.pipeline.addFirst("log", new io.netty.handler.logging.LoggingHandler("debug"))
        }
      }
      val ahcBuilder = builder.configure()
      ahcBuilder.setHttpAdditionalChannelInitializer(logging)
      val ahcConfig = ahcBuilder.build()
      new AhcWSClient(ahcConfig)
  }
  
  
  val complexRequest: WSRequest = ws.url("http://localhost:9000/places").withHeaders("Accept" -> "application/json")
  val futureResponse:Future[WSResponse] = complexRequest.get()
  futureResponse.map { r => println(r.body) }
  
  
  //
  // Mapping...
  //
  
  import play.api.libs.json._
  implicit val locationReads = Json.reads[Location]
  implicit val placeReads = Json.reads[Place]
  implicit val locationWrites = Json.writes[Location]
  implicit val placeWrites = Json.writes[Place]
  
  // POST DATA
  
  val data = Json.toJson(new Place("Sunnyvale", new Location(0,1)))
  ws.url("http://localhost:9000/places").withHeaders("Accept" -> "application/json").post(data)
  Thread.sleep(3000)
  
  // GEt and map to pojo
  
  val futureResult:Future[Option[List[Place]]] = ws.url("http://localhost:9000/places").withHeaders("Accept" -> "application/json").get().map(
    response => Json.parse(response.body).validate[List[Place]].asOpt
  )
  
  Thread.sleep(3000)
  futureResult.map { r =>
      println("Places:")
      r.foreach { p =>
         p.foreach { x => println(x.name) }
      }
  }
  
  //
  // Backpressure
  //
  import rx.lang.scala.Observable
  import scala.util.Random.nextDouble
  import scala.concurrent.duration._
  import rx.lang.scala.subjects.PublishSubject
  import rx.lang.scala.schedulers.NewThreadScheduler
  
  var doubleSubject = PublishSubject.apply[Double]()
  Future {
       var i:BigDecimal = BigDecimal(1)
       while(i.doubleValue() <= 99999){
         Thread.sleep(100)
         doubleSubject.onNext(i.toDouble)
         i = BigDecimal(i.doubleValue() + 1) 
       }
  }
  
  Observable.create(doubleSubject.subscribe).
            subscribeOn(NewThreadScheduler()).
            //debounce(1 seconds).
            //throttleWithTimeout(2 seconds).
            //sample(1 seconds).
            subscribe(x => println("stream: " + x) )
  
  Thread.sleep(8000)

  //
  // end
  //
  ws.close
  system.terminate
  
}