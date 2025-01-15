package com.github.diegopacheco.scala.ids.envelope

import java.util.UUID

trait AccountID {
  def getUUID: UUID
  def getEnvelope: Envelope
}

trait AccountsContract {
  def createAccount: AccountID
  def getAllAccounts: Map[String, Envelope]
}

class Account(val uuid: UUID, val envelope: Envelope) extends AccountID {
  def getUUID: UUID = uuid
  def getEnvelope: Envelope = envelope
}

object Account {
  def fromUUID(uuid: UUID): Account = new Account(uuid, new PlainEnvelope())
}

object AccountsService extends AccountsContract {
  // here we could be just storing the envelopes, but this way makes faster to process it.
  private var accounts: Map[UUID, Map[String, Int]] = Map(
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d479") -> Map("loginService" -> 10_001, "EmailService" -> 20_001, "PurchaseService" -> 30_001),
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d480") -> Map("loginService" -> 10_002, "EmailService" -> 20_002, "PurchaseService" -> 30_002),
    UUID.fromString("f47ac10b-58cc-4372-a567-0e02b2c3d481") -> Map("loginService" -> 10_003, "EmailService" -> 20_003, "PurchaseService" -> 30_003)
  )

  def createAccount: AccountID = {
    val uuid = UUID.randomUUID()
    val envelope = new PlainEnvelope()
    accounts = accounts.updated(uuid, Map.empty[String, Int])
    new Account(uuid, envelope)
  }

  def getAllAccounts: Map[String, Envelope] = {
    accounts.map {
      case (uuid, services) =>
        val envelope = PlainEnvelope()
        services.foreach { case (serviceName, serviceID) =>
          envelope.addID(serviceName, serviceID.toString)
        }
        uuid.toString -> envelope
    }
  }

}
