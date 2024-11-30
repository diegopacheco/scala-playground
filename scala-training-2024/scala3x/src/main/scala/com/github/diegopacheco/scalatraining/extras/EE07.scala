package com.github.diegopacheco.scalatraining.extras

import com.github.diegopacheco.scalatraining.E05.{Json4sSupport, TollRepository}
import com.github.diegopacheco.scalatraining.extras.EE07.Direction.*
import com.github.diegopacheco.scalatraining.extras.EE07.{Mapping, Position, Rover, RoverRoutes}
import org.apache.pekko.http.scaladsl.server.Directives.*
import org.apache.pekko.http.scaladsl.server.Route
import scala.util.{Failure, Success}
import org.json4s.{DefaultFormats, Formats, FullTypeHints, ShortTypeHints}
import scala.concurrent.{ExecutionContext, Future}
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.http.scaladsl.Http
import org.slf4j.LoggerFactory
import scala.concurrent.ExecutionContextExecutor
import scala.io.StdIn
import com.github.diegopacheco.scalatraining.db.slick.Tables.*

import scala.concurrent.ExecutionContext

/*
 * Implement a MARS ROVER in Scala: https://codingdojo.org/kata/mars-rover/
 */
object EE07 {

  object Direction extends Enumeration {
    type Direction = Value
    val N, S, E, W = Value
  }

  case class Position(x: Int, y: Int, direction: Direction)

  class Mapping(val width: Int, val height: Int, val obstacles: Set[(Int, Int)]) {
    def isObstacle(x: Int, y: Int): Boolean = obstacles.contains((x, y))
  }

  class Rover(var position: Position, val map: Mapping) {

    def turnLeft(): Unit = {
      position = position.copy(direction = position.direction match {
        case N => W
        case W => S
        case S => E
        case E => N
      })
    }

    def turnRight(): Unit = {
      position = position.copy(direction = position.direction match {
        case N => E
        case E => S
        case S => W
        case W => N
      })
    }

    def moveForward(): Unit = {
      val (newX, newY) = position.direction match {
        case N => (position.x, position.y + 1)
        case S => (position.x, position.y - 1)
        case E => (position.x + 1, position.y)
        case W => (position.x - 1, position.y)
      }
      if (!map.isObstacle(newX, newY) && newX >= 0 && newX < map.width && newY >= 0 && newY < map.height) {
        position = position.copy(x = newX, y = newY)
      }
    }

    def executeCommands(commands: String): Unit = {
      commands.foreach {
        case '⬆' => moveForward()
        case '➡' => turnRight()
        case '⬅' => turnLeft()
        case _ => // Ignore invalid commands
      }
    }

    def stats: Future[String] =
      Future.successful(s"Final Position: (${this.position.x}, ${this.position.y}) facing ${this.position.direction}")
  }

  class RoverRoutes(rover: Rover)(implicit ec: ExecutionContext) extends Json4sSupport {
    val route: Route = path("stats") {
      get {
        onComplete(rover.stats) {
          case Success(result) => complete(serialization.write(result)(formats))
          case Failure(ex) => complete((500, s"An error occurred: ${ex.getMessage}"))
        }
      }
    }
  }
}

object EE07App extends App {

  val initialPosition = Position(0, 0, N)
  val obstacles = Set((1, 1), (2, 2))
  val map = new Mapping(5, 5, obstacles)
  val rover = new Rover(initialPosition, map)

  val commands = "⬆➡⬆⬅⬆"
  rover.executeCommands(commands)

  val logger = LoggerFactory.getLogger(getClass)
  implicit val system: ActorSystem = ActorSystem()
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  val routes = new RoverRoutes(rover)
  val bindingFuture = Http().newServerAt("localhost", 8080).bind(routes.route)

  logger.info(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine()
  bindingFuture
    .flatMap(_.unbind())
    .onComplete(_ => system.terminate())

}