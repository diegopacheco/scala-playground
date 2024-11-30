package com.github.diegopacheco.scalatraining

import java.text.SimpleDateFormat


/*
 * Build a REST service, a car tool service where the cars should be registered in the database using slick
 * and the toll should charge different values per type of car ie: bikes 7 usd, cars 13 usd, trucks 20 usd.
 * Everytime a vehicle passes the toll that should be recorded on the database. The toll should have an operation
 * to list all cars that passed given a day.
 */
object E06:

  import com.typesafe.config.ConfigFactory
  import slick.jdbc.JdbcProfile
  import org.apache.pekko.http.scaladsl.server.Directives.*
  import org.apache.pekko.http.scaladsl.server.Route
  import scala.util.{Failure, Success}
  import org.json4s.native.Serialization
  import org.json4s.{DefaultFormats, Formats, FullTypeHints, ShortTypeHints}
  import slick.jdbc.PostgresProfile.api.*
  import scala.concurrent.{ExecutionContext, Future}
  import org.apache.pekko.actor.ActorSystem
  import org.apache.pekko.http.scaladsl.Http
  import org.slf4j.LoggerFactory
  import scala.concurrent.ExecutionContextExecutor
  import scala.io.StdIn
  import com.github.diegopacheco.scalatraining.db.slick.Tables.*

  case class TollModel(id: Int, date: String, carType: String, licensePlate: String, tollAmount: Int)

  trait Json4sSupport {
    private val customTypeHints = FullTypeHints(List(
      classOf[TollModel],
    ))
    implicit val formats: Formats = DefaultFormats.withHints(customTypeHints)
    implicit val serialization: Serialization.type = Serialization
  }

  object DatabaseConfig {
    private val config = ConfigFactory.load()

    def getProfile(profileName: String): JdbcProfile = {
      profileName match {
        case "postgres" => slick.jdbc.PostgresProfile
        case _ => throw new IllegalArgumentException(s"Unknown profile: $profileName")
      }
    }

    def getDbConfig(profileName: String) = {
      val profile = getProfile(profileName)
      val dbConfig = config.getConfig(s"slick.profiles.$profileName.db")
      (profile, dbConfig)
    }

  }

  class TollRepository(db: Database)(implicit ec: ExecutionContext) {
    def getAllTolls: Future[Seq[TollModel]] = {
      val query = Toll.result
      val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
      val action = query.map(_.map(row => TollModel(row.id, dateFormat.format(row.date), row.carType, row.licensePlate, row.tollAmount)))
      db.run(action)
    }

    def passVehicle(carType: String, licensePlate: String): Future[Int] = {
      val tollAmount = carType.toLowerCase match {
        case "bike" => 7
        case "car" => 13
        case "truck" => 20
        case _ => throw new IllegalArgumentException(s"Unknown car type: $carType")
      }
      val date = new java.sql.Date(System.currentTimeMillis())
      val newToll = TollRow(0, date, carType, licensePlate, tollAmount)

      val insertAction = (Toll returning Toll.map(_.id)) += newToll
      db.run(insertAction)
    }

    def getSalesByDate(dateStr: String): Future[Seq[TollModel]] = {
      val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
      val date = new java.sql.Date(dateFormat.parse(dateStr).getTime)
      val query = for {
        toll <- Toll if toll.date === date
      } yield (toll.id, toll.date, toll.carType, toll.licensePlate, toll.tollAmount)

      db.run(query.result).map(_.map {
        case (id, date, carType, licensePlate, tollAmount) => TollModel(id, date.toString, carType, licensePlate, tollAmount)
      })
    }

    def getStateWithMostVehiclesByDate(dateStr: String): Future[Option[(String, Int)]] = {
      val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
      val date = new java.sql.Date(dateFormat.parse(dateStr).getTime)
      val query = for {
        toll <- Toll if toll.date === date
      } yield toll.licensePlate

      db.run(query.result).map { licensePlates =>
        val stateCounts = licensePlates.groupBy(_.substring(0, 2)).mapValues(_.size)
        if (stateCounts.nonEmpty) Some(stateCounts.maxBy(_._2)) else None
      }
    }

  }

  class TollRoutes(vendingMachineRepository: TollRepository)(implicit ec: ExecutionContext) extends Json4sSupport {
    val route: Route = path("tolls") {
      get {
        onComplete(vendingMachineRepository.getAllTolls) {
          case Success(computers) => complete(serialization.write(computers)(formats))
          case Failure(ex) => complete((500, s"An error occurred: ${ex.getMessage}"))
        }
      }
    } ~ path("pass" / Segment / Segment) { (carType, licensePlate) =>
      post {
        onComplete(vendingMachineRepository.passVehicle(carType, licensePlate)) {
          case Success(_) => complete((200, s"Vehicle $carType with license plate $licensePlate pass the toll successfully."))
          case Failure(ex) => complete((500, s"An error occurred: ${ex.getMessage}"))
        }
      }
    } ~ path("sales" / Segment) { date =>
      get {
        onComplete(vendingMachineRepository.getStateWithMostVehiclesByDate(date)) {
          case Success(Some((state, count))) => complete(s"State with most vehicles: $state with $count vehicles.")
          case Success(None) => complete((404, s"No vehicles found for date: $date"))
          case Failure(ex) => complete((500, s"An error occurred: ${ex.getMessage}"))
        }
      }
    }  ~ path("") {
      get {
        complete(serialization.write(Seq("Goto http://localhost:8080/products"))(formats))
      }
    }
  }

  object TollApp extends App {
    val logger = LoggerFactory.getLogger(getClass)
    implicit val system: ActorSystem = ActorSystem()
    implicit val ec: ExecutionContextExecutor = system.dispatcher

    val (profile, dbConfig) = DatabaseConfig.getDbConfig("postgres")
    val db = Database.forConfig("", dbConfig)
    val tollRepository = new TollRepository(db)
    val tollRoutes = new TollRoutes(tollRepository)
    val bindingFuture = Http().newServerAt("localhost", 8080).bind(tollRoutes.route)

    logger.info(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }

object E06Main extends App:
  E06.TollApp.main(args)