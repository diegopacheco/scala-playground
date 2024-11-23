package com.github.diegopacheco.slick.postgres.api

import org.apache.pekko.http.scaladsl.server.Directives.*
import org.apache.pekko.http.scaladsl.server.Route
import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

trait ApiRoute

class ComputerRoutes(computerRepository: ComputerRepository)(implicit ec: ExecutionContext) extends ApiRoute with Json4sSupport {
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