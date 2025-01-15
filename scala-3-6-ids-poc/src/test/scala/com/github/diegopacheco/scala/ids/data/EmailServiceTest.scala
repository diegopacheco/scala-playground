package com.github.diegopacheco.scala.ids.data


import org.json4s.{CustomSerializer, DefaultFormats, Formats, JString}
import org.json4s.native.Serialization
import org.scalatest.funsuite.AnyFunSuite
import java.util.UUID

// Custom serializer for UUID
object UUIDSerializer extends CustomSerializer[UUID](_ => (
  {
    case JString(s) => UUID.fromString(s)
  },
  {
    case uuid: UUID => JString(uuid.toString)
  }
))

class EmailServiceTest extends AnyFunSuite {

  implicit val formats: Formats = DefaultFormats + UUIDSerializer

  val accountService: AccountsService.type = AccountsService
  val emailService = new EmailService(accountService)

  test("getEmailByID should return the correct email for a valid account ID") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479")
    val email = emailService.getEmailByID(accountID)
    assert(email == "bananas@gmail.com")
  }

  test("getEmailByID should throw an exception for an invalid account ID") {
    val invalidAccountID = UUID.randomUUID()
    assertThrows[RuntimeException] {
      emailService.getEmailByID(invalidAccountID)
    }
  }

  test("getEmailByID should return the correct email for another valid account ID") {
    val accountID = UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d480")
    val email = emailService.getEmailByID(accountID)
    assert(email == "apples@gmail.com")
  }

  class BusServiceFake extends Bus {
    var eventHandler: Event => Unit = _ => ()
    override def subscribe(eventType: EventType, handler: Event => Unit): Boolean = {
      eventHandler = handler
      true
    }
    override def publish(eventType: EventType, event: Any): Boolean = {
      eventHandler(event.asInstanceOf[Event])
      true
    }
  }

  test("BusService should subscribe to accountCreated and process the event correctly") {
    val event = (UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479"), Map("balance" -> 100))
    val eventJson = Serialization.write(event)
    val account: (UUID, Map[String, Int]) = Serialization.read[(UUID, Map[String, Int])](eventJson)

    val testBusService = new BusServiceFake

    testBusService.subscribe("accountCreated", (event: Event) => {
      val eventJson = Serialization.write(event)
      val account: (UUID, Map[String, Int]) = Serialization.read[(UUID, Map[String, Int])](eventJson)
      println("[EMAIL] - ACK there is a new account: " + account._1 + " will update my db!")
    })

    testBusService.publish("accountCreated", event)
  }
}