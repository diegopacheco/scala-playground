package com.github.diegopacheco.scalaplayground.cmd

import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.http.scaladsl._
import org.json4s.native.Serialization
import org.json4s.{DefaultFormats, Formats, FullTypeHints}
import org.apache.pekko.http.scaladsl.server.Directives._
import org.apache.pekko.http.scaladsl.server.Route
import scala.concurrent.{ExecutionContext, ExecutionContextExecutor, Future}
import scala.io.StdIn
import scala.util.{Failure, Success}

trait Json4sSupport {
  private val customTypeHints = FullTypeHints(List(
    classOf[ComputerModel],
  ))
  implicit val formats: Formats = DefaultFormats.withHints(customTypeHints)
  implicit val serialization: Serialization.type = Serialization
}

case class ComputerModel(id: Int, name: String, manufacturerId: Int)

class ComputerRepository{
  def getAllComputers: Future[Seq[ComputerModel]] = {
    Future.successful(Seq(ComputerModel(1, "MacBook Pro", 1)))
  }
}

class ComputerRoutes(computerRepository: ComputerRepository)(implicit ec: ExecutionContext) extends Json4sSupport {
  val route: Route = path("computers") {
    get {
      onComplete(computerRepository.getAllComputers) {
        case Success(computers) => complete(serialization.write(computers)(formats))
        case Failure(ex) => complete((500, s"An error occurred: ${ex.getMessage}"))
      }
    }
  } ~ path("") {
    get {
      complete(serialization.write(Seq("Goto http://localhost:8080/computers"))(formats))
    }
  }
}

object PekkoHttpServer {

  def run(): Unit = {
    implicit val system: ActorSystem = ActorSystem("my-system")
    implicit val executionContext: ExecutionContextExecutor = system.dispatcher

    val computerRepository = new ComputerRepository
    val computerRoutes = new ComputerRoutes(computerRepository)

    println("Server online at http://localhost:8080/")
    val bindingFuture = Http().newServerAt("localhost", 8080).bindFlow(computerRoutes.route)

    println("Press RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }

}