package com.github.diegopacheco.scalatraining

import com.typesafe.config.ConfigFactory
import slick.jdbc.JdbcProfile

import java.text.SimpleDateFormat
import java.util.Date

/*
 * Build a REST service using apache Pekko/http and json4s that implement a vending machine
 * capable of having stock of items, and sell items as long as they have stock, vending machine should have
 * an operation to tell the sales that happen given a specific date.
 */
object E04:

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

  case class ProductModel(id: Int, name: String, stock: Int)
  case class SaleModel(id: Int, productId: Int, date: String, quantity: Int)
  case class SalesProductModel(id: Int, productName: String, date: String, quantity: Int, currentStock: Int)

  trait Json4sSupport {
    private val customTypeHints = FullTypeHints(List(
      classOf[ProductModel],
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

  class VendingMachineRepository(db: Database)(implicit ec: ExecutionContext) {
    def getAllProducts: Future[Seq[ProductModel]] = {
      val query = Products.result
      val action = query.map(_.map(row => ProductModel(row.id, row.name, row.stock)))
      db.run(action)
    }

    def buyProduct(productId: Int): Future[Int] = {
      val query = for {
        product <- Products if product.id === productId
      } yield product.stock

      val action = query.result.headOption.flatMap {
        case Some(stock) if stock > 0 =>
          val updateAction = for {
            _ <- Products.filter(_.id === productId).map(_.stock).update(stock - 1)
            _ <- Sales += SalesRow(0, new java.sql.Date(System.currentTimeMillis()), productId, 1)
          } yield 1 // Return 1 to indicate success
          updateAction.transactionally
        case _ => DBIO.successful(0) // Return 0 to indicate failure or out of stock
      }
      db.run(action)
    }

    def getSalesByDate(dateStr: String): Future[Seq[SalesProductModel]] = {
      val dateFormat = new SimpleDateFormat("yyyy-MM-dd")
      val date = new java.sql.Date(dateFormat.parse(dateStr).getTime)
      val query = for {
        sale <- Sales if sale.date === date
        product <- Products if product.id === sale.productId
      } yield (sale.id, product.name, sale.date, sale.quantity, product.stock)

      db.run(query.result).map(_.map {
        case (id, productName, date, quantity, currentStock) =>
          SalesProductModel(id, productName, date.toString, quantity, currentStock)
      })
    }
  }

  class VendingMachineRoutes(vendingMachineRepository: VendingMachineRepository)(implicit ec: ExecutionContext) extends Json4sSupport {
    val route: Route = path("products") {
      get {
        onComplete(vendingMachineRepository.getAllProducts) {
          case Success(computers) => complete(serialization.write(computers)(formats))
          case Failure(ex) => complete((500, s"An error occurred: ${ex.getMessage}"))
        }
      }
    } ~ path("buy" / IntNumber) { productId =>
      post {
        onComplete(vendingMachineRepository.buyProduct(productId)) {
          case Success(result) if result > 0 => complete(s"Product $productId bought successfully.")
          case Success(_) => complete((404, s"Product $productId not found or out of stock."))
          case Failure(ex) => complete((500, s"An error occurred: ${ex.getMessage}"))
        }
      }
    } ~ path("sales" / Segment) { date =>
      get {
        onComplete(vendingMachineRepository.getSalesByDate(date)) {
          case Success(sales) => complete(serialization.write(sales)(formats))
          case Failure(ex) => complete((500, s"An error occurred: ${ex.getMessage}"))
        }
      }
    }~ path("") {
      get {
        complete(serialization.write(Seq("Goto http://localhost:8080/products"))(formats))
      }
    }
  }

  object VendingMachineApp extends App {
    val logger = LoggerFactory.getLogger(getClass)
    implicit val system: ActorSystem = ActorSystem()
    implicit val ec: ExecutionContextExecutor = system.dispatcher

    val (profile, dbConfig) = DatabaseConfig.getDbConfig("postgres")
    val db = Database.forConfig("", dbConfig)
    val vendingMachineRepository = new VendingMachineRepository(db)
    val vendingMachineRoutes = new VendingMachineRoutes(vendingMachineRepository)
    val bindingFuture = Http().newServerAt("localhost", 8080).bind(vendingMachineRoutes.route)

    logger.info(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
    StdIn.readLine()
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }

object E04Main extends App:
  E04.VendingMachineApp
